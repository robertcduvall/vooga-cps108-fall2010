package engine.player.control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.object.Background;
import engine.core.Sprite;


/**
 * GameEntitySprite represents any object that you might interact with in a game. Player and Item
 * are both extensions of this class. It supports having multiple images (implemented as sprites)
 * to represent a single GameEntity. This class extends Sprite so that it can act as a sprite,
 * which is the unit that Golden-T is built around, for things like collision detection and 
 * being able to be added to SpriteGroups, but can also use multiple sprites (and different kind of
 * sprites) to represent the entity.
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 *
 */

@SuppressWarnings("serial")
public abstract class GameEntitySprite extends Sprite {

	private long myStartTime;
	private String myName;
	private Map<String, Sprite> mySprites;							
	private Sprite myCurrentSprite;							
	
	/**
	 * @param name is any name you'd like to give to the object.
	 * @param stateName is the name you'd like to map to the following Sprite object. e.g."alive" to represent a sprite that's a live, or
	 * "dead" if the sprite represents the entity in a dead state, etc
	 * @param this is the default Sprite that will represent this Entity on the Screen.
	 */
	
	public GameEntitySprite(String name, String stateName, Sprite s)
	{
		myStartTime = System.currentTimeMillis();
		mySprites = new HashMap<String, Sprite>();
		mapNameToSprite(stateName, s);
		myCurrentSprite = s;
		setName(name);
	}
	

	/**
	 * @param state is the name you'd like to use to represent the Sprite parameter. e.g. "alive" or "dead" or "shooting"
	 * @param sp is the Sprite you are mapping to the first parameter.
	 */
	
	public void mapNameToSprite(String state, Sprite sp)						
	{
		mySprites.put(state, sp);
	}

	/**
	 * 
	 * @return length of time object has existed
	 */
	public long getTimeInExistence() 
	{										
		long currentTime = System.currentTimeMillis();
		return currentTime - myStartTime;
	}

	/** 
	 * 
	 * @return GameEntity's name.
	 */
	public String getName()
	{
		return myName;
	}
	
	/**
	 * Changes the name of the GameEntity.
	 * @param s new name.
	 */
	public void setName(String s)
	{
		myName = s;
	}

	/**
	 * Modify GameEntity so that it is represented by the Sprite specified by spriteName. 
	 * @param spriteName the "name" of the sprite that GameEntity will now be represented by.
	 */
	public void setCurrentSprite(String spriteName)
	{
		if(mySprites.containsKey(spriteName))
		{
			Sprite tempSprite = mySprites.get(spriteName);
			syncIncomingAndOutgoingSprite(tempSprite);
			myCurrentSprite = tempSprite;
		}
		else
			System.out.println("String does not exist in as a state to set current image");
	}

	//syncs the outgoing and incoming sprites so that no matter what Sprite GameEntity is using currently,
	//the GameEntity object will always be in the same place and moving at the same speed.
	private void syncIncomingAndOutgoingSprite(Sprite incoming) {
		incoming.setLocation(myCurrentSprite.getX(), myCurrentSprite.getY());
		incoming.setSpeed(myCurrentSprite.getHorizontalSpeed(), myCurrentSprite.getVerticalSpeed());
		incoming.setActive(true);
	}
	
	
	
/*
 * THIS SECTION REWRITES ALL OF SPRITE'S METHODS. These simply forward the method calls to the
 * currently active sprite in the GameEntity.
 */

	public void update(long elapsedTime) {
		myCurrentSprite.update(elapsedTime);
	}
	
	public void render(Graphics2D g) 
	{
		myCurrentSprite.render(g);
	}
	
	public void addHorizontalSpeed(long elapsedTime, double accel, double maxSpeed)
	{
		myCurrentSprite.addHorizontalSpeed(elapsedTime, accel, maxSpeed);
	}
	
	public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed)
	{
		myCurrentSprite.addVerticalSpeed(elapsedTime, accel, maxSpeed);
	}
	
	public void forceX(double xs)
	{
		myCurrentSprite.forceX(xs);
	}
   
	public void forceY(double ys)
	{
		myCurrentSprite.forceY(ys);
	}
	
	public double getX()
	{
		return myCurrentSprite.getX();
	}
	
	public double getY()
	{
		return myCurrentSprite.getY();
	}
	
	public double getDistance(Sprite other) 
	{
		return myCurrentSprite.getDistance(other);
	}
	
	public int getHeight() 
	{
		return myCurrentSprite.getHeight();
	}
	
	public int getWidth()
	{
		return myCurrentSprite.getWidth();
	}
	
	public double getHorizontalSpeed() 
	{
		return myCurrentSprite.getHorizontalSpeed();
	}
	
	public BufferedImage getImage() 
	{
		return myCurrentSprite.getImage();
	}
	
	public double getVerticalSpeed()
	{
		return myCurrentSprite.getVerticalSpeed();
	}
	
	public boolean isOnScreen() 
	{
		return myCurrentSprite.isOnScreen();
	}
	
	public void move(double dx, double dy) 
	{
		myCurrentSprite.move(dx, dy);
	}
	
	public boolean moveTo(long elapsedTime, double xs, double ys, double speed)
	{
		return myCurrentSprite.moveTo(elapsedTime, xs, ys, speed);
	}
	
	public void moveX(double dx) 
	{
		myCurrentSprite.moveX(dx);
	}
	
	public void moveY(double dy) 
	{
		myCurrentSprite.moveY(dy);
	}
	
	public void setActive(boolean b)
	{
		myCurrentSprite.setActive(b);
	}
	
	public boolean isActive()
	{
		return myCurrentSprite.isActive();
	}
	
	public void setBackground(Background backgr)
	{
		for(String s : mySprites.keySet()) {
			mySprites.get(s).setBackground(backgr);
		}
	}
	
	public void setHorizontalSpeed(double vx)
	{
		myCurrentSprite.setHorizontalSpeed(vx);
	}
	
	public void setLayer(int i)
	{
		for(String s : mySprites.keySet()) 
		{
			mySprites.get(s).setLayer(i);
		}
	}
	
	public void setLocation(double xs, double ys)
	{
		myCurrentSprite.setLocation(xs, ys);
	}
	
	public void setMovement(double speed, double angleDir)
	{
		myCurrentSprite.setMovement(speed, angleDir);
	}
	
	public void setSpeed(double vs, double vy)
	{
		myCurrentSprite.setSpeed(vs, vy);
	}
	
	public void setVerticalSpeed(double vy) 
	{
		myCurrentSprite.setVerticalSpeed(vy);
	}
	
	public void setX(double xs) 
	{
		myCurrentSprite.setX(xs);	
	}
	
	public void setY(double ys)
	{
		myCurrentSprite.setY(ys);
	}
	
}