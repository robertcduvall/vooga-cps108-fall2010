package vooga.games.cyberion.events;

import java.awt.event.KeyEvent;

import vooga.engine.event.IEventHandler;
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
		PlayerShot sprite = new PlayerShot();
		sprite.setX(player.getX());
		sprite.setY(player.getY());
		sprite.setVerticalSpeed(2);
		System.out.println("being here");
		playState.getPlayField().add(sprite);
	}

	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_SPACE);
	}

}
