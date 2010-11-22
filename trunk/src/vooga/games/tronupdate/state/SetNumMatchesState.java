package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.GameState;
import vooga.engine.overlay.*;
import vooga.games.tronupdate.events.SetNumMatchesEvent;

public class SetNumMatchesState extends GameState{
	private Game game;
	private GameStateManager gameStateManager;
	private PlayField playField;
	private EventPool eventPool;
	
	public SetNumMatchesState(Game game, GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		this.game=game;
	}
	
	@Override
	public void initialize(){
		playField = new PlayField();
		playField.addColorBackground(Color.WHITE);
		playField.setBackground(0);
		initializeOverlay();
	}
	
	private void initializeOverlay(){
		OverlayCreator.setGame(game);
		OverlayTracker tracker = OverlayCreator.createOverlays("src/vooga/games/tronupdate/resources/overlays.xml");
		playField.addGroup(tracker.getOverlayGroup("SetNumMatches"));
		initializeEvents();
	}
	
	public void initializeEvents(){
		eventPool = new EventPool();
		eventPool.addEvent(new SetNumMatchesEvent(game,gameStateManager));
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
