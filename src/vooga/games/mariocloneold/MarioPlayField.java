package vooga.games.mariogame;

import java.awt.Graphics2D;
import java.util.List;

import vooga.engine.factory.MapTile;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.player.control.ItemSprite;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

public class MarioPlayField extends PlayField {
	private TileMap myTileMap;
	private OverlayTracker myOverlays;
	private double mySpawnX;
	private double mySpawnY;
	private String myMusic;

	public MarioPlayField() {
		addGroup(new SpriteGroup("Item Group"));
		addGroup(new SpriteGroup("Mario Group"));
		addGroup(new SpriteGroup("Enemy Group"));
		addCollisionGroup(getGroup("Mario Group"), getGroup("Item Group"),
				new MarioToItemCollision());
		addCollisionGroup(getGroup("Mario Group"), getGroup("Enemy Group"),
				new MarioToEnemyCollision());
	}

	public MarioPlayField(TileMap map) {
		this();
		addTileMap(map);
	}

	private void scrollLevel() {
		getBackground().setToCenter(getMario());
	}

	public MarioSprite getMario() {
		return (MarioSprite) getGroup("Mario Group").getActiveSprite();
	}

	public void setMario(MarioSprite mario) {
		getGroup("Mario Group").remove(0);
		getGroup("Mario Group").add(mario);
	}

	public void addTileMap(TileMap tileMap) {
		myTileMap = tileMap;
		addGroup(tileMap.getTileGroup());
		addCollisionGroup(getGroup("Mario Group"), myTileMap.getTileGroup(),
				new MarioToTileCollision());
		addCollisionGroup(getGroup("Enemy Group"), myTileMap.getTileGroup(),
				new EnemyToTileCollision());
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
		if (getMario().getX() > getMario().getMaxX()) {
			scrollLevel();
		}
		getGroup("Enemy Group").removeInactiveSprites();
		updateStats();
		if (getMario().isKilled())
			respawnMario();
		super.update(elapsedTime);
	}

	private void respawnMario() {
		getMario().setLocation(mySpawnX, mySpawnY);
		scrollLevel();
		getMario().getBackground().setLocation(0, 0);
		getMario().setKilled(false);
		getMario().setMaxX(0);
		getMario().setGravityCoef(1);
	}

	@SuppressWarnings("unchecked")
	public void setLevel(int level) {
		myOverlays.getStats().get(2).setStat(level);
	}

	@SuppressWarnings("unchecked")
	private void updateStats() {
		myOverlays.getStats().get(0).setStat(getMario().getHealth()); // update
		// lives
		myOverlays.getStats().get(1).setStat(getMario().getScore()); // update
		// score
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		for (MapTile t : myTileMap.getTiles()) {
			t.render(g);
		}
	}

	public void addOverlays(OverlayTracker overlayTracker) {
		myOverlays = overlayTracker;
		for (SpriteGroup g : myOverlays.getOverlayGroups())
			addGroup(g);
	}

	public OverlayTracker getOverlays() {
		return myOverlays;
	}

	public boolean isFinished() {
		int backgroundWidth = myTileMap.width * myTileMap.TILE_SIZE;
		return getMario().getX() > backgroundWidth - 200;
	}

	public void setSpawnLocation(double x, double y) {
		this.mySpawnX = x;
		this.mySpawnY = y;
	}

	public String getMusic() {
		return myMusic;
	}

	public void setMusic(String music) {
		this.myMusic = music;
	}

}
