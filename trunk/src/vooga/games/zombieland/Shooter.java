package vooga.games.zombieland;

import com.golden.gamedev.object.*;
import com.golden.gamedev.Game.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

public class Shooter extends PlayerSprite {

	private AnimatedSprite currentSprite;
	private int speed;

	public Shooter(String name, String stateName, AnimatedSprite s,
			int playerHealth, int playerRank) {
		super(name, stateName, s, playerHealth, playerRank);
		currentSprite = s;
		speed = -1;
	}

	private void initiazlizeAnimation(AnimatedSprite s) {
		// set animation speed 100 milliseconds for each frame
		s.getAnimationTimer().setDelay(100);

		// set animation frame starting from the first image to the third image
		s.setAnimationFrame(0, 4);

		// animate the sprite, and perform continous animation
		s.setAnimate(true);
		s.setLoopAnim(true);
	}

	public void goLeft() {
		setToCurrentSprite("Left");
		moveX(speed);
		animateSprite();
	}

	public void goRight() {
		setToCurrentSprite("Right");
		moveX(Math.abs(speed));
		animateSprite();
	}

	public void goUp() {
		setToCurrentSprite("Up");
		moveY(speed);
		animateSprite();
	}

	public void goDown() {
		setToCurrentSprite("Down");
		moveY(Math.abs(speed));
		animateSprite();
	}

	private void animateSprite() {
		System.out.println(currentSprite.getFrame()+":"+currentSprite.getStartAnimationFrame() +"-" +currentSprite.getFinishAnimationFrame());
//		if(currentSprite.getFrame()>=currentSprite.getFinishAnimationFrame()) {
			currentSprite.setFrame(currentSprite.getFrame()+1);
//		}
		
//		currentSprite.setAnimate(true);
//		currentSprite.setLoopAnim(true);
	}

}
