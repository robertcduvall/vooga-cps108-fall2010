package vooga.games.tronupdate.util;

import vooga.games.tronupdate.items.Player;

public abstract class AI {
	protected Player myPlayer;
	
	public AI(Player player){
		myPlayer = player;
	}

	public abstract void update(Grid[][] grid, int row, int col,
			Direction direction, int oRow, int oCol, Direction oDirection);

	/**
	 * performs down turning
	 */
	public void down() {
		myPlayer.down();
	}

	/**
	 * performs left turning
	 */
	public void left() {
		myPlayer.left();
	}

	/**
	 * performs right turning
	 */
	public void right() {
		myPlayer.right();
	}

	/**
	 * performs up turning
	 */
	public void up() {
		myPlayer.up();
	}
}