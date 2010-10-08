package vooga.games.galaxyinvaders;


import java.awt.Point;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;
import vooga.engine.player.control.GameEntitySprite;
import com.golden.gamedev.object.Timer;

public class EnemySprite extends GameEntitySprite {

	private static final int DEFAULT_HP = 2;
    private static final int LEVEL1_TIMER = 700;
    private int pathNum;
    private Timer timer;
   // private Graphics2D graphics;
    private ArrayList<Point> path;
	
	private int hitPoints;
	
	public EnemySprite(String s, String name, Sprite spr, ArrayList<Point> list) {
		super(s, name, spr);
		hitPoints = DEFAULT_HP;
		timer = new Timer(LEVEL1_TIMER);
		pathNum = 0;
		path = list;
	}
	
	public void update(long time) {
		super.update(time);
		if(hitPoints < 2) {
			setToCurrentSprite("damaged");
		}
		if(hitPoints <= 0) {
			setActive(false);
		}
        if (timer.action(time))   move();
	}
	
    public void move(){
        if (pathNum>=path.size())
            return;
        move(path.get(pathNum).getX(), path.get(pathNum).getY());
        pathNum++;
    }
	
	public void decrementHitPoints() {
		hitPoints--;
	}
	

}
