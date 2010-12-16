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
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith 
 *
 */
public class Player extends MouseFollower {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH_BOUNDS = Resources.getInt("playFieldWidth");
	private static final int HEIGHT_BOUNDS = Resources.getInt("playFieldHeight");
	private static final int PATH_WIDTH = Resources.getInt("pathWidth");
	private final int MONEY_CHEAT = 1000000;
	private Tower currentTower;
	private BuildTowerEvent buildTowerEvent;
	private FindTargetEvent findTargetEvent;
	private ShootEvent shootEvent;
	private Stat<Integer> balance;
	private Stat<Integer> score;
	private Stat<Integer> selfEsteem;
	private List<PathPoint> pathPoints;
	private BufferedImage image;

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
	
	public void update(long elapsedTime){
		if(!crossBorder() && this.getImage()!=null)
		{
			this.setImage(null);
		}else if(crossBorder() && this.getImage()==null)
		{
			this.setImage(currentTower.getPreviewImage());
		}
		
		super.update(elapsedTime);
	}
	
	public void onClick() {
		buildTower();
	}
	
	public void cheatOn() {
		balance.setStat(MONEY_CHEAT);
	}

	private void buildTower() {
		if(hasMoneyToBuild() && withinBounds() && checkOnPath())
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
		return getY()+getHeight()/2-currentTower.getHeight()/2;
	}
	
	public boolean withinBounds(){
		return getOffsetX()<WIDTH_BOUNDS && getOffsetY()<HEIGHT_BOUNDS && getOffsetX()>0 && getOffsetY()>0;
	}
	
	public boolean crossBorder(){
		if((WIDTH_BOUNDS)>(getOffsetX()) - currentTower.getWidth()/2)
		{
			return true;
		}
		return false;
	}
		
	public boolean checkOnPath(){
		for(PathPoint p: pathPoints)
		{
			if(onPathPoints(p.getX(), p.getY()))
			{
				return false;
			}
		}
		return true;
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
		if((x+PATH_WIDTH)>getOffsetX() && getOffsetX()>(x-PATH_WIDTH))
		{
			if((y+PATH_WIDTH)>getOffsetY() && getOffsetY()>(y-PATH_WIDTH))
			{
				return true;
			}
		}
		return false;
	}
}
