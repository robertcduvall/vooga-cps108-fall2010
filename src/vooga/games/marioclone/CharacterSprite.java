package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.object.Sprite;

/*
 * @author Andrew Brown
 */

@SuppressWarnings("serial")
public abstract class CharacterSprite extends PlayerSprite {

	double gravity = .0025 ;
	
	BufferedImage rightImage;
	BufferedImage leftImage;

	public CharacterSprite(String name, String stateName, BufferedImage left, BufferedImage right) {
		super(name, stateName, new Sprite(right));
		leftImage = left;
		rightImage = right;
		addStat("Health", new Stat<Integer>(getMaxHealth()));
	}
	
	public abstract Integer getMaxHealth();
	
	public void setHealth(Integer health) {
		((Stat<Integer>) getStat("Health")).setStat(health);
	}
	
	public Integer getHealth() {
		return ((Stat<Integer>) getStat("Health")).getStat();
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		setVerticalSpeed(getVerticalSpeed() + gravity * elapsedTime);

		/*
		 * double yVelocity = getVerticalSpeed(); double newYVelocity =
		 * yVelocity + gravity*elapsedTime; setVerticalSpeed(newYVelocity);
		 */

	}

}
