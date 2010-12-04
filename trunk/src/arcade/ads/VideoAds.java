package arcade.ads;

import java.awt.Image;

public abstract class VideoAds extends BasicAds {

	private Video video;

	public VideoAds(String name, Video video) {
		super(name);
		this.video = video;
	}

	public VideoAds(String name, Image img, int xMin, int xMax, int yMin,
			int yMax) {
		super(name, xMin, xMax, yMin, yMax);

	}

	@Override
	public void onClick() {
		// open a url
	}

	@Override
	public void onMouseOver() {
		// play the video
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
