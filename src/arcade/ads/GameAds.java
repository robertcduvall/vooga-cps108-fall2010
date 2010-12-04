package arcade.ads;


public abstract class GameAds extends BasicAds {

	private Game game;

	public GameAds(String name, Game game) {
		super(name);
		this.game = game;
	}

	public GameAds(String name, int xMin, int xMax, int yMin,
			int yMax) {
		super(name, xMin, xMax, yMin, yMax);
		this.name = name;

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
