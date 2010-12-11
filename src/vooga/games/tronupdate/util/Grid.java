package vooga.games.tronupdate.util;

import java.util.*;
import vooga.engine.resource.Resources;

public class Grid {
	private boolean isTaken,isBonus,isWall,isPlayer;
	private int bonusType;
	private Stack<Integer> players = new Stack<Integer>();
	public void setPlayer(int player){
		players.add(player);
		isPlayer = true;
	}
	//public int getPlayer(){ 
	//	return players;
	//}
	public boolean collides(){
		return (players.size()>1) || (isWall && isPlayer);
	}
	public int getCollidePlayer(){
		return players.peek();
	}
	public void setTaken(boolean taken){
		isTaken = taken;
	}
	public boolean isTaken(){
		return isTaken;
	}
	public void setWall(boolean wall){
		isWall = wall;
	}
	public boolean isWall(){
		return isWall;
	}
	public void setBonus(boolean bonus){
		isBonus = bonus;
	}
	public boolean isBonus(){
		return isBonus;
	}
	
}
