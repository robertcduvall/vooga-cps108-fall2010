package vooga.games.towerdefense;


/**
 * A point along the path which gives a location 
 * for an enemy to travel to. A list of PathPoints creates
 * a full path for enemies to follow.
 * @author Justin Goldsmith
 *
 */
public class PathPoint {
	
	private int myX;
	private int myY;
	
	
	public PathPoint(int x, int y){
		myX = x;
		myY = y;
	}


	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}
	
	

}
