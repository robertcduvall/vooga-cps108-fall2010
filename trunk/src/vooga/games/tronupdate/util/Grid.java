package vooga.games.tronupdate.util;

public class Grid {
	private boolean isTaken,isBonus;//,isWall;
	private int playerIndex;
	public void setPlayer(int player){
		playerIndex = player;
	}
	public int getPlayer(){
		return playerIndex;
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
