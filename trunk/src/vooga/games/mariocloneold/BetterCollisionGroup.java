package vooga.games.mariocloneold;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * 
 * @author Andrew Brown, David Herzka, and Cameron McCallie
 * 
 *         This class is intended to fix issues AdvanceCollisionGroup has with
 *         detecting collision side and reverting sprites' positions.
 * 
 */
public abstract class BetterCollisionGroup extends AdvanceCollisionGroup {

	public BetterCollisionGroup() {
		pixelPerfectCollision = true;
	}

	/**
	 * Gets the sides between which a collision occurred. i.e., top of sprite 1
	 * to bottom of sprite 2
	 * 
	 * @param s1
	 *            first sprite
	 * @param s2
	 *            second sprite
	 * @return side of collision
	 */
	public int getCollisionSide(Sprite s1, Sprite s2) {

		if (s1.getHorizontalSpeed() < 0
				&& (s1.getX() >= s2.getX() || s1.getVerticalSpeed() == 0))
			return LEFT_RIGHT_COLLISION;
		if (s1.getHorizontalSpeed() > 0
				&& (s1.getX() <= s2.getX() || s1.getVerticalSpeed() == 0))
			return RIGHT_LEFT_COLLISION;
		if (s1.getVerticalSpeed() > 0
				&& (s1.getY() <= s2.getY() || s1.getHorizontalSpeed() == 0))
			return BOTTOM_TOP_COLLISION;
		if (s1.getVerticalSpeed() < 0
				&& (s1.getY() >= s2.getY() || s1.getHorizontalSpeed() == 0))
			return TOP_BOTTOM_COLLISION;
		return Integer.MAX_VALUE;
	}

	/**
	 * Moves the first sprite to an appropriate position based on the collision
	 * side.
	 * 
	 * @param s1
	 *            first sprite
	 * @param s2
	 *            second sprite
	 */
	public void revertPosition1(Sprite s1, Sprite s2) {
		switch (getCollisionSide(s1, s2)) {
		case (LEFT_RIGHT_COLLISION):
			s1.setX(s2.getX() + s2.getWidth());
			break;
		case (RIGHT_LEFT_COLLISION):
			s1.setX(s2.getX() - s1.getWidth() + 1);
			break;
		case (TOP_BOTTOM_COLLISION):
			s1.setY(s2.getY() + s2.getHeight());
			break;
		case (BOTTOM_TOP_COLLISION):
			s1.setY(s2.getY() - s1.getHeight() + 2);
			break;
		}
	}

	/**
	 * Prints the collision side in the output stream.
	 * 
	 * @param s1
	 *            first sprite
	 * @param s2
	 *            second sprite
	 */
	public void printCollisionSide(Sprite s1, Sprite s2) {
		String sideString = "";
		switch (getCollisionSide(s1, s2)) {
		case (LEFT_RIGHT_COLLISION):
			sideString = "Left<->Right";
			break;
		case (RIGHT_LEFT_COLLISION):
			sideString = "Right<->Left";
			break;
		case (TOP_BOTTOM_COLLISION):
			sideString = "Top<->Bottom";
			break;
		case (BOTTOM_TOP_COLLISION):
			sideString = "Bottom<->Top";
			break;
		case (Integer.MAX_VALUE):
			sideString = "Can't tell";
			break;
		}
		System.out.println(String.format("Collision Side -> %s", sideString));
	}

}
