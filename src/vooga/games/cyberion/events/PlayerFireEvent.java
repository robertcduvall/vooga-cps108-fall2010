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

		for (int i = 0; i <= player.getWeaponPower(); i++) {
			PlayerShot sprite = new PlayerShot(playState.getPlayField()
					.getGroup("playerShot").getActiveSprite().getImage());
			PlayerShot s = sprite;

			s.setX(player.getX() + 15 * i);
			s.setY(player.getY());
			s.setVerticalSpeed(-0.5);
			playState.getPlayField().getGroup("playerShot").add(s);
		}
	}

	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_SPACE);
	}

}
