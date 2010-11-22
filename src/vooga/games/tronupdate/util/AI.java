package vooga.games.tronupdate.util;

import java.util.*;

import vooga.games.tronupdate.items.TronPlayer;
import vooga.engine.control.Control;
import vooga.engine.core.Game;

public class AI{
	private int preventRange = 10;
	private int counter = 0;
	private Game game;
	
	int leftRightCounter,upDownCounter;
	String leftRightDirection, upDownDirection;
	
	//temporary implementation
	private TronPlayer myPlayer;
	
	public void setPlayer(TronPlayer player){
		myPlayer = player;
	}
	
	//public void aiUpdate(List<TronPlayer> tronPlayerList,boolean[][] levelBlocks){
	public void aiUpdate(boolean[][] blocks){
		//counter++;
		//int row = myPlayer.blocks.length;
		//int col = myPlayer.blocks[0].length;
		
		int row = blocks.length;
		int col = blocks[0].length;
		
		boolean[][] blocksTaken = new boolean[row][col];
		blocksTaken = blocks;
		
		/*for(int i=0;i<blocksTaken.length;i++){
			Arrays.fill(blocksTaken[i],false);
		}
		
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(firstBlocks[i][j]) blocksTaken[i][j]=true;
			}
		}
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(secondBlocks[i][j]) blocksTaken[i][j]=true;
			}
		}*/
		
		/*for(TronPlayer player: tronPlayerList){
			for(int i=0;i<row;i++){
				for(int j=0;j<col;j++){
					if(player.blocks[i][j]) blocksTaken[i][j]=true;
				}
			}
		}*/
		Direction direction = myPlayer.getDirection();
		
		int currentRow  = (int) myPlayer.getPlayerRow();
		int currentCol = (int) myPlayer.getPlayerColumn();
		//if(counter==5){
			react(currentRow,currentCol,direction,blocksTaken);
			//counter = 0;
		//}
	}
	
	public void react(int row,int col, Direction direction, boolean[][] blocksTaken){
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
	/**
	 * performs down turning
	 */
	//@Override
	public void down() {
		myPlayer.down();		
	}
	//@Override
	/**
	 * performs left turning 
	 */
	public void left() {
		myPlayer.left();
	}
	//@Override
	/**
	 * performs right turning
	 */
	public void right() {
		myPlayer.right();
	}
	//@Override
	/**
	 * performs up turning
	 */
	public void up() {
		myPlayer.up();
	}
	
}
