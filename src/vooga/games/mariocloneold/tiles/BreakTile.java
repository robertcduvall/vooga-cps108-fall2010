package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class BreakTile extends MapTile {

	public BreakTile(double x, double y, BufferedImage image) {
		super(image, x, y);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		setState(State.removed);
	}

}
