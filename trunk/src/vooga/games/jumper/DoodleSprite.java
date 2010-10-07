package vooga.games.jumper;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class DoodleSprite extends PlayerSprite {


	public DoodleSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
	}
	
	public DoodleSprite(String name, String stateName, Sprite s,
			int playerHealth, int playerRank) {
		super(name, stateName, s, playerHealth, playerRank);
	}

	public void goLeft(){
		System.out.println("left");
	}
}
