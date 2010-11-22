package vooga.games.tronupdate.util;

import java.util.Arrays;



public class GridSpace {
	private int totalRow;
	private int totalColumn;
	private boolean[][] gridsTaken;

	public GridSpace(int totalRow,int totalColumn){  //for example, for the screen size 480*480 and the image size is 5*5,it is better to set it (480/5) by (480/5)
		this.totalRow=totalRow;      				 
		this.totalColumn=totalColumn;
		gridsTaken = new boolean[totalRow][totalColumn];
		for(int i=0;i<totalRow;i++){
			Arrays.fill(gridsTaken[i],false);
		}
	}	
	/**
	 * get the total number of rows in the grid
	 * @return
	 */
	public int getTotalRow(){
		return totalRow;
	}
	/**
	 * get the total number of columns in the grid
	 * @return
	 */
	public int getTotalColumn(){
		return totalColumn;
	}		
	
	public void fillGrid(int row,int col){
		if((row>=totalRow||col>=totalColumn || row<0 || col<0)){
		return;
		}
		gridsTaken[row][col] = true;
	}
	
	public boolean isGridTaken(int row,int col){
		return gridsTaken[row][col];
	}
	
	public boolean[][] gridsTaken(){
		return gridsTaken;
	}
	
	public void updateGridsTaken(boolean[][] grids){
		gridsTaken = grids;
	}
	
}
