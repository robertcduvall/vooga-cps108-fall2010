package vooga.games.mariogame.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

/**
 * Rule that applies gravity to the sprite groups listed in the level.xml document
 * 
 * @author Cameron McCallie
 *
 */
public class Gravity implements Rule{
	
	private static final double GRAVITY_CONSTANT = 0.05;
	private static double GRAVITY_COEF = 1;

	public Gravity(){
		
	}
	
	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group : groups){
			applyGravity(group);
		}
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}
	
	private void applyGravity(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setVerticalSpeed(s.getVerticalSpeed() + GRAVITY_CONSTANT * GRAVITY_COEF);
		}	
	}
	
	public static void setGravityCoef(double coef) {
		GRAVITY_COEF = coef;
	}

}
