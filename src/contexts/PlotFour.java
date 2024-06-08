package contexts;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlotFour extends Group {
	
	private class Piece extends Circle{																	
		
		public int user = 0;
		public Position position = null;
		
		public Piece(int user, Position position, double radius) {
			super(radius);
			this.user = user;
			this.position = position;
		}
	}
	
	private class Position {																
		
		public int user = 0;
		public int row = 0;
		public int col = 0;
		public Piece piece = null;
		
		public Position(int user, int row, int col, Piece piece) {
			this.user = user;
			this.row = row;
			this.col = col;
			this.piece = piece;
		}
	}
	
	private final int ROWS = 6;
	private final int COLS = 7;
	private final int UNITS = 42;
	private Piece[] pieces = new Piece[UNITS];
	private Position[][] positions = new Position[ROWS][COLS];
	
	private int turn = 0;
	private int currentUnit = 0;
	private int winner = 0;
	
	public String ass = "8==D";
	
	public PlotFour() {
		startGame();
	}
	
	private void setPieces() {
		for (int piece = 0; piece < UNITS; piece++)
			if (piece % 2 == 0)
				pieces[piece] = new Piece(1, null, 25);
			else
				pieces[piece] = new Piece(2, null, 25);
		
		pieces[0].setCenterX(200);
		pieces[0].setCenterY(200);
		pieces[0].setStroke(Color.YELLOW);
		this.getChildren().add(pieces[0]);
		
		pieces[1].setCenterX(500);
		pieces[1].setCenterY(500);
		pieces[1].setStroke(Color.RED);
		this.getChildren().add(pieces[1]);
	}
	
	private void setPositions() {
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLS; col++)
				positions[row][col] = new Position(0, row, col, null);
	}
	
	private void move(int col) {
		
		if (col >= 0 && col < COLS && positions[0][col].user == 0) {
			
			int row = 0;
			while(row < ROWS && positions[row][col].user == 0)
				row++;
			
			positions[row - 1][col].user = pieces[currentUnit].user;
			winner = win(positions[row - 1][col], -1, 0, 0);

			if (turn == 0)
				turn = 1;
			else
				turn = 0;
			
			currentUnit++;
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
	
	private void startGame() {
		
		setPieces();
		setPositions();
		
		while(currentUnit < 42 && winner == 0) {
			break;
		}
	}
	
	private void endGame() {								
	}
}
