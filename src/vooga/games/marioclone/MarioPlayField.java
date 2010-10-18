package vooga.games.marioclone;

import java.awt.Graphics2D;
import java.util.List;

import vooga.engine.player.control.ItemSprite;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

public class MarioPlayField extends PlayField {
	private TileMap myTileMap;

	public MarioPlayField(TileMap map) {
		myTileMap = map;
		addGroup(new SpriteGroup("Item Group"));
		addGroup(new SpriteGroup("Mario Group"));
		addGroup(new SpriteGroup("Enemy Group"));
		addCollisionGroup(getGroup("Mario Group"), getGroup("Item Group"),
				new MarioToItemCollision());
		addCollisionGroup(getGroup("Mario Group"), myTileMap.getTileGroup(),
				new MarioToTileCollision());
		addCollisionGroup(getGroup("Enemy Group"), myTileMap.getTileGroup(),
				new EnemyToTileCollision());
		addCollisionGroup(getGroup("Mario Group"), getGroup("Enemy Group"),
				new MarioToEnemyCollision());
	}


	public void addTileMap(TileMap tileMap) {
		myTileMap = tileMap;
	}

	public TileMap getTileMap() {
		return myTileMap;
	}

	private void updateItems() {
		List<ItemSprite> itemList = myTileMap.getNewItems();
		for (ItemSprite i : itemList) {
			getGroup("Item Group").add(i);
		}
	}

	@Override
	public void update(long elapsedTime) {
		myTileMap.updateTiles();
		updateItems();
		super.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		for (Tile t : myTileMap.getTiles()) {
			t.render(g);
		}
	}
}
