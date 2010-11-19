package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;
import java.util.List;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class ItemTile extends ChangingTile {

	protected Sprite myItem;
	protected boolean releaseItem;

	public ItemTile(double x, double y, List<BufferedImage> images,
			Sprite item) {
		super(x, y, images);
		myItem = item;
	}

	public ItemTile(BufferedImage image, double x, double y) {
		super(image,x,y);
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
			releaseItem = false;
			return myItem;
		}
		return null;
	}
	

}
