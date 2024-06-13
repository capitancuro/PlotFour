package contexts;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Button;
import assets.AssetsManager;

import java.lang.Math;

public class PlotFour extends Group {
	
	private class Piece extends Circle{																	
		
		public int user = 0;
		public Position position = null;
		
		public Piece(int user) {
			super(25);
			this.user = user;
		}
	}
	
	private class Position extends Rectangle{																
		
		public int user = 0;
		public int row = 0;
		public int col = 0;
		public Piece piece = null;
		
		public Position(int row, int col, double x, double y) {
			super(x, y, 50, 50);
			this.row = row;
			this.col = col;
		}
	}
	
	private class Selector extends Polygon {
		public Piece currentPiece = null;
		
		public void setCurrentPiece(Piece currentPiece) {
			currentPiece.setCenterX(this.getPoints().get(4));
			currentPiece.setCenterY(this.getPoints().get(5));
			//currentPiece.setFill(Color.TRANSPARENT);
			//currentPiece.setStroke(Color.RED);
			this.currentPiece = currentPiece;
		}
	}
	
	private double width = 0;
	private double height = 0;
	
	public AssetsManager assetsManager = null;
	public Controller controller = null;
	
	private final int ROWS = 6;
	private final int COLS = 7;
	
	private Position[][] positions = new Position[ROWS][COLS];
	private final int UNITS = 42;
	private Piece[] pieces = new Piece[UNITS];
	private final Selector selector = new Selector();
	
	private int turn = 0;
	private int currentUnit = 0;
	private int winner = 0;
	
	public PlotFour(double width, double height, AssetsManager assetsManager, Controller controller) {
		
		this.assetsManager = assetsManager;
		this.controller = controller;
		
		this.width = width;
		this.height = height;
	}
	
	private void setPieces() {
		
		for (int piece = 0; piece < UNITS; piece++) {
			
			if (piece % 2 == 0)
				pieces[piece] = new Piece(1);
			else 
				pieces[piece] = new Piece(2);
			
			pieces[piece].setFill(Color.TRANSPARENT);
			
			this.getChildren().add(pieces[piece]);
		}
	}
	
	private void setPositions() {
		
		double y = height/2 - (ROWS*50)/2;
		for (int row = 0; row < ROWS; row++) {
			double x = width/2 - (COLS*50)/2;
			for (int col = 0; col < COLS; col++) {
				positions[row][col] = new Position(row, col, x, y);
				positions[row][col].setFill(Color.TRANSPARENT);
				positions[row][col].setStroke(Color.WHITE);
				this.getChildren().add(positions[row][col]);
				x += 50;
			}
			y += 50;
		}
	}
	
	private void setSelector() {
		selector.setFill(Color.WHITE);
		
		//Point 1
		selector.getPoints().add(positions[0][3].getX());
		selector.getPoints().add(positions[0][3].getY() - 50);
		
		//Point 2
		selector.getPoints().add(positions[0][3].getX() + 50);
		selector.getPoints().add(positions[0][3].getY() - 50);
		
		//Point 3
		selector.getPoints().add(positions[0][3].getX() + 25);
		selector.getPoints().add(positions[0][3].getY() - 25);
		
		selector.setCurrentPiece(pieces[currentUnit]);

		this.getChildren().add(selector);
		
		selector.toBack();
	}
	
	private void move(int col) {
		if(selector.currentPiece.user == 1)
			selector.currentPiece.setFill(Color.RED);
		else 
			selector.currentPiece.setFill(Color.YELLOW);
		
		if (col >= 0 && col < COLS && positions[0][col].user == 0) {
			
			int row = 0;
			while(row < ROWS && positions[row][col].user == 0)
				row++;
			
			positions[row - 1][col].piece = selector.currentPiece;
			positions[row - 1][col].user = positions[row - 1][col].piece.user;
			positions[row - 1][col].piece.setTranslateX(positions[row - 1][col].getX() - selector.currentPiece.getCenterX() + 25);
			positions[row - 1][col].piece.setTranslateY(positions[row - 1][col].getY() - selector.currentPiece.getCenterY() + 25);
			
			winner = win(positions[row - 1][col], -1, 0, 0);

			if (turn == 0)
				turn = 1;
			else
				turn = 0;
			
			currentUnit++;
			
			if(currentUnit < UNITS && winner == 0)
				selector.setCurrentPiece(pieces[currentUnit]);
			else {
				selector.currentPiece = null;
				selector.setFill(Color.TRANSPARENT);
			}
		}
	}
	
	private int win(Position position, int v, int u, int n) {
		if(position == null)
			return 0;
		
		int row = position.row, col = position.col, count = 0;

																												
		while ((row >= 0 && col >= 0) && (row < positions.length && col < positions[row].length)) {

			if (positions[row][col].user == position.user)
				count++;
			else
				break;

			row += v;
			col += u;
		}
		
																												
		row = position.row; 
		col = position.col; 

		while ((row >= 0 && col >= 0) && (row < ROWS && col < COLS)) {

			if (positions[row][col].user == position.user && (row != position.row || col != position.col))		
				count++;
			else if (row != position.row || col != position.col)
				break;

			row += v * -1;
			col += u * -1;

		}

		if (count == 4)
			return position.user;
		else if (n < 4) {
			if (v + u == -1)
				return win(position, v, 1, ++n);
			else if (v + u == 0)
				return win(position, 0, u, ++n);
			else
				return win(position, 1, u, ++n);
		}
		else
			return 0;
	}
	
	private void setGame() {
		setPieces();
		setPositions();
		setSelector();
	}
	
	public void startGame() {
		
		setGame();
		controller.port.setOnMouseMoved(event ->{
			
			if (currentUnit < UNITS && winner == 0)
				if(event.getSceneX() >= positions[0][0].getX() && event.getSceneX() < positions[0][6].getX() + 50) {
					int col = 0;
					
					while(Math.abs(positions[0][col].getX() - event.getSceneX()) > 50)
						col++;
					
					selector.setTranslateX(positions[0][col].getX() - selector.getPoints().get(0));
					selector.currentPiece.setTranslateX(positions[0][col].getX() - selector.currentPiece.getCenterX() + 25);
				}
		});
		
		controller.port.setOnMouseClicked(event -> {
			
			if (currentUnit < UNITS && winner == 0)
				if(event.getSceneX() >= positions[0][0].getX() && event.getSceneX() < positions[0][6].getX() + 50) {
					int col = 0;
					
					while(Math.abs(positions[0][col].getX() - event.getSceneX()) > 50)
						col++;
					
					move(col);
				}
		});
	}
	
	private void endGame() {								
	}
}
...
