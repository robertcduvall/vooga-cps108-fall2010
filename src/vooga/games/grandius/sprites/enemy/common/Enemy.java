package vooga.games.grandius.sprites.enemy.common;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;

/**
 * An Enemy is any adversary of the player.
 */
public abstract class Enemy extends BetterSprite {
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