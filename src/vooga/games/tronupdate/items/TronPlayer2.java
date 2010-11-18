package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;

import vooga.engine.core.Sprite;
import vooga.games.tron.GridSpace;

public class TronPlayer2 extends Sprite {

	private static final long serialVersionUID = 1L;
	private String direction;  //left,right,down,up
	private String initDirection;

	public boolean[][] blocks;
	private GridSpace grid;

	private double playerInitialRow;
	private double playerInitialCol;

	private double playerCurrentRow;
	private double playerCurrentColumn;
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
	public TronPlayer2(BufferedImage image,double initialColPosition,double initialRowPosition,GridSpace gridSpace,int playerImageWidth, String initialDirection){
		super(image,initialColPosition*playerImageWidth,initialRowPosition*playerImageWidth);

		playerInitialRow = initialRowPosition;
		playerInitialCol = initialColPosition;

		direction=initialDirection;
		initDirection = initialDirection;

		this.playerImageWidth=playerImageWidth;
		playerCurrentRow=initialRowPosition;
		playerCurrentColumn=initialColPosition;
		blocks=new boolean[(int)gridSpace.getTotalRow()+2][(int)gridSpace.getTotalColumn()+2];
		speedUp=1;
		score = 0;
		grid = gridSpace;

	}


	public void resetPosition(){

		setPlayerRowandCol(playerInitialRow,playerInitialCol);
		direction = initDirection;

		blocks = new boolean[(int)grid.getTotalRow()+2][(int)grid.getTotalColumn()+2];

	}
	/**
	 * mark the block if it is filled by items in the game 
	 * @param row
	 * @param col
	 * 
	 */
	public void fillBlock(double row,double col){
		blocks[(int)row][(int)col]=true;
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
	public void setPlayerRowandCol(double currentRow,double currentColumn){
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
			return getPlayerXPosition();
		}
		else if(direction.equals("right")){
			for(int i=0;i<speedUp;i++){
				setPlayerColumn(1);
				fillBlock(playerCurrentRow,playerCurrentColumn);

			}
			return getPlayerXPosition();
		}
		else if(direction.equals("down")){
			return getPlayerXPosition();
		}
		else{
			return getPlayerXPosition();
		}
	}
	/**
	 * routinely update the Y-coordinate for the player (keep going in the same Y-direction)
	 * @return
	 */
	public double playerYDirectionMove(){
		if(direction.equals("left")){
			return getPlayerYPosition();
		}
		else if(direction.equals("right")){
			return getPlayerYPosition();
		}
		else if(direction.equals("down")){
			for(int i=0;i<speedUp;i++){
				setPlayerRow(1);
				fillBlock(playerCurrentRow,playerCurrentColumn);

			}
			return getPlayerYPosition();
		}
		else{ //up
			for(int i=0;i<speedUp;i++){
				setPlayerRow(-1);
				if(playerInbound())
					fillBlock(playerCurrentRow,playerCurrentColumn);

			}
			return getPlayerYPosition();
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
}
