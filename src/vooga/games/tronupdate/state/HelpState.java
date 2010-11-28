package vooga.games.tronupdate.state;

import java.awt.Color;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.events.HelpToGameEvent;


public class HelpState extends GameState{
	private GameStateManager gameStateManager;
	private EventPool eventPool;
	private Game game;
	private PlayField playField;
	
	public HelpState(Game g,GameStateManager manager){
		game = g;
		gameStateManager = manager;
		initialize();
	}
	
	@Override
	public void initialize(){
		removeEverything();
		playField = new PlayField();
		playField.addColorBackground(Color.BLACK);
		playField.setBackground(0);
		addPlayField(playField);
		initializeOverlay();
		initializeEvents();
	}
	
	private void initializeOverlay(){
		OverlayCreator.setGame(game);
		OverlayTracker tracker = OverlayCreator.createOverlays(Resources.getString("overlayFileURL"));
		playField.addGroup(tracker.getOverlayGroup("Help"));
	}
	
	public void initializeEvents(){
		eventPool = new EventPool();
		eventPool.addEvent(new HelpToGameEvent(game,gameStateManager));
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
	
}
