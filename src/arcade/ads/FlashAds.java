package arcade.ads;


public abstract class FlashAds extends BasicAds {

	private Flash flash;

	public FlashAds(String name, Video video) {
		super(name);
		this.flash = flash;
	}

	public FlashAds(String name, Flash flash, int xMin, int xMax, int yMin,
			int yMax) {
		super(name, xMin, xMax, yMin, yMax);

	}

	@Override
	public void onClick() {
		// open a url
	}

	@Override
	public void onMouseOver() {
		// play the flash
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
