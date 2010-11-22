package vooga.games.towerdefense.actors;

import java.awt.image.BufferedImage;
import java.util.List;

import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.actors.towers.Normal;
import vooga.games.towerdefense.actors.towers.ShootingTower;
import vooga.games.towerdefense.actors.towers.Tower;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.events.FindTargetEvent;
import vooga.games.towerdefense.events.ShootEvent;
import vooga.games.towerdefense.path.PathPoint;
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
	private static final int WIDTH_BOUNDS = Resources.getInt("playFieldWidth");
	private static final int HEIGHT_BOUNDS = Resources.getInt("playFieldHeight");
	private final int MONEY_CHEAT = 1000000;
	private Tower currentTower;
	private BuildTowerEvent buildTowerEvent;
	private FindTargetEvent findTargetEvent;
	private ShootEvent shootEvent;
	private Stat<Integer> balance;
	private Stat<Integer> score;
	private Stat<Integer> selfEsteem;
	private List<PathPoint> pathPoints;

	public Player(BufferedImage image, double x, double y, 
			BuildTowerEvent buildTower, FindTargetEvent findTarget, 
			ShootEvent shootEvent, Stat<Integer> balance, 
			Stat<Integer> score,Stat<Integer> selfEsteem) {
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
		if(hasMoneyToBuild() && withinBounds())
		{
			setTowerLocation();
			buildTowerEvent.addTower(currentTower);
			addMoney(-(currentTower.getCost()));
			findTargetEvent.addTower((ShootingTower) currentTower);
			changeTowerType(currentTower.clone());
		}
	}
	
	private boolean hasMoneyToBuild(){
		return getMoney()>=currentTower.getCost();
	}
	
	private void setTowerLocation(){
		currentTower.forceX(getOffsetX());
		currentTower.forceY(getOffsetY());
	}
	
	private double getOffsetX()
	{
		return getX()+getWidth()/2-currentTower.getWidth()/2;
	}
	
	private double getOffsetY()
	{
		return getY()+getHeight()/2-currentTower.getWidth()/2;
	}
	
	public boolean withinBounds(){
		
		for(PathPoint p: pathPoints)
		{
			if(onPathPoints(p.getX(), p.getY()))
			{
				return false;
			}
		}
		return getOffsetX()<WIDTH_BOUNDS && getOffsetY()<HEIGHT_BOUNDS && getOffsetX()>0 && getOffsetY()>0;
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

	public void addPathBoundary(List<PathPoint> pathPoints) {
		this.pathPoints = pathPoints;
	}
	
	private boolean onPathPoints(double x, double y) {
		if((x+5)>getOffsetX() && getOffsetX()>(x-5))
		{
			if((y+5)>getOffsetY() && getOffsetY()>(y-5))
			{
				System.out.println("Y");
				return true;
			}
		}
		return false;
	}
}
