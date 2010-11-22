package vooga.games.towerdefense.actors;

import java.awt.image.BufferedImage;

import vooga.engine.overlay.Stat;
import vooga.games.towerdefense.actors.towers.Normal;
import vooga.games.towerdefense.actors.towers.ShootingTower;
import vooga.games.towerdefense.actors.towers.Tower;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.events.FindTargetEvent;
import vooga.games.towerdefense.events.ShootEvent;
import vooga.widget.MouseFollower;

/**
 * Represents the player in the game. The player follows 
 * the cursor and triggers a build tower event whenever 
 * the player clicks. The image displayed is based on the 
 * preview image of whatever tower the current tower is.
 * 
 * @author Daniel Koverman
 *
 */
public class Player extends MouseFollower {

	private static final long serialVersionUID = 1L;
	private final int MONEY_CHEAT = 1000000;
	private Tower currentTower;
	private BuildTowerEvent buildTowerEvent;
	private FindTargetEvent findTargetEvent;
	private ShootEvent shootEvent;
	private Stat<Integer> balance;
	private Stat<Integer> score;
	private Stat<Integer> selfEsteem;

	public Player(BufferedImage image, double x, double y, BuildTowerEvent buildTower, FindTargetEvent findTarget, ShootEvent shootEvent, Stat<Integer> balance, Stat<Integer> score ,Stat<Integer> selfEsteem) {
		super(image, x, y);
		changeTowerType(new Normal(0,0, shootEvent));
		this.balance = balance;
		this.score = score;
		this.selfEsteem = selfEsteem;
		this.buildTowerEvent = buildTower;
		this.findTargetEvent = findTarget;
		this.shootEvent = shootEvent;
	}

	public void onClick() {
		buildTower();
	}
	
	public void cheatOn() {
		balance.setStat(MONEY_CHEAT);
	}

	private void buildTower() {
		if(canBuild())
		{
			setTowerLocation();
			buildTowerEvent.addTower(currentTower);
			addMoney(-(currentTower.getCost()));
			findTargetEvent.addTower((ShootingTower) currentTower);
			changeTowerType(currentTower.clone());
		}
	}
	
	private boolean canBuild(){
		return getMoney()>=currentTower.getCost();
	}
	
	private void setTowerLocation(){
		currentTower.forceX(getX()+getWidth()/2-currentTower.getWidth()/2);
		currentTower.forceY(getY()+getHeight()/2-currentTower.getWidth()/2);
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
	
	public int getMoney(){
		return balance.getStat();		
	}
	
	public void addScore(int amount){
		score.setStat(score.getStat() + amount);
	}
}
