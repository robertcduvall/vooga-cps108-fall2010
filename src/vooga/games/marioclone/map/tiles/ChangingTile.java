package vooga.games.marioclone.map.tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.collision.Collidable;

public class ChangingTile extends Tile {
	
	private List<BufferedImage> images;
	private int curImage;
	
	public ChangingTile(double x, double y, BufferedImage image) {
		super(x,y);
		init();
		this.addImage(image);
	}
	
	public ChangingTile(double x, double y, List<BufferedImage> images) {
		super(x,y);
		init();
		this.images = images;
	}
	
	private void init() {
		images = new ArrayList<BufferedImage>();
	}
	
	public void addImage(BufferedImage image) {
		images.add(image);
	}
	
	public void addImages(List<BufferedImage> images) {
		this.images.addAll(images);
	}

	@Override
	public void actOnCollision(Collidable object) {
		curImage = (curImage+1)%images.size();
	}

	@Override
	public BufferedImage getImage() {
		return images.get(curImage);
	}

}
