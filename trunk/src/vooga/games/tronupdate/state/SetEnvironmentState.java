package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.events.SetEnvironmentEvent;

public class SetEnvironmentState extends GameState{
	private Game game;
	private GameStateManager gameStateManager;
	private PlayField playField;
	private EventPool eventPool;
	
	public SetEnvironmentState(Game game, GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		this.game=game;
	}
	
	@Override
	public void initialize(){
		playField = new PlayField();
		playField.addColorBackground(Color.BLACK);
		playField.setBackground(0);
		initializeOverlay();
		initializeEvents();
	}
	
	private void initializeOverlay(){
		OverlayCreator.setGame(game);
		OverlayTracker tracker = OverlayCreator.createOverlays(Resources.getString("overlayFileURL"));
		playField.addGroup(tracker.getOverlayGroup("SetEnvironment"));
	}
	
	private void initializeEvents(){
		eventPool = new EventPool();
		eventPool.addEvent(new SetEnvironmentEvent(game,gameStateManager));
	}
	
	public void render(Graphics2D g) {
		playField.render(g);	
	}	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		playField.update(elapsedTime);	
		eventPool.checkEvents();
	}
}
