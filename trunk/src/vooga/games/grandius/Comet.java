package vooga.games.grandius;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.games.grandius.weapons.BlackHole;

public class Comet extends BetterSprite {

	public Comet() {
		this(0,0);
	}
	
	public Comet(double x, double y) {
		super(Resources.getImage("cometImage"), x, y);
	}
}