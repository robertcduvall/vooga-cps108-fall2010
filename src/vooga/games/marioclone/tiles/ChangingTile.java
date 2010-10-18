package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.golden.gamedev.object.Sprite;

import vooga.engine.collision.Collidable;

@SuppressWarnings("serial")
public class ChangingTile extends Tile {

	private List<BufferedImage> images = new ArrayList<BufferedImage>();
	private int curImage;
	private boolean locked;

	public ChangingTile(double x, double y, BufferedImage image) {
		super(image, x, y);
		this.addImage(image);
	}

	public ChangingTile(double x, double y, List<BufferedImage> images) {
		super(images.get(0), x, y);
		this.images = images;
	}

	public void addImage(BufferedImage image) {
		images.add(image);
	}

	public void addImages(Collection<BufferedImage> images) {
		this.images.addAll(images);
	}

	protected void lock() {
		locked = true;
	}

	protected void unlock() {
		locked = false;
	}
	
	protected boolean isLocked() {
		return locked;
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		if (!locked) {
			curImage = (curImage + 1) % images.size();
			setImage(images.get(curImage));
		}
	}

}
