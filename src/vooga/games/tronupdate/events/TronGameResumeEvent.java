package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class TronGameResumeEvent implements IEventHandler {

	private GameStateManager gm;
	private Game game;


	public TronGameResumeEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	@Override
	public void actionPerformed() {
		gm.switchTo(gm.getGameState(Resources.getInt("PlayState"))); 
		System.out.println("Game is resumed!");
	}

	@Override
	public boolean isTriggered() {
		return game.keyPressed(KeyEvent.VK_R);
    }
}
