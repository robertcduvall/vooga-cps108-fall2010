package vooga.games.tronupdate.util;

import vooga.games.tronupdate.items.Player;

/**
 * Wall-hugging strategy AI-bot
 * @author Jiaqi Yan
 */

public class AI_3 extends AI{
	private int nearRow,nearCol;
	private boolean targetSet;
	public AI_3(Player player){
		super(player);
	}

	@Override
	public void update(Grid[][] grid, int row, int col, Direction direction,
			int oRow, int oCol, Direction oDirection) {
		int rowLength = grid.length;
		int colLength = grid[0].length;
		boolean[][] blocksTaken = new boolean[rowLength][colLength];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				blocksTaken[i][j] = grid[i][j].isTaken();
			}
		}
		if(!targetSet) setTarget(blocksTaken,row,col,direction);
		
		
		
	}
	
	private void setTarget(boolean[][] blocksTaken,int row,int col,Direction direction){
		
		
		
	}
	
	

}
