package vooga.games.cyberion.events;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.cyberion.sprites.playershot.PlayerShot;
import vooga.games.cyberion.states.PlayState;

public class PlayerFireEvent implements IEventHandler {

	private DropThis myGame;
	private PlayerShip player;
	private PlayState playState;

	public PlayerFireEvent(DropThis myGame, PlayerShip player,
			PlayState playState) {
		this.myGame = myGame;
		this.player = player;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
		PlayerShot sprite = new PlayerShot(Resources.getImage("playerShot"));
		sprite.setX(player.getX());
		sprite.setY(player.getY());
		sprite.setVerticalSpeed(-0.5);
		playState.getPlayField().getGroup("playerShot").add(sprite);
	
//		playState.getPlayField().addGroup(temp);
	}

	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_SPACE);
	}

}
