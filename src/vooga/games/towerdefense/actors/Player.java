package vooga.games.towerdefense.actors;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.actors.towers.Normal;

public class Player extends BetterSprite {


	private static final long serialVersionUID = 1L;
	private Tower currentTower;
	private BuildTowerEvent buildTowerEvent;

	public Player(BufferedImage image, double x, double y, BuildTowerEvent buildTower) {
		super(image, x, y);
		changeTowerType(new Normal(0,0));
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
		
		//TODO add something real
		buildTowerEvent.addTower(currentTower);
		changeTowerType(currentTower.clone());

//		if (creditBalance.getStat() >= currentTower.getCost() && offPath()
//				&& inPlayArea()) {
//			SingletonEventManager.fireEvent("BuildTowerEvent",
//					new BuildTowerEvent(this, "BuildTowerEvent", currentTower,
//							getX(), getY()));
//			creditBalance.setStat(creditBalance.getStat()
//					- currentTower.getCost());
//			changeTowerType(currentTower.clone());
//
//		}
	}
	
	public void changeTowerType(Tower newTower){
		currentTower = newTower;
		setImage(currentTower.getPreviewImage());
	}
}
