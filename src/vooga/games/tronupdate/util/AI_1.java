package vooga.games.tronupdate.util;

import vooga.games.tronupdate.items.Player;
import java.util.ArrayList;

/**
 * Implements the Chase Strategy of the tronbot: keeps chasing after the
 * opponent and force it to make mistakes
 * 
 * @author Jiaqi Yan
 * 
 */
public class AI_1 extends AI {

	public AI_1(Player player) {
		super(player);
	}

	@Override
	public void update(Grid[][] grid, int row, int col, Direction direction,
			int oRow, int oCol, Direction oDirection) {
		int rowLength = grid.length;
		int colLength = grid[0].length;

		boolean[][] blocksTaken = new boolean[rowLength][colLength];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				blocksTaken[i][j] = grid[i][j].isTaken();
			}
		}
		react(row, col, direction, blocksTaken, oRow, oCol, oDirection);
	}

	/**
	 * Chase strategy: try to chase after the enemy and force it to make
	 * mistakes
	 * 
	 * @param row
	 * @param col
	 * @param direction
	 * @param blocksTaken
	 */
	private void react(int row, int col, Direction direction,
			boolean[][] blocksTaken, int oRow, int oCol, Direction oDirection) {

		int preventRange = 2;
		int rowDist = oRow - row;
		int colDist = oCol - col;

		boolean obstacleLeft = false;
		boolean obstacleRight = false;
		boolean obstacleUp = false;
		boolean obstacleDown = false;

		for (int i = 1; i <= preventRange; i++) {
			if (row - i < 0) {
				obstacleUp = true;
				break;
			}
			if (blocksTaken[row - i][col]) {
				obstacleUp = true;
				break;
			}
		}
		for (int i = 1; i <= preventRange; i++) {
			if (row + i >= blocksTaken.length) {
				obstacleDown = true;
				break;
			}
			if (blocksTaken[row + i][col]) {
				obstacleDown = true;
				break;
			}
		}
		for (int i = 1; i <= preventRange; i++) {
			if (col - i < 0) {
				obstacleLeft = true;
				break;
			}
			if (blocksTaken[row][col - i]) {
				obstacleLeft = true;
				break;
			}
		}
		for (int i = 1; i <= preventRange; i++) {
			if (col + i >= blocksTaken[0].length) {
				obstacleRight = true;
				break;
			}
			if (blocksTaken[row][col + i]) {
				obstacleRight = true;
				break;
			}
		}
		if (direction.equals(Direction.left))
			obstacleRight = false;
		if (direction.equals(Direction.down))
			obstacleUp = false;
		if (direction.equals(Direction.up))
			obstacleDown = false;
		if (direction.equals(Direction.right))
			obstacleLeft = false;

		if (direction.equals(Direction.up)) {
			if (obstacleUp && obstacleLeft) {
				right();
				return;
			}
			if (obstacleUp && obstacleRight) {
				left();
				return;
			}
		}

		if (direction.equals(Direction.up)) {
			if (obstacleUp && obstacleLeft) {
				right();
				return;
			}
			if (obstacleUp && obstacleRight) {
				left();
				return;
			}
		}
		if (direction.equals(Direction.down)) {
			if (obstacleDown && obstacleLeft) {
				right();
				return;
			}
			if (obstacleDown && obstacleRight) {
				left();
				return;
			}
		}
		if (direction.equals(Direction.left)) {
			if (obstacleLeft && obstacleUp) {
				down();
				return;
			}
			if (obstacleLeft && obstacleDown) {
				up();
				return;
			}
		}
		if (direction.equals(Direction.right)) {
			if (obstacleRight && obstacleUp) {
				down();
				return;
			}
			if (obstacleRight && obstacleDown) {
				up();
				return;
			}
		}

		if (Math.max(Math.abs(rowDist), Math.abs(colDist)) == rowDist) {
			if (rowDist > 0) {
				if (direction.equals(Direction.up)) {
					if (colDist > 0)
						direction = Direction.right;
					else
						direction = Direction.left;
				} else {
					direction = Direction.down;
				}
			} else {
				if (direction.equals(Direction.down)) {
					if (colDist > 0)
						direction = Direction.right;
					else
						direction = Direction.left;
				} else
					direction = Direction.up;
			}
		} else {
			if (colDist > 0) {
				if (direction.equals(Direction.left)) {
					if (rowDist > 0)
						direction = Direction.down;
					else
						direction = Direction.up;
				} else
					direction = Direction.right;
			} else {
				if (direction.equals(Direction.right)) {
					if (rowDist > 0)
						direction = Direction.down;
					else
						direction = Direction.up;
				} else
					direction = Direction.left;
			}
		}

		if (direction.equals(Direction.up)) {
			if (obstacleUp) {
				if (obstacleRight)
					left();
				if (obstacleLeft)
					right();
				left();
			} else
				up();
		} else if (direction.equals(Direction.down)) {
			if (obstacleDown) {
				if (obstacleRight)
					left();
				if (obstacleLeft)
					right();
				left();
			} else
				down();
		} else if (direction.equals(Direction.left)) {
			if (obstacleLeft) {
				if (obstacleUp)
					down();
				if (obstacleDown)
					up();
				down();
			} else
				left();
		} else {
			if (obstacleRight) {
				if (obstacleUp)
					down();
				if (obstacleDown)
					up();
				down();
			} else
				right();
		}
	}

}
