package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.games.mariogame.sprites.MarioSprite;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class ItemTile extends ChangingTile {

	protected BetterSprite myItem;
	protected boolean releaseItem;

	public ItemTile(double x, double y, BetterSprite item, BufferedImage... images) {
		super(x, y, images);
		myItem = item;
	}

	public ItemTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		if (!isLocked()) {
			super.actOnCollision(sprite);
			((MarioSprite) sprite).incScore(10);
			releaseItem = true;
			lock();
		}
	}

	@Override
	public Sprite checkItem() {
		if (releaseItem) {
			releaseItem = false;
			return myItem;
		}
		return null;
	}

}
