package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;

import vooga.games.tronupdate.events.ModeSelectEvent;
import vooga.games.tronupdate.events.InvokeHelpEvent;

public class LoadState extends GameState{
	private Game game;
	private GameStateManager gameStateManager;
	private PlayField playField;
	private EventPool eventPool;
	
	public LoadState(Game game, GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		this.game=game;
	}
	
	@Override
	public void initialize() {
		playField = new PlayField();
		playField.addColorBackground(Color.BLACK);
		playField.setBackground(0);
		initializeEvents();
		initializeOverlay();
	}
	
	private void initializeOverlay(){
		OverlayCreator.setGame(game);
		OverlayTracker tracker = OverlayCreator.createOverlays(Resources.getString("overlayFileURL"));
		playField.addGroup(tracker.getOverlayGroup("load"));
	}
	
	
	private void initializeEvents(){
		eventPool = new EventPool();
		eventPool.addEvent(new ModeSelectEvent(game,gameStateManager));
		eventPool.addEvent(new InvokeHelpEvent(game,gameStateManager,Resources.getInt("LoadState")));
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
