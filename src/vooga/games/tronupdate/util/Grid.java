package vooga.games.tronupdate.util;

import java.util.Stack;

public class Grid {
	private boolean isTaken,isBonus;//,isWall;
	private Stack<Integer> players = new Stack<Integer>();
	public void setPlayer(int player){
		players.add(player);
	}
	public int getPlayer(){
		return players.peek();
	}
	public boolean collides(){
		return (players.size()>1);
	}
	public void setTaken(boolean taken){
		isTaken = taken;
	}
	public boolean isTaken(){
		return isTaken;
	}
	
	public void setBonus(boolean bonus){
		isBonus = bonus;
	}
	public boolean isBonus(){
		return isBonus;
	}
	
}
