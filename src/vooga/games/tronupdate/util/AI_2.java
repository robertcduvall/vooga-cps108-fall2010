package vooga.games.tronupdate.util;

import vooga.games.tronupdate.items.Player;
/**
 * Implements a basic avoiding strategy. Make turns when enemy or block is detected ahea
 * @author Jiaqi Yan
 *
 */
public class AI_2 extends AI {

	public AI_2(Player player) {
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

		int preventRange = 2;
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
