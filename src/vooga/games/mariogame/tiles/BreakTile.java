package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class BreakTile extends Tile {

	public BreakTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		setState(State.removed);
	}

}
