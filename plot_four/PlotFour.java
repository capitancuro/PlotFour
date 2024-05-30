package plot_four;
import java.util.Scanner;

public class PlotFour {																		//Class used to create Plot Four plot_four_app
	
	private class Piece {																	//Class used to create pieces
		
		public int user = 0;
		public Position position = null;
		
		public Piece(int user, Position position) {
			this.user = user;
			this.position = position;
		}
	}
	
	private class Position {																//Class used to create positions
		
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
	//Array that stores the pieces
	private Piece[] pieces = new Piece[UNITS];
	//Array that stores the positions
	private Position[][] positions = new Position[ROWS][COLS];
	
	private int turn = 0;
	private int currentUnit = 0;
	private int winner = 0;
	
	public PlotFour() {
		startGame();
	}
	
	private void setPieces() {																//Sets all pieces to be used during a plot_four_app
		for (int piece = 0; piece < UNITS; piece++)
			if (piece % 2 == 0)
				pieces[piece] = new Piece(1, null);
			else
				pieces[piece] = new Piece(2, null);
	}
	
	private void setPositions() {															//Sets all positions to be used during a plot_four_app
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLS; col++)
				positions[row][col] = new Position(0, row, col, null);
	}
	
	private void move(int col) {															//Selects column for a move
		
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
	
	private int win(Position position, int v, int u, int n) {							//Checks if there is a win
		if(position == null)
			return 0;
		
		int row = position.row, col = position.col, count = 0;

		//Checks half a vertical, diagonal, or horizontal line based on the arguments assigned to r and c
		while ((row >= 0 && col >= 0) && (row < positions.length && col < positions[row].length)) {

			if (positions[row][col].user == position.user)
				count++;
			else
				break;
			//System.out.println(count);
			row += v;
			col += u;
		}
		
		//Re-assigns the values of y and x to r and c to check the other half of the line
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

		//Recursively checks the area centering a unit before returning the value of the unit
		//that won or 0.
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
	
	private void startGame() {																//Start a Plot Four plot_four_app
		
		//Instantiates all pieces for plot_four_app
		setPieces();
		//Instantiates all positions for plot_four_app
		setPositions();
		
		while(currentUnit < 42 && winner == 0) {
			break;
		}
	}
	
	private void endGame() {																//End a Plot Four plot_four_app									
		//To-do
	}
}
