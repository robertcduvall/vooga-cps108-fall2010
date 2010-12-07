package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
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
	private PlayField playField;
	
	public TronGamePauseState(Game game, GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		this.game=game;
	}
	
	@Override
	public void initialize() {
		playField = new PlayField();
		initEvent();
		initOverlay();
	}
	
	private void initOverlay(){
		OverlayCreator.setGame(game);
		OverlayTracker tracker = OverlayCreator.createOverlays(Resources.getString("overlayFileURL"));
		playField.addGroup(tracker.getOverlayGroup("Pause"));
	}
	
	private void initEvent(){
		eventPool = new EventPool();
		TronGameResumeEvent tronGameResumeEvent = new TronGameResumeEvent(game,gameStateManager);
		eventPool.addEvent(tronGameResumeEvent);
	}
	
	public void render(Graphics2D g) {
		playField.render(g);	
	}

	public void update(long elapsedTime){
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
	
}
