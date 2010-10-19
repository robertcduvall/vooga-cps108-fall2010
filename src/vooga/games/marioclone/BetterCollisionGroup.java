package vooga.games.marioclone;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

public abstract class BetterCollisionGroup extends AdvanceCollisionGroup {
	
	public BetterCollisionGroup() {
		pixelPerfectCollision = true;
	}
	
	public int getCollisionSide(Sprite s1, Sprite s2) {


		if(s1.getHorizontalSpeed() < 0  && (s1.getX() >= s2.getX() || s1.getVerticalSpeed()==0))
			return LEFT_RIGHT_COLLISION;
		if(s1.getHorizontalSpeed() > 0  && (s1.getX() <= s2.getX() || s1.getVerticalSpeed()==0))
			return RIGHT_LEFT_COLLISION;
		if(s1.getVerticalSpeed() > 0  && (s1.getY() <= s2.getY() || s1.getHorizontalSpeed()==0))
			return BOTTOM_TOP_COLLISION;
		if(s1.getVerticalSpeed() < 0  && (s1.getY() >= s2.getY() || s1.getHorizontalSpeed()==0))
			return TOP_BOTTOM_COLLISION;
		return 5;
	}
	
	public void revertPosition1(Sprite s1, Sprite s2) {
		switch(getCollisionSide(s1,s2)) {
		case(LEFT_RIGHT_COLLISION):
			s1.setX(s2.getX()+s2.getWidth());
			break;
		case(RIGHT_LEFT_COLLISION):
			s1.setX(s2.getX()-s1.getWidth()+1);
			break;
		case(TOP_BOTTOM_COLLISION):
			s1.setY(s2.getY()+s2.getHeight());
			break;
		case(BOTTOM_TOP_COLLISION):
			s1.setY(s2.getY()-s1.getHeight()+2);
			break;
		}	
	}

	
	public void printCollisionSide(Sprite s1, Sprite s2) {
		String sideString = "";
		switch(getCollisionSide(s1,s2)) {
		case(LEFT_RIGHT_COLLISION):
			sideString = "Left<->Right";
			break;
		case(RIGHT_LEFT_COLLISION):
			sideString = "Right<->Left";
			break;
		case(TOP_BOTTOM_COLLISION):
			sideString = "Top<->Bottom";
			break;
		case(BOTTOM_TOP_COLLISION):
			sideString = "Bottom<->Top";
			break;
		case(5):
			sideString = "Can't tell";
			break;
		}
//		System.out.println(String.format("Collision Side -> %s",sideString));
	}

}
