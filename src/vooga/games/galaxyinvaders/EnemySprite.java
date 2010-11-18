package vooga.games.galaxyinvaders;


import java.awt.Point;
import java.util.ArrayList;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.engine.resource.random.Randomizer;
import vooga.engine.resource.random.RandomizerException;

import com.golden.gamedev.object.Timer;

/**
 * The EnemySprite class represents each invader on the screen. The 
 * GalaxyInvaders class creates a sprite group of them.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class EnemySprite extends Sprite {

	private static final int DEFAULT_HP = 2;
    private int pathNum;
    private Timer timer;
    private ArrayList<Point> path;    
	private int hitPoints;
	
	/**
	 * This is the constructor for the EnemySprite class. It is similar to GameEntitySprite's 
	 * constructor, but with an extra field which determines the path it will travel.
	 * 
	 * @param s see GameEntitySprite
	 * @param name see GameEntitySprite
	 * @param spr see GameEntitySprite
	 * @param list the path which this enemy is going to follow
	 */
	public EnemySprite(String s, String name, Sprite spr, ArrayList<Point> pathmap, int timerNum) {
		super(s, name, spr);
		hitPoints = DEFAULT_HP;
		timer = new Timer(timerNum);
		pathNum = 0;
		path = pathmap;
	}
	
	/**
	 * Called every turn by the Game, this method moves the enemy, changes 
	 * its color if it is damaged, and removes it if it is destroyed.
	 */
	public void update(long time) {
		super.update(time);
		if(hitPoints < DEFAULT_HP) {
			setAsRenderedSprite("damaged");
		}
		if(hitPoints <= 0) {
			setActive(false);
		}
        if (timer.action(time))   move();
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
	
	/**
	 * Decreases hitPoints by a certain amount
	 * 
	 * @param dmg amount to decrease by
	 */
	public void decrementHitPoints(int dmg) {
		hitPoints = hitPoints - dmg;
	}

	

}
