package vooga.games.zombieland;

import com.golden.gamedev.object.*;
import com.golden.gamedev.Game.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

public class Shooter extends PlayerSprite{
	
	private Sprite currentSprite;
	
	public Shooter(String name, String stateName, Sprite s,int playerHealth, int playerRank) {
		super(name, stateName, s, playerHealth, playerRank);
		currentSprite = s;
	}

	
	
	public void goLeft()
	{
		moveX(-10);
	}
	
	public void goRight()
	{
		moveX(10);
	}
	
	public void goUp()
	{
		moveY(-10);
	}
	
	public void goDown()
	{
		moveY(10);
	}
	
}
