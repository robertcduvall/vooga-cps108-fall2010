package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;
import vooga.games.tronupdate.util.Direction;
import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.event.EventPool;
import vooga.games.tronupdate.util.AI_0;
import vooga.games.tronupdate.util.Grid;

public class Player {
	private Direction Dir;
	private int Row, Col;
	private int height, width;

	private boolean isAI;
	private AI_0 ai;
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
		ai = new AI_0();
	}

	public void setControl(Control c) {
		control = c;
	}

	public void setAsAI(boolean AI) {
		isAI = AI;
	}
	public boolean isAI() {
		return isAI;
	}
	public void setAI(AI_0 ai_){
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

	public void update(Grid[][] grid) {
		if(isAI()){
			ai.update(grid,Row,Col);
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
		System.out.println("Being called");
		updateX(Direction.left);
		updateY(Direction.left);
	}

	public void right() {
		System.out.println("Being called");
		updateX(Direction.right);
		updateY(Direction.right);
	}

	public void up() {
		System.out.println("Being called");
		updateX(Direction.up);
		updateY(Direction.up);
	}

	public void down() {
		System.out.println("Being called");
		updateX(Direction.down);
		updateY(Direction.down);
	}

}
