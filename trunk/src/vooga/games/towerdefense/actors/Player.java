package vooga.games.towerdefense.actors;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.actors.towers.Normal;
import vooga.games.towerdefense.actors.towers.Tower;

/**
 * Represents the player in the game. The player follows 
 * the cursor and triggers a build tower event whenever 
 * the player clicks. The image displayed is based on the 
 * preview image of whatever tower the current tower is.
 * 
 * @author Daniel Koverman
 *
 */
public class Player extends BetterSprite {


	private static final long serialVersionUID = 1L;
	private static final Tower DEFAULT_TOWER = new Normal(0,0);
	private Tower currentTower;
	private BuildTowerEvent buildTowerEvent;

	public Player(BufferedImage image, double x, double y, BuildTowerEvent buildTower) {
		super(image, x, y);
		changeTowerType(DEFAULT_TOWER);
	}

	public void move() {
		forceX(Resources.getGame().bsInput.getMouseX());
		forceY(Resources.getGame().bsInput.getMouseY());
	}

	public void onClick() {
		System.out.println("Building Tower");
		buildTower();
	}

	private void buildTower() {
		currentTower.forceX(getX());
		currentTower.forceY(getY());
		buildTowerEvent.addTower(currentTower);
		changeTowerType(currentTower.clone());
	}
	
	public void changeTowerType(Tower newTower){
		currentTower = newTower;
		setImage(currentTower.getPreviewImage());
	}
}
