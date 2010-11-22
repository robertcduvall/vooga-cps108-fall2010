package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.factory.MapTile;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class BreakTile extends MapTile {

	public BreakTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		setActive(false);
	}

}
