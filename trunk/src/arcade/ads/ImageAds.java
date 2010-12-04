package arcade.ads;

import java.awt.Image;

public abstract class ImageAds extends BasicAds {

	private Image img;
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;

	public ImageAds(String name, Image img) {
		super(name);
		this.img = img;
	}

	public ImageAds(String name, Image img, int xMin, int xMax, int yMin,
			int yMax) {
		super(name, xMin, xMax, yMin, yMax);

	}

	@Override
	public void onClick() {
		// open a url
	}

	@Override
	public void onMouseOver() {
		// highlight or scale ads

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		// render img
	}

	@Override
	public abstract boolean isActive();

}
