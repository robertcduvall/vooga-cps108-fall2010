package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;

/**
 * The JigglingMonster class extends Sprite and defines a JigglingMonster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class JigglingMonster extends BetterSprite {
	private int jiggleTimer;
	private static final String[] monsterNames = {"greenMonster", "bigBlueMonster", "redMonster", "purpleMonster"};
	
	public JigglingMonster(){
		this(Resources.getImage(monsterNames[(int) (Math.random() * 4)]));
	}

	public JigglingMonster(BufferedImage image) {
		super(image);
	}

	@Override
	public void firstRun(){
		jiggleTimer = Resources.getInt("jiggleTime");
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jiggleTimer--;
		if (jiggleTimer < 0) {
			setHorizontalSpeed(getHorizontalSpeed() * -1);
			jiggleTimer = Resources.getInt("jiggleTime");
		}
	}
}
