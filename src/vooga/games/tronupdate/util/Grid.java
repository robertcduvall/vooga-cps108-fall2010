package vooga.games.tronupdate.util;

import java.util.*;

public class Grid {
	private boolean isTaken,isBonus,isWall;
	private Set<Integer> players = new HashSet<Integer>();
	private int wallIndex = 100;
	public void setPlayer(int player){
		players.add(player);
	}
	//public int getPlayer(){
	//	return players;
	//}
	public boolean collides(){
		return (players.size()>1);
	}
	public void setTaken(boolean taken){
		isTaken = taken;
	}
	public boolean isTaken(){
		return isTaken;
	}
	public void setWall(boolean wall){
		isWall = wall;
		if(wall) players.add(wallIndex);
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
