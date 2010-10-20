package vooga.games.tron;
/**
 * This class holds the basic layout for the game (which is the grid space)
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */

public class GridSpace {
	private double totalRow;
	private double totalColumn;

	public GridSpace(double totalRow,double totalColumn){  //for example, for the screen size 480*480 and the image size is 5*5,it is better to set it (480/5) by (480/5)
		this.totalRow=totalRow;      				 
		this.totalColumn=totalColumn;
	}	
	/**
	 * get the total number of rows in the grid
	 * @return
	 */
	public int getTotalRow(){
		return (int)totalRow;
	}
	/**
	 * get the total number of columns in the grid
	 * @return
	 */
	public int getTotalColumn(){
		return (int)totalColumn;
	}	

}
