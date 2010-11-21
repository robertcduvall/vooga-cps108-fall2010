package vooga.games.towerdefense.actors;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.actors.towers.Normal;
import vooga.games.towerdefense.actors.towers.Tower;
import vooga.games.towerdefense.events.BuildTowerEvent;

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
	private Stat<Integer> balance;
	private Stat<Integer> score;
	private Stat<Integer> selfEsteem;

	public Player(BufferedImage image, double x, double y, BuildTowerEvent buildTower, Stat<Integer> balance, Stat<Integer> score ,Stat<Integer> selfEsteem) {
		super(image, x, y);
		changeTowerType(DEFAULT_TOWER);
		this.balance = balance;
		this.score = score;
		this.selfEsteem = selfEsteem;
		this.buildTowerEvent = buildTower;
	}

	public void move() {
		forceX(Resources.getGame().bsInput.getMouseX());
		forceY(Resources.getGame().bsInput.getMouseY());
	}

	public void onClick() {
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
	
	public void addMoney(int money){
		balance.setStat(balance.getStat()+money);
	}
	
	public int getSelfEsteem(){
		return selfEsteem.getStat();
	}
	
	public void removeSelfEsteem(int amount){
		selfEsteem.setStat(selfEsteem.getStat()-amount);
	}
	
	public int getScore(){
		return score.getStat();		
	}
	
	public void addScore(int amount){
		score.setStat(score.getStat() + amount);
	}
}