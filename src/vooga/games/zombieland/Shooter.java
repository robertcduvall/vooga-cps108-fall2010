package vooga.games.zombieland;

import com.golden.gamedev.object.*;
import com.golden.gamedev.Game.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

public class Shooter extends PlayerSprite{
	
	private Sprite currentSprite;
	private int speed;
	
	public Shooter(String name, String stateName, Sprite s,int playerHealth, int playerRank) {
		super(name, stateName, s, playerHealth, playerRank);
		currentSprite = s;
		speed = -1;
	}

	
	
	public void goLeft()
	{
		moveX(speed);
	}
	
	public void goRight()
	{
		moveX(Math.abs(speed));
	}
	
	public void goUp()
	{
		moveY(speed);
	}
	
	public void goDown()
	{
		moveY(Math.abs(speed));
	}
	
}
