package vooga.games.galaxyinvaders.sprites;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.galaxyinvaders.states.PlayState;

import com.golden.gamedev.object.Timer;

/**
 * The EnemySprite class represents each invader on the screen. The 
 * GalaxyInvaders class creates a sprite group of them.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class EnemySprite extends BetterSprite {

	private int pathNum;
    private Timer timer;
    private Timer bombTimer;
    private ArrayList<Point> path;    
	private int hitPoints;
	
	
	public EnemySprite() {
		pathNum = 0;
    	hitPoints = Resources.getInt("enemySpriteDefaultHP");
    	bombTimer = new Timer(5000);
	}
	
	/**
	 * Called every turn by the Game, this method moves the enemy, changes 
	 * its color if it is damaged, and removes it if it is destroyed.
	 */
	public void update(long time) {
		super.update(time);
		if(hitPoints < Resources.getInt("enemySpriteDefaultHP")) {
			setAsRenderedSprite("enemy1damage");
		}
		if(hitPoints <= 0) {
			setActive(false);
		}
        if (timer.action(time))   move();
        if (bombTimer.action(time))	  spawnBomb();
	}
	
    /**
     * Moves the enemy along its path
     * 
     */
    public void move(){
        if (pathNum>=path.size())
            return;
        move(path.get(pathNum).getX(), path.get(pathNum).getY());
        pathNum++;
    }
	
    
    public void setPathPointsAndTimerNum(ArrayList<Point> pathmap, int num) {
    	path = pathmap;
    	timer = new Timer(num);
    }
    
    
	/**
	 * Decreases hitPoints by a certain amount
	 * 
	 * @param dmg amount to decrease by
	 */
	public void decrementHitPoints(int dmg) {
		hitPoints = hitPoints - dmg;
	}
	
	/**
	 * Spawns a bomb, returns it
	 * 
	 * @return BetterSprite the bomb being dropped
	 */
	public void spawnBomb()
	{
		BetterSprite temp = new BetterSprite(Resources.getImage("torpedo"), this.getX()+25, this.getY()+30);
		temp.setSpeed(0, Resources.getDouble("enemyBombSpeed"));
		PlayState.getPlayField().getGroup("enemyTorpedos").add(temp);
	}

	

}
