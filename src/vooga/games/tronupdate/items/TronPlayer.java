package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;
import static vooga.games.tronupdate.state.TronGamePlayState.*;

import vooga.engine.core.BetterSprite;
import vooga.games.tronupdate.util.GridSpace;
import vooga.games.tronupdate.util.*;

public class TronPlayer extends BetterSprite {

	private static final long serialVersionUID = 1L;

	//private Position pos;
	private Direction dir;
	private Direction initialDir;

	private String direction;  //left,right,down,up
	private String initDirection;

	//public boolean[][] blocks;
	public GridSpace grid;
	//private Sprite[][] player1Blocks;

	private int playerInitialRow;
	private int playerInitialCol;

	private int playerCurrentRow;
	private int playerCurrentColumn;
	private int playerImageWidth;
	private int speedUp;
	private boolean isAI;
	private int score;

	/**
	 * constructor
	 * @param image
	 * @param initialColPosition
	 * @param initialRowPosition
	 * @param gridSpace
	 * @param playerImageWidth
	 * @param initialDirection
	 */
	public TronPlayer(BufferedImage image,int initialColPosition,int initialRowPosition,int playerImageWidth, String initialDirection){
		super(image,initialColPosition*playerImageWidth,initialRowPosition*playerImageWidth);
		playerInitialRow = initialRowPosition;
		playerInitialCol = initialColPosition;

		direction=initialDirection;
		initDirection = initialDirection;

		this.playerImageWidth=playerImageWidth;
		playerCurrentRow=initialRowPosition;
		playerCurrentColumn=initialColPosition;
		//blocks=new boolean[gridSpace.getTotalRow()+2][gridSpace.getTotalColumn()+2];
		speedUp=1;
		score = 0;
		grid = new GridSpace(GRID_WIDTH,GRID_HEIGHT);

	}

	public TronPlayer(BufferedImage image,int initialColPosition,int initialRowPosition,int playerImageWidth, Direction dir){
		this(image,initialColPosition,initialRowPosition,playerImageWidth,"");
		//super(image,initialColPosition*playerImageWidth,initialRowPosition*playerImageWidth);
		this.dir=dir;
		this.initialDir=Direction.right;
	}


	public void resetPosition(){

		setPlayerRowandCol(playerInitialRow,playerInitialCol);		
		direction = initDirection;
		dir=initialDir;
		grid = new GridSpace(GRID_WIDTH,GRID_HEIGHT);
		//blocks = new boolean[grid.getTotalRow()+2][grid.getTotalColumn()+2];

	}
	
	public void setAsAI(boolean flag){
		isAI=flag;
	}
	
	public boolean isAI(){
		return isAI;
	}
	/**
	 * mark the block if it is filled by items in the game 
	 * @param row
	 * @param col
	 * 
	 */
	public void fillBlock(int row,int col){
		grid.fillGrid(row,col);
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
		speedUp=speedUp+rate;
	}
	/**
	 * set the direction the player is heading
	 * @param direction
	 */
	public void setDirection(Direction dir){
		this.dir=dir;
	}
	/**
	 * get the direction the player is heading
	 * @return
	 */
	public Direction getDirection(){
		return dir;
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

	public void changePlayerColumn(int amount){
		playerCurrentColumn=playerCurrentColumn+amount;
	}

	public void changePlayerRow(int amount){
		playerCurrentRow=playerCurrentRow+amount;
	}

	/**
	 * routinely update the X-coordinate for the player (keep going in the same X-direction)
	 * @return
	 */
	public double playerXDirectionMove(){
		if(dir==Direction.left){
			for(int i=0;i<speedUp;i++){
				if(playerInbound()){
					fillBlock(playerCurrentRow,playerCurrentColumn);
					changePlayerColumn(-1);

				}
			}
			return -speedUp*playerImageWidth;//getPlayerXPosition();
		}
		else if(dir==Direction.right){
			for(int i=0;i<speedUp;i++){
				fillBlock(playerCurrentRow,playerCurrentColumn);
				changePlayerColumn(1);
			}
			return speedUp*playerImageWidth;//getPlayerXPosition();
		}
		else if(dir==Direction.down){
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
		if(dir==Direction.left){//direction.equals("left")){
			return 0;//getPlayerYPosition();
		}
		else if(dir==Direction.right){
			return 0;//getPlayerYPosition();
		}
		else if(dir==Direction.down){
			for(int i=0;i<speedUp;i++){
				fillBlock(playerCurrentRow,playerCurrentColumn);
				changePlayerRow(1);
			}
			return speedUp*playerImageWidth;//getPlayerYPosition();
		}
		else{ //up
			for(int i=0;i<speedUp;i++){
				if(playerInbound()){
					fillBlock(playerCurrentRow,playerCurrentColumn);
					changePlayerRow(-1);
				}
			}
			return -speedUp*playerImageWidth;
		}
	}
	/**
	 * update the X-coordinate position of the player
	 * @param playerDirection
	 * @return
	 */
	public double updatePlayerXPosition(Direction playerDirection){

		this.dir=playerDirection;
		return playerXDirectionMove();
	}
	/**
	 * update the Y-coordinate position for the player
	 * @param playerDirection
	 * @return
	 */
	public double updatePlayerYPosition(Direction playerDirection){
		this.dir=playerDirection;
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
		if(!(getDirection()==Direction.down)&&!(getDirection()==Direction.up)){
			setDirection(Direction.down);
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));

			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}  		
	}
	/**
	 * handles left turning
	 */
	public void left() {
		if(!(getDirection()==Direction.left)&&!(getDirection()==Direction.right)){
			setDirection(Direction.left);
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	/**
	 * handles right turning
	 */
	public void right() {
		if(!(getDirection()==Direction.right)&&!(getDirection()==Direction.left)){
			setDirection(Direction.right);
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
			//myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	/**
	 * handles up turning
	 */
	public void up() {
		if(!(getDirection()==Direction.up)&&!(getDirection()==Direction.down)){
			setDirection(Direction.up);
			move(updatePlayerXPosition(getDirection()),updatePlayerYPosition(getDirection()));
		}
	}	

	public void update(long elapsedTime) {

		super.update(elapsedTime);
		move(playerXDirectionMove(),playerYDirectionMove());
		//buildBlockWall();
		//setLocation(playerXDirectionMove(),playerYDirectionMove());
	}


}
