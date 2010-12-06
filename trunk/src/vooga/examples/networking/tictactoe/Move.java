package vooga.examples.networking.tictactoe;

import vooga.engine.networking.client.Serializeable;

public class Move implements Serializeable{
	private int row;
	private int col;

	public Move(int row, int col){
		this.row = row;
		this.col = col;
	}

	public static Serializeable deserialize(String data) {
		int move = Integer.parseInt(data);
		int col = move < 10 ? 0 : move / 10;
		int row = move % 10;
		return new Move(row, col);
	}

	@Override
	public String serialize() {
		return Integer.toString(col * 10 + row);
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
