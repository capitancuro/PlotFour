package contexts;

import java.lang.Math;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;  

import assets.AssetsManager;

public class PlotFour extends Group {
	
	private class LiveMenu extends Group {
		public Button newGame = null;
		public Button forfeit = null;
		public Button pauseMenu = null;
		
		public LiveMenu() {
			newGame = new Button("NEW GAME");
			newGame.setFont(Font.loadFont(assetsManager.getFont(), 15));
			newGame.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			newGame.setTextFill(Color.WHITE);
			newGame.setLayoutX(50);
			newGame.setLayoutY(positions[0][3].getY() - 50);
			
			newGame.setOnMouseClicked(event -> {
				reset();
				startGame();
				getChildren().remove(newGame);
				getChildren().add(forfeit);
			});
			
			newGame.setOnMouseEntered(event -> {
				newGame.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				newGame.setTextFill(Color.BLACK);
			});
			
			newGame.setOnMouseExited(event -> {
				newGame.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
				newGame.setTextFill(Color.WHITE);
			});
			
			forfeit = new Button("FORFEIT");
			forfeit.setOnMouseClicked(null);
			forfeit.setFont(Font.loadFont(assetsManager.getFont(), 15));
			forfeit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			forfeit.setTextFill(Color.WHITE);
			forfeit.setLayoutX(50);
			forfeit.setLayoutY(newGame.getLayoutY());
			getChildren().add(forfeit);
			
			forfeit.setOnMouseClicked(event -> {
				
				if(currentUnit % 2 == 0)
					record.setWinner(2);
				else
					record.setWinner(1);
			});
			
			
			forfeit.setOnMouseEntered(event -> {
				forfeit.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				forfeit.setTextFill(Color.BLACK);
			});
			
			forfeit.setOnMouseExited(event -> {
				forfeit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
				forfeit.setTextFill(Color.WHITE);
			});
			
			pauseMenu = new Button("MENU");
			pauseMenu.setOnMouseClicked(null);
			pauseMenu.setFont(Font.loadFont(assetsManager.getFont(), 15));
			pauseMenu.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			pauseMenu.setTextFill(Color.WHITE);
			pauseMenu.setLayoutX(50);
			pauseMenu.setLayoutY(forfeit.getLayoutY() + 50);
			getChildren().add(pauseMenu);
			
			pauseMenu.setOnMouseClicked(event -> {
				controller.changeContext("Pause Menu");
			});
			
			pauseMenu.setOnMouseEntered(event -> {
				pauseMenu.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				pauseMenu.setTextFill(Color.BLACK);
			});
			
			pauseMenu.setOnMouseExited(event -> {
				pauseMenu.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
				pauseMenu.setTextFill(Color.WHITE);
			});
		}
	}
	
	private class Record extends Group {
		
		public Text text = new Text("'TURN");
		public Circle user = new Circle(15);
		
		public Record() {
			
		
			setTurn(pieces[currentUnit].user);
			
			text.setFont(Font.loadFont(assetsManager.getFont(), 15));
			text.setFill(Color.WHITE);
			
			user.setCenterX(width/2 - (user.getRadius()*2 + user.getRadius() + text.getLayoutBounds().getWidth())/2 + 25);
			text.setX(user.getCenterX() + user.getRadius());
			
			text.setY(height - 50);
			user.setCenterY((height - 50) - text.getLayoutBounds().getHeight()/2);
			
			getChildren().add(user);
			getChildren().add(text);
		}
		
		public void setTurn(int user) {
			
			if (user == 1)
				this.user.setFill(Color.RED);
			else
				this.user.setFill(Color.YELLOW);
				
		}
		
