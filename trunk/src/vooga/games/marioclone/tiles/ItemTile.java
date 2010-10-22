package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;
import java.util.List;

import vooga.engine.player.control.ItemSprite;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class ItemTile extends ChangingTile {

	private ItemSprite myItem;
	private boolean releaseItem;

	public ItemTile(double x, double y, List<BufferedImage> images,
			ItemSprite item) {
		super(x, y, images);
		myItem = item;
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		if (!isLocked()) {
			super.actOnCollision(sprite);
			releaseItem = true;
			lock();
		}
	}

	@Override
	public ItemSprite checkItem() {
		if (releaseItem) {
			releaseItem = false;
			return myItem;
		}
		return null;
	}

}
