package vooga.games.tronupdate.util;

public class RandomLayoutGenerator {
	private int difficultyLevel;
	public void setDifficultyLevel(int diff){
		difficultyLevel = diff;
	}
	public Grid[][] generateGrid(int numRow,int numCol,int[] startX,int[] startY){
		int preventRangeX = numCol/20;
		int preventRangeY = numRow/20;
		Grid[][] grid = new Grid[numRow][numCol];
		initWalls(grid);
		initBlocks(grid);
		initBonuses(grid);
		return grid;
	}
	private void initWalls(Grid[][] grid){
		
	}
	private void initBlocks(Grid[][] grid){
		
	}
	private void initBonuses(Grid[][] grid){
		
	}
	
	
	
	
}