		public void setWinner(int winner) {
			text.setText("WINS");
			text.setFont(Font.loadFont(assetsManager.getFont(), 25));
			user.setRadius(25);
			
			user.setCenterX(width/2 - (user.getRadius()*2 + 25 + text.getLayoutBounds().getWidth())/2 + 25);
			text.setX(user.getCenterX() + 50);
			
			text.setY(positions[0][3].getY() - 25);
			user.setCenterY((positions[0][3].getY() - 25) - text.getLayoutBounds().getHeight()/2);
			
			selector.currentPiece = null;
			selector.setFill(Color.TRANSPARENT);
			
			if(winner == 1) {
				user.setFill(Color.RED);
			}
			else if(winner == 2) {
				user.setFill(Color.YELLOW);
			}
			else {
				text.setText("DRAW");
				user.setCenterX(width/2 - (user.getRadius()*2 + 25 + text.getLayoutBounds().getWidth())/2 + 25);
			}
			
			liveMenu.getChildren().remove(liveMenu.forfeit);
			liveMenu.getChildren().add(liveMenu.newGame);
		}
		
	}
	
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
			currentPiece.setCenterX(getPoints().get(4));
			currentPiece.setCenterY(getPoints().get(5));
			this.currentPiece = currentPiece;
		}
		
		public int slide(MouseEvent event) {
			
			int col = -1;
			
			if (currentUnit < UNITS && winner == 0 && currentPiece != null)
				if(event.getSceneX() >= positions[0][0].getX() && event.getSceneX() < positions[0][6].getX() + 50) {
					
					col = 0;
					
					while(Math.abs(positions[0][col].getX() - event.getSceneX()) > 50)
						col++;
					
					setTranslateX(positions[0][col].getX() - getPoints().get(0));
					currentPiece.setTranslateX(positions[0][col].getX() - currentPiece.getCenterX() + 25);
				}
			
			return col;
		}
		
		public void select(MouseEvent event) {
			
			int col = slide(event);
			
			if(col > -1 && controller.port.getRoot() == PlotFour.this) {
				move(col);
			}
			
		}
	}
	
	private double width = 0;
	private double height = 0;
	
	Media audio = null;
	MediaPlayer audioPlayer = null;
	
	public AssetsManager assetsManager = null;
	public Controller controller = null;
	
	private final int ROWS = 6;
	private final int COLS = 7;
	
	private LiveMenu liveMenu = null;
	
	private Record record = null;
	
	private Position[][] positions = null;
	private final int UNITS = 42;
	private Piece[] pieces = null;
	private Selector selector = null;
	
	private int currentUnit = 0;
	private int winner = 0;
	
	public PlotFour(double width, double height, AssetsManager assetsManager, Controller controller) {
		
		this.width = width; //integrate into controller class
		this.height = height;
		
		this.assetsManager = assetsManager;
		this.controller = controller;
		
		audio = new Media(assetsManager.getDropAudio());
		audioPlayer = new MediaPlayer(audio);
		audioPlayer.setVolume(.25);
	}

	
	private void setPieces() {
		
		pieces = new Piece[UNITS];
		
		for (int piece = 0; piece < UNITS; piece++) {
			
			if (piece % 2 == 0)
				pieces[piece] = new Piece(1);
			else 
				pieces[piece] = new Piece(2);
			
			pieces[piece].setFill(Color.TRANSPARENT);
			
			getChildren().add(pieces[piece]);
		}
	}
	
	private void setPositions() {
		
		positions = new Position[ROWS][COLS];
		
		double y = height/2 - (ROWS*50)/2;
		for (int row = 0; row < ROWS; row++) {
			double x = width/2 - (COLS*50)/2;
			for (int col = 0; col < COLS; col++) {
				positions[row][col] = new Position(row, col, x, y);
				positions[row][col].setFill(Color.TRANSPARENT);
				positions[row][col].setStroke(Color.WHITE);
				getChildren().add(positions[row][col]);
				x += 50;
			}
			y += 50;
		}
	}
	
	private void setSelector() {
		
		selector = new Selector();
		
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

		getChildren().add(selector);
		
		selector.toBack();
	}
	
	private void setRecord() {
		record = new Record();
		getChildren().add(record);
	}
	
	private void setMenu() {
		liveMenu = new LiveMenu();
		getChildren().add(liveMenu);
	}
	
	private void move(int col) {
		if (col >= 0 && col < COLS && positions[0][col].user == 0) {
			
			if(selector.currentPiece.user == 1)
				selector.currentPiece.setFill(Color.RED);
			else 
				selector.currentPiece.setFill(Color.YELLOW);
			
			int row = 0;
			while(row < ROWS && positions[row][col].user == 0)
				row++;
			
			positions[row - 1][col].piece = selector.currentPiece;
			positions[row - 1][col].user = positions[row - 1][col].piece.user;
			positions[row - 1][col].piece.setTranslateX(positions[row - 1][col].getX() - selector.currentPiece.getCenterX() + 25);
			positions[row - 1][col].piece.setTranslateY(positions[row - 1][col].getY() - selector.currentPiece.getCenterY() + 25);
			
			winner = win(positions[row - 1][col], -1, 0, 0);
			
			currentUnit++;
			
			if(currentUnit < UNITS && winner == 0) {
				selector.setCurrentPiece(pieces[currentUnit]);
				record.setTurn(pieces[currentUnit].user);
			}
			else
				record.setWinner(winner);
			
			audioPlayer.play();
			audioPlayer.seek(audioPlayer.getStartTime());
		}
	}
	
	private int win(Position position, int v, int u, int n) {
		if(position == null)
			return 0;
		
		int row = position.row, col = position.col, count = 0;

																												
		while ((row >= 0 && col >= 0) && (row < ROWS && col < COLS)) {

			if (positions[row][col].user == position.user)
				count++;
			else if (row != position.row || col != position.col || count == 4)
				break;

			row += v;
			col += u;
		}
		
		row = position.row; 
		col = position.col; 

		while ((row >= 0 && col >= 0) && (row < ROWS && col < COLS)) {

			if (positions[row][col].user == position.user && (row != position.row || col != position.col))		
				count++;
			else if (row != position.row || col != position.col || count == 4)
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
		setRecord();
		setMenu();
	}
	
	public void startGame() {
		
		setGame();
		controller.port.setOnMouseMoved(event ->{
			selector.slide(event);
		});
		
		controller.port.setOnMouseDragged(event ->{
			selector.slide(event);
		});
		
		controller.port.setOnMouseClicked(event -> {
			selector.select(event);
		});
	}
	
	private void reset() {
		
		winner = 0;
		currentUnit = 0;
		
		getChildren().remove(liveMenu);
		getChildren().remove(record);
		getChildren().remove(selector);
		
		for (int i = 0; i < 42; i++)
			getChildren().remove(pieces[i]);
		
		for (int row = 0; row < ROWS; row++)
			for ( int col = 0; col < COLS; col++)
				getChildren().remove(positions[row][col]);
		
	}
}
