package vooga.games.marioclone;

import java.awt.Graphics2D;

import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.PlayField;

public class MarioPlayField extends PlayField {
	private TileMap myTileMap;
	
	public void addTileMap(TileMap tileMap) {
		myTileMap = tileMap;
	}
	
	public TileMap getTileMap() {
		return myTileMap;
	}
	
	@Override
	public void update(long elapsedTime) {
		myTileMap.updateTiles();
		super.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		for(Tile t : myTileMap.getTiles()) {
			t.render(g);
		}
	}
}
