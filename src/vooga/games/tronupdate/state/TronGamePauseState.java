package vooga.games.tronupdate.state;

import java.awt.Graphics2D;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.events.TronGameResumeEvent;
import vooga.games.zombieland.Blah;

public class TronGamePauseState extends GameState{
	private GameStateManager gameStateManager;
	private EventPool eventPool;
	private Game game;
	
	public TronGamePauseState(Game game, GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		this.game=game;
	}
	
	@Override
	public void initialize() {
		eventPool = new EventPool();
		TronGameResumeEvent tronGameResumeEvent = new TronGameResumeEvent(game,gameStateManager);
		eventPool.addEvent(tronGameResumeEvent);
	}
	

	public void update(long elapsedTime){
		super.update(elapsedTime);
	//	playerControl.update();
		eventPool.checkEvents();
	}
	
}
