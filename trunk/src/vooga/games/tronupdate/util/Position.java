package vooga.games.tronupdate.util;
//what is this used for?
public class Position {
	private int XPos;
	private int YPos;
	public Position(int x,int y){
		XPos = x; YPos = y;
	}
	public int getX(){
		return XPos;
	}
	public int getY(){
		return YPos;
	}
	public void setX(int x){
		XPos = x;
	}
	public void setY(int y){
		YPos = y;
	}
}
