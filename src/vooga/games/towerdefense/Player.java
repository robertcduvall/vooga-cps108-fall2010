package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;

public class Player extends Sprite {


	private static final long serialVersionUID = 1L;

	public Player(BufferedImage image, double x, double y) {
		super(image, x, y);
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
}
