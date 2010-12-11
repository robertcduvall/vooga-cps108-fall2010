package vooga.games.tronupdate.util;

import vooga.games.tronupdate.util.Grid;
import vooga.games.tronupdate.items.Player;
/**
 * The original AI for the game
 * @author Jiaqi Yan
 *
 */
public class AI_0 extends AI{
	
	public AI_0(Player player) {
		super(player);
	}
	private int preventRange = 5;
	int leftRightCounter,upDownCounter;
	String leftRightDirection, upDownDirection;
	
	//public void setPlayer(Player player){
	//	myPlayer = player;
	//}
	
	
	
	
	public void update(Grid[][] grid,int row,int col,Direction direction,int oRow,int oCol,Direction oDirection){
		int rowLength = grid.length;
		int colLength = grid[0].length;
		
		boolean[][] blocksTaken = new boolean[rowLength][colLength];
		for(int i=0;i<rowLength;i++){
			for(int j=0;j<colLength;j++){
				blocksTaken[i][j] = grid[i][j].isTaken();
			}
		}
		react(row,col,direction,blocksTaken);
	}
	
	/**
	 * Far strategy: try to avoide the enemy when from a range
	 * @param row
	 * @param col
	 * @param direction
	 * @param blocksTaken
	 */
	private void react(int row,int col, Direction direction, boolean[][] blocksTaken){
		if(direction.equals(Direction.up)){
			boolean obstacleUp = false;
			for(int i=1;i<=preventRange;i++){
				if(row-i<0) {
					obstacleUp = true; break; 
				}
				if(blocksTaken[row-i][col]) {
					obstacleUp = true; break;
				}
			}
			if(obstacleUp)	handleLeftRight();			
		}
		else if(direction.equals(Direction.down)){
			boolean obstacleDown = false;
			for(int i=1;i<=preventRange;i++){
				if(row+i>=blocksTaken.length) {
					obstacleDown = true; break; 
				}
				if(blocksTaken[row+i][col]) {
					obstacleDown = true; break;
				}
			}
			if(obstacleDown) handleLeftRight();
		}
		else if(direction.equals(Direction.left)){
			boolean obstacleLeft = false;
			for(int i=1;i<=preventRange;i++){
				if(col-i<0) {
					obstacleLeft = true; break; 
				}
				if(blocksTaken[row][col-i]) {
					obstacleLeft = true; break;
				}
			}
			if(obstacleLeft) handleUpDown();
		}
		else{
			boolean obstacleRight = false;
			for(int i=1;i<=preventRange;i++){
				if(col+i>=blocksTaken[0].length) {
					obstacleRight = true; break; 
				}
				if(blocksTaken[row][col+i]) {
					obstacleRight = true; break;
				}
			}
			if(obstacleRight) handleUpDown();
		}
	
	}
	/**
	 * This methods handles the upcoming left/right turn by taking into account the previous turns.  
	 */
	public void handleLeftRight(){
		if (leftRightCounter==1){
			leftRightCounter = 0;
			if(leftRightDirection.equals("right")) right();
			else left();
		}
		else{
			leftRightCounter = 1;
			randomLeftRight();
		}
	}
	/**
	 * This method handles the upcoming up/down turning by taking into account the previous turns.
	 */
	public void handleUpDown(){
		if (upDownCounter==1){
			upDownCounter = 0;
			if(upDownDirection.equals("up")) up();
			else down();
		}
		else{
			upDownCounter = 1;
			randomUpDown();
		}
	}
	/**
	 * This method randomly decides whether to turn left or right. 
	 */
	public void randomLeftRight(){
		if(Math.random()<0.5){
			left(); leftRightDirection = "left";
		}
		else{
			right(); leftRightDirection = "right";
		}
	}
	/**
	 * This methods randomly decides whether to turn up or down
	 */
	public void randomUpDown(){
		if(Math.random()<0.5){
			up(); upDownDirection = "up";
		}
		else{
			down(); upDownDirection = "down";
		}
	}	
	
}
