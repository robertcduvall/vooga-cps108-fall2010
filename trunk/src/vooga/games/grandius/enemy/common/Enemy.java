package vooga.games.grandius.enemy.common;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.*;

public abstract class Enemy extends AnimatedSprite {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int myScore;
	
	public Enemy(BufferedImage[] images, double x, double y)
	{
		super(images, x, y);
	}
	
	public int getScore()
	{
		return myScore;
	}
}
