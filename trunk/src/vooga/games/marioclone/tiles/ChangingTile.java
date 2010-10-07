package vooga.games.marioclone.tiles;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.collision.Collidable;

public class ChangingTile extends Tile {
	
	private List<Image> images;
	private int curImage;
	
	public ChangingTile(double x, double y, Image image) {
		super(x,y);
		init();
		this.addImage(image);
	}
	
	public ChangingTile(double x, double y, List<Image> images) {
		super(x,y);
		init();
		this.images = images;
	}

	private void init() {
		images = new ArrayList<Image>();
	}
	
	public void addImage(Image image) {
		images.add(image);
	}
	
	public void addImages(List<Image> images) {
		this.images.addAll(images);
	}

	@Override
	public void actOnCollision(Collidable object) {
		curImage = (curImage+1)%images.size();
	}

	@Override
	public Image getImage() {
		return images.get(curImage);
	}

}
