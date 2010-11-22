package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.mariogame.items.Coin;
import vooga.games.mariogame.sprites.MarioSprite;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class CoinTile extends ItemTile {
	
	public CoinTile(double x, double y, BufferedImage... images) {
		this(x, y, new Coin(Resources.getImage("Coin")),images);
	}

	public CoinTile(double x, double y, BetterSprite coin, BufferedImage... images) {
		super(x, y, coin, images);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		if (!isLocked()) {
			((Coin) myItem).spawn();
			((MarioSprite) sprite).incScore(10);
		}
		super.actOnCollision(sprite);
	}

}
