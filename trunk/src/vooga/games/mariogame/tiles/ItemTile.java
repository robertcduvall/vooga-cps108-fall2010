package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import vooga.games.mariogame.items.Item;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class ItemTile extends ChangingTile {

	protected Item myItem;
	protected boolean releaseItem;

	public ItemTile(double x, double y, Item item, BufferedImage... images) {
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
			releaseItem = true;
			lock();
		}
	}

	@Override
	public Sprite checkItem() {
		if (releaseItem) {
			myItem.spawn();
			releaseItem = false;
			return myItem;
		}
		return null;
	}

}
