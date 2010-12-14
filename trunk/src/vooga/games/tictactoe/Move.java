package vooga.games.tictactoe;

import vooga.engine.networking.client.Serializeable;

/**
 * Move is a simple example of how to use the Serializable interface. If you want to send a piece of data then wrap a class around that data and have it
 * implements the Serializable interface to be able to send it over the network.  In TicTacToe, the only piece of data we send besides status updates are
 * moves. The Move class wraps the column and row that make up a move and has serialize and deserialize methods that convert the Move into a String to send
 * over the network and convert the String into a Move to receive from the network.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class Move extends Serializeable{
	private int row;
	private int col;
	private static final String identifier = "move:";

	/**
	 * Constructor that saves the row and column.
	 * 
	 * @param row the row
	 * @param col the column
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Move(int row, int col){
		this.row = row;
		this.col = col;
	}

	/**
	 * Takes a String and parses the column and row from the string and returns a new Move object from that row and column.
	 * 
	 * @param data the String representing a Move object
	 * @return a new Move object based on the data in the data param
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static Serializeable deserialize(String data) {
		int move = Integer.parseInt(data.substring(identifier.length()));
		int col = move < 10 ? 0 : move / 10;
		int row = move % 10;
		return new Move(row, col);
	}

	/**
	 * Takes the row number and the column number and returns the String that represents that move.
	 * 
	 * @return String to send to the socket that represents the Move
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String serialize() {
		return identifier + Integer.toString(col * 10 + row);
	}
	
	public static String getIdentifier(){
		return identifier;
	}
	
	/**
	 * @return the row of the move
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column of the move
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getCol() {
		return col;
	}
}
