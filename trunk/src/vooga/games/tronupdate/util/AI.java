package vooga.games.tronupdate.util;

import java.util.*;

import vooga.games.tron.players.TronPlayer;

public class AI {
	private int preventRange = 10;
	private int counter = 0;
	
	
	int leftRightCounter,upDownCounter;
	String leftRightDirection, upDownDirection;
	
	//temporary implementation
	private TronPlayer myPlayer;
	
	public void setPlayer(TronPlayer player){
		myPlayer = player;
	}
	
	public void aiUpdate(List<TronPlayer> tronPlayerList,boolean[][] levelBlocks){
		//counter++;
		int row = myPlayer.blocks.length;
		int col = myPlayer.blocks[0].length;
		
		boolean[][] blocksTaken = new boolean[row][col];
		
		for(int i=0;i<blocksTaken.length;i++){
			Arrays.fill(blocksTaken[i],false);
		}
		
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(levelBlocks[i][j]) blocksTaken[i][j]=true;
			}
		}
		
		for(TronPlayer player: tronPlayerList){
			for(int i=0;i<row;i++){
				for(int j=0;j<col;j++){
					if(player.blocks[i][j]) blocksTaken[i][j]=true;
				}
			}
		}
		String direction = myPlayer.getDirection();
		int currentRow  = (int) myPlayer.getPlayerRow();
		int currentCol = (int) myPlayer.getPlayerColumn();
		//if(counter==5){
			react(currentRow,currentCol,direction,blocksTaken);
			//counter = 0;
		//}
	}
	
	public void react(int row,int col, String direction, boolean[][] blocksTaken){
		if(direction.equals("up")){
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
		else if(direction.equals("down")){
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
		else if(direction.equals("left")){
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
		if(!myPlayer.getDirection().equals("down")&&!myPlayer.getDirection().equals("up")){
			myPlayer.setDirection("down");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}  		
	}
	//@Override
	/**
	 * performs left turning 
	 */
	public void left() {
		if(!myPlayer.getDirection().equals("left")&&!myPlayer.getDirection().equals("right")){
			myPlayer.setDirection("left");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	//@Override
	/**
	 * performs right turning
	 */
	public void right() {
		if(!myPlayer.getDirection().equals("right")&&!myPlayer.getDirection().equals("left")){
			myPlayer.setDirection("right");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	//@Override
	/**
	 * performs up turning
	 */
	public void up() {
		if(!myPlayer.getDirection().equals("up")&&!myPlayer.getDirection().equals("down")){
			myPlayer.setDirection("up");
			myPlayer.setLocation(myPlayer.updatePlayerXPosition(myPlayer.getDirection()), myPlayer.updatePlayerYPosition(myPlayer.getDirection()));
		}
	}
	
}
