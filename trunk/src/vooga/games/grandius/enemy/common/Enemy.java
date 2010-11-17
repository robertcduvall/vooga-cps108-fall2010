package vooga.games.grandius.enemy.common;

import java.awt.image.BufferedImage;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;

/**
 * An Enemy is any adversary of the player.
 */
public abstract class Enemy extends Sprite {
	//TODO - what is serialVersionUID for?
	private static final long serialVersionUID = 1L;
	private static final String SCORE = "Score";
	private static final String CASH = "Cash";
	
	public Enemy(BufferedImage[] images, double x, double y){
		super(images);
		this.setLocation(x, y);
	}
	
	public void setScore(int score){
		Stat<Integer> scoreValue = new Stat<Integer>(score);
		setStat(SCORE, scoreValue);
	}
	
	public int getScore(){
		return (Integer) this.getStat(SCORE).getStat();
	}
	
	public void setCash(int cash){
		Stat<Integer> cashValue = new Stat<Integer>(cash);
		setStat(CASH, cashValue);
	}
	
	public int getCash(){
		return (Integer) this.getStat(CASH).getStat();
	}
}

//public abstract class Enemy extends AnimatedSprite {
//	
//	private static final long serialVersionUID = 1L;
//	protected int scoreValue;
//	protected int cashValue;
//	
//	public Enemy(BufferedImage[] images, double x, double y)
//	{
//		super(images, x, y);
//	}
//	
//	public int getScore()
//	{
//		return scoreValue;
//	}
//	
//	public int getCashValue()
//	{
//		return cashValue;
//	}
//}