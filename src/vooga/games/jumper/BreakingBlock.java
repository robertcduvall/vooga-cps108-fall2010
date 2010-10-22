package vooga.games.jumper;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.engine.resource.GameClock;
import vooga.engine.resource.Resources;

public class BreakingBlock extends BlockSprite{

	private long breakTimer = 0;
	private long breakTimerRate = 100;
	private long startBreakTime = 0;
	private long breakTimeElapsed;
	
	
	public BreakingBlock(BufferedImage image, Point location, double velocityX,
			double velocityY) {
		super(image, location, velocityX, velocityY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleCollision(DoodleSprite doodle) {
		// TODO Auto-generated method stub
		doodle.setVerticalSpeed(this.getVerticalSpeed()); //doodlespeed = speed of block
		if(startBreakTime == 0){
			startBreakTime = GameClock.getTime();
			
		}else if(Math.abs(breakTimeElapsed-breakTimerRate*1)<10){
			this.setImage(Resources.getImage("platformBreak1"));
			
		}else if(Math.abs(breakTimeElapsed-breakTimerRate*2)<10){
			this.setImage(Resources.getImage("platformBreak2"));
			
		}else if(Math.abs(breakTimeElapsed-breakTimerRate*3)<10){
			this.setImage(Resources.getImage("platformBreak3"));

			startBreakTime = 0;
			((BlockSprite)this).setBlockType("blockTypeNormal");
			
		}
		doodle.setVerticalSpeed(1.0);
		breakTimeElapsed = GameClock.getTime()-startBreakTime;
		

	}

}
