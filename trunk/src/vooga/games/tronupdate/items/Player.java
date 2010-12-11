package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;
import vooga.games.tronupdate.util.Direction;
import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.event.EventPool;
import vooga.games.tronupdate.util.AI;
import vooga.games.tronupdate.util.Grid;

public class Player {
	private Direction Dir;
	private int Row, Col;
	private int height, width;
	private int loses;

	private boolean isAI;
	private AI ai;
	private int playerImageWidth;
	private int speedUp;
	private Control control;
	private boolean withinBound;
	
	public Player(BufferedImage image, int initialRow, int initialCol,
			Direction initialDirection, int imageWidth, int height_, int width_) {
		Row = initialRow;
		Col = initialCol;
		Dir = initialDirection;
		playerImageWidth = imageWidth;
		height = height_;
		width = width_;
		control = new Control();
		loses=0;
	}

	public void setKeyboardControl(Control c) {
		control = c;
	}
	
	public void setLost(){
		loses++;
	}
	public int getLoses(){
		return loses;
	}
	
	
	public void setAsAI(boolean AI) {
		isAI = AI;
	}
	public boolean isAI() {
		return isAI;
	}
	public void setAI(AI ai_){
		ai = ai_;	
	}

	public int getImageWidth() {
		return playerImageWidth;
	}

	public void setDirection(Direction dir) {
		Dir = dir;
	}

	public Direction getDirection() {
		return Dir;
	}

	public int getRow() {
		return Row;
	}

	public int getCol() {
		return Col;
	}

	public void update(Grid[][] grid,Player[] players) {
		if(isAI()){
			//temporary implementation for only two players
			int opponentRow = 0; int opponentCol = 0; Direction opponentDirection = Direction.down;
			for(int i=0;i<players.length;i++){
				if(players[i].getRow()==Row) continue;
				opponentRow = players[i].getRow(); opponentCol = players[i].getCol(); 
				opponentDirection = players[i].getDirection();
			}
			ai.update(grid,Row,Col,Dir,opponentRow,opponentCol,opponentDirection);
		}
		else control.update();
		routinelyUpdateX();
		routinelyUpdateY();
		//checkBoundary();
	}
	
	public boolean outOfBoundary() {
		return (Row < 0 || Col < 0 || Row >= height || Col >= width);
	}

	private void updateX(Direction direction) {
		// if(movingHorizontally() && moveVertically(direction)) return;
		if (movingVertically() && moveHorizontally(direction))
			Dir = direction;
	}

	private void updateY(Direction direction) {
		// if(movingVertically() && moveHorizontally(direction)) return;
		if (movingHorizontally() && moveVertically(direction))
			Dir = direction;
	}

	private void routinelyUpdateX() {
		if (movingHorizontally()) {
			Col = (Dir.equals(Direction.left)) ? Col - 1 : Col + 1;
		}
	}

	private void routinelyUpdateY() {
		if (movingVertically()) {
			Row = (Dir.equals(Direction.up)) ? Row - 1 : Row + 1;
		}
	}

	private boolean moveHorizontally(Direction direction) {
		return (direction.equals(Direction.left) || direction
				.equals(Direction.right));
	}

	private boolean moveVertically(Direction direction) {
		return (direction.equals(Direction.up) || direction
				.equals(Direction.down));
	}

	private boolean movingHorizontally() {
		return (Dir.equals(Direction.left) || Dir.equals(Direction.right));
	}

	private boolean movingVertically() {
		return (Dir.equals(Direction.up) || Dir.equals(Direction.down));
	}

	public void left() {
		updateX(Direction.left);
		updateY(Direction.left);
	}

	public void right() {
		updateX(Direction.right);
		updateY(Direction.right);
	}

	public void up() {
		updateX(Direction.up);
		updateY(Direction.up);
	}

	public void down() {
		updateX(Direction.down);
		updateY(Direction.down);
	}

}
