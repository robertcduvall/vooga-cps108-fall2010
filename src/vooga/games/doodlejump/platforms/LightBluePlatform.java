package vooga.games.doodlejump.platforms;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

/**
 * The LightBluePlatform class extends Sprite and defines a LightBluePlatform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class LightBluePlatform extends BetterSprite {
	
	public LightBluePlatform(){
		this(Resources.getImage("lightBluePlatform"));
	}
	public LightBluePlatform(BufferedImage image) {
		super(image);
	}

	@Override
	public void firstRun(){
		setHorizontalSpeed(-0.1);
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getX() < Resources.getDouble("minScreenX") || getX() > Resources.getDouble("maxScreenX") - getWidth())
			setHorizontalSpeed(getHorizontalSpeed() * -1);
	}
}
