package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.games.tron.GridSpace;
import vooga.games.tronupdate.util.*;

public class TronPlayer extends BetterSprite {

	private static final long serialVersionUID = 1L;
	
	private Position pos;
	private Direction dir;
	
	private String direction;  //left,right,down,up
	private String initDirection;

	public boolean[][] blocks;
	private GridSpace grid;
	//private Sprite[][] player1Blocks;
	
	private int playerInitialRow;
	private int playerInitialCol;

	private int playerCurrentRow;
	private int playerCurrentColumn;
	private int playerImageWidth;
	private int speedUp;

	public int score;

	/**
	 * constructor
	 * @param image
	 * @param initialColPosition
	 * @param initialRowPosition
	 * @param gridSpace
	 * @param playerImageWidth
	 * @param initialDirection
	 */
	public TronPlayer(BufferedImage image,int initialColPosition,int initialRowPosition,GridSpace gridSpace,int playerImageWidth, String initialDirection){
		super(image,initialColPosition*playerImageWidth,initialRowPosition*playerImageWidth);

		playerInitialRow = initialRowPosition;
		playerInitialCol = initialColPosition;

		direction=initialDirection;
		initDirection = initialDirection;

		this.playerImageWidth=playerImageWidth;
		playerCurrentRow=initialRowPosition;
		playerCurrentColumn=initialColPosition;
		blocks=new boolean[gridSpace.getTotalRow()+2][gridSpace.getTotalColumn()+2];
		speedUp=1;
		score = 0;
		grid = gridSpace;

	}


	public void resetPosition(){

		setPlayerRowandCol(playerInitialRow,playerInitialCol);
		direction = initDirection;

		blocks = new boolean[grid.getTotalRow()+2][grid.getTotalColumn()+2];

	}
	/**
	 * mark the block if it is filled by items in the game 
	 * @param row
	 * @param col
	 * 
	 */
	public void fillBlock(int row,int col){
		blocks[row][col]=true;
	}

	/**
	 * get the width of the image
	 * @return
	 */
	public int getImageWidth(){
		return playerImageWidth;
	}
	/**
	 * speed up the speed
	 * @param rate
	 */
	public void setSpeedUp(int rate){
		speedUp=speedUp*2;
	}
	/**
	 * set the direction the player is heading
	 * @param direction
	 */
	public void setDirection(String direction){
		this.direction=direction;
	}
	/**
	 * get the direction the player is heading
	 * @return
	 */
	public String getDirection(){
		return direction;
	}
	/**
	 * set the row and column position for the player
	 * @param currentRow
	 * @param currentColumn
	 */
	public void setPlayerRowandCol(int currentRow,int currentColumn){
		playerCurrentRow=currentRow;
		playerCurrentColumn=currentColumn;
		fillBlock(playerCurrentRow,playerCurrentColumn);

	}
	/**
	 * get the row position of the player
	 * @return
	 */
	public double getPlayerRow(){
		return playerCurrentRow;
	}
	/**
	 * get the Y-coordinate position of the player
	 * @return
	 */
	public double getPlayerYPosition(){
		return playerCurrentRow*playerImageWidth;
	}
	/**
	 * get the column position for the player
	 * @return
	 */
	public double getPlayerColumn(){
		return playerCurrentColumn;
	}
	/**
	 * get the X-coordinate position for the player
	 * @return
	 */
	public double getPlayerXPosition(){
		return playerCurrentColumn*playerImageWidth;
	}

	public void setPlayerColumn(int amount){
		playerCurrentColumn=playerCurrentColumn+amount;
	}

	public void setPlayerRow(int amount){
		playerCurrentRow=playerCurrentRow+amount;
	}
	public void goUp() {

		moveY(0.9);
	}
	
	/**
	 * routinely update the X-coordinate for the player (keep going in the same X-direction)
	 * @return
	 */
	public double playerXDirectionMove(){
		if(direction.equals("left")){
			for(int i=0;i<speedUp;i++){
				setPlayerColumn(-1);
				if(playerInbound())
					fillBlock(playerCurrentRow,playerCurrentColumn);

			}
			return -playerImageWidth;//getPlayerXPosition();
		}
		else if(direction.equals("right")){
			for(int i=0;i<speedUp;i++){
				setPlayerColumn(1);
				fillBlock(playerCurrentRow,playerCurrentColumn);

			}
			return playerImageWidth;//getPlayerXPosition();
		}
		else if(direction.equals("down")){
			return 0;//getPlayerXPosition();
		}
		else{
			return 0;//getPlayerXPosition();
		}
	}
	/**
	 * routinely update the Y-coordinate for the player (keep going in the same Y-direction)
	 * @return
	 */
	public double playerYDirectionMove(){
		if(direction.equals("left")){
			return 0;//getPlayerYPosition();
		}
		else if(direction.equals("right")){
			return 0;//getPlayerYPosition();
		}
		else if(direction.equals("down")){
			for(int i=0;i<speedUp;i++){
				setPlayerRow(1);
				fillBlock(playerCurrentRow,playerCurrentColumn);
				

			}
			return playerImageWidth;//getPlayerYPosition();
		}
		else{ //up
			for(int i=0;i<speedUp;i++){
				setPlayerRow(-1);
				if(playerInbound())
					fillBlock(playerCurrentRow,playerCurrentColumn);

			}
			return -playerImageWidth;//getPlayerYPosition();
		}
	}
	/**
	 * update the X-coordinate position of the player
	 * @param playerDirection
	 * @return
	 */
	public double updatePlayerXPosition(String playerDirection){

		this.direction=playerDirection;
		return playerXDirectionMove();
	}
	/**
	 * update the Y-coordinate position for the player
	 * @param playerDirection
	 * @return
	 */
	public double updatePlayerYPosition(String playerDirection){
		this.direction=playerDirection;
		return playerYDirectionMove();
	}

	/**
	 * check to see if players are in bound
	 * @return true if players are still in bound
	 */
	public boolean playerInbound(){
		return playerCurrentRow>=0&&playerCurrentColumn>=0;
	}
	

	/**
	 * handles down turning
	 */
	public void down() {
		if(!getDirection().equals("down")&&!getDirection().equals("up")){
			setDirection("down");
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
			
			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}  		
	}
	/**
	 * handles left turning
	 */
	public void left() {
		if(!getDirection().equals("left")&&!getDirection().equals("right")){
			setDirection("left");
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	/**
	 * handles right turning
	 */
	public void right() {
		if(!getDirection().equals("right")&&!getDirection().equals("left")){
			setDirection("right");
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	/**
	 * handles up turning
	 */
	public void up() {
		if(!getDirection().equals("up")&&!getDirection().equals("down")){
			setDirection("up");
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}	
	
	public void update(long elapsedTime) {
		
		super.update(elapsedTime);
		move(playerXDirectionMove(),playerYDirectionMove());
		//buildBlockWall();
		//setLocation(playerXDirectionMove(),playerYDirectionMove());
	}
	
	
}
