package vooga.games.mariogame;

import java.awt.Graphics2D;
import java.util.List;

import vooga.engine.overlay.OverlayTracker;
import vooga.games.mariogame.tiles.Tile;
import vooga.engine.core.PlayField;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class MarioPlayField extends PlayField {
	private OverlayTracker myOverlays;
	private double mySpawnX;
	private double mySpawnY;
	private String myMusic;

	public MarioPlayField() {
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

	/*
	private void updateItems() {
		List<Sprite> itemList = myTileMap.getNewItems();
		for (Sprite i : itemList) {
			getGroup("Item Group").add(i);
		}
	}
*/
	@Override
	public void update(long elapsedTime) {
		//updateItems();
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
		myOverlays.getStat("level").setStat(level);
	}

	@SuppressWarnings("unchecked")
	private void updateStats() {
		myOverlays.getStat("health").setStat(getMario().getHealth()); // update
		// lives
		myOverlays.getStat("score").setStat(getMario().getScore()); // update
		// score
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	public void addOverlays(OverlayTracker overlayTracker) {
		myOverlays = overlayTracker;
		addGroup(myOverlays.getOverlayGroup("levelOverlays"));
	}

	public OverlayTracker getOverlays() {
		return myOverlays;
	}

	public boolean isFinished() {
		//int backgroundWidth = myTileMap.width * myTileMap.TILE_SIZE;
		//fix this later
		int backgroundWidth = 1000;
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
