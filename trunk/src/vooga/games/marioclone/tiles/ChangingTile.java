package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import vooga.engine.collision.Collidable;

@SuppressWarnings("serial")
public class ChangingTile extends Tile {
	
	private List<BufferedImage> images = new ArrayList<BufferedImage>();
	private int curImage;
	
	public ChangingTile(double x, double y, BufferedImage image) {
		super(image,x,y);
		this.addImage(image);
	}
	
	public ChangingTile(double x, double y, List<BufferedImage> images) {
		super(images.get(0),x,y);
		this.images = images;
	}
	
	public void addImage(BufferedImage image) {
		images.add(image);
	}
	
	public void addImages(Collection<BufferedImage> images) {
		this.images.addAll(images);
	}

	@Override
	public void actOnCollision(Collidable object) {
		curImage = (curImage+1)%images.size();
		setImage(images.get(curImage));
	}
}
