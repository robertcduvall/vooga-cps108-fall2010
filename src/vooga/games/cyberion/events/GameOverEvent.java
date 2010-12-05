package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;
import vooga.games.cyberion.Blah;
import vooga.games.cyberion.sprites.playership.PlayerShip;

public class GameOverEvent implements IEventHandler {
	private Blah myGame;
	private PlayerShip player;

	public GameOverEvent(Blah myGame, PlayerShip player) {
		this.myGame = myGame;
		this.player = player;
	}

	@Override
	public boolean isTriggered() {
		return player.getLife() == 0;
	}

	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().switchTo(
				myGame.getGameStateManager().getGameState(1));
	}
}
