package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import vooga.engine.factory.MapTile;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class ChangingTile extends MapTile {

	private List<BufferedImage> images = new ArrayList<BufferedImage>();
	private int curImage;
	private boolean locked;

	public ChangingTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
		this.images.addAll(Arrays.asList(images));
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

	public boolean isLocked() {
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
