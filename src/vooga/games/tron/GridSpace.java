package vooga.games.tron;
/**
 * This class holds the basic layout for the game (which is the grid space)
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */

public class GridSpace {
	private int totalRow;
	private int totalColumn;

	public GridSpace(int totalRow,int totalColumn){  //for example, for the screen size 480*480 and the image size is 5*5,it is better to set it (480/5) by (480/5)
		this.totalRow=totalRow;      				 
		this.totalColumn=totalColumn;
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

}
