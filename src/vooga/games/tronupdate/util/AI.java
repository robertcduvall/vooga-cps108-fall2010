package vooga.games.tronupdate.util;

import vooga.games.tronupdate.items.Player;

public abstract class AI{
	protected Player myPlayer;
	public abstract void update(Grid[][] grid,int row,int col,Direction direction);
}