package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;
import vooga.games.tronupdate.util.Direction;
import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.resource.Resources;

public class Player {
	private Direction Dir;
	private int Row,Col;
	
	private boolean isAI;
	private int playerImageWidth;
	private int speedUp;
	private Control control;
	
	public Player(BufferedImage image, int initialRow, int initialCol, Direction initialDirection, int imageWidth){
		Row = initialRow; Col = initialCol;
		Dir = initialDirection;
		playerImageWidth = imageWidth;
	}
	
	public void setControl(Control c){
		control = c;
	}
	
	
	public void setAI(boolean AI){
		isAI = AI;
	}
	public boolean isAI(){
		return isAI;
	}
	public int getImageWidth(){
		return playerImageWidth;
	}
	public void setDirection(Direction dir){
		Dir = dir;
	}
	public Direction getDirection(){
		return Dir;
	}
	public int getRow(){
		return Row;
	}
	public int getCol(){
		return Col;
	}
	
	public void update(){
		routinelyUpdateX();
		routinelyUpdateY();
	}
	
	private void updateX(Direction direction){
		//if(movingHorizontally() && moveVertically(direction)) return;
		if(movingVertically() && moveHorizontally(direction))	Dir = direction;
		//routinelyUpdateX();
	}
	private void updateY(Direction direction){
		//if(movingVertically() && moveHorizontally(direction)) return;
		if(movingHorizontally() && moveVertically(direction)) Dir = direction;
		//routinelyUpdateY();
	}
	private void routinelyUpdateX(){
		if(movingHorizontally()){
			Col = (Dir.equals(Direction.left))? Col-1:Col+1;	
		}
	}
	private void routinelyUpdateY(){
		if(movingVertically()){
			Row = (Dir.equals(Direction.up))? Row-1:Row+1;
		}
	}
	
	private boolean moveHorizontally(Direction direction){
		return (direction.equals(Direction.left) || direction.equals(Direction.right));
	}
	
	private boolean moveVertically(Direction direction){
		return (direction.equals(Direction.up) || direction.equals(Direction.down));
	}
	
	private boolean movingHorizontally(){
		return (Dir.equals(Direction.left) || Dir.equals(Direction.right)); 
	}
	private boolean movingVertically(){
		return (Dir.equals(Direction.up) || Dir.equals(Direction.down));
	}
	
	public void left(){
		updateX(Direction.left);
		updateY(Direction.left);
	}
	
	public void right(){
		updateX(Direction.right);
		updateY(Direction.right);
	}
	
	public void up(){
		updateX(Direction.up);
		updateY(Direction.up);
	}
	
	public void down(){
		updateX(Direction.down);
		updateY(Direction.down);
	}
	
}
