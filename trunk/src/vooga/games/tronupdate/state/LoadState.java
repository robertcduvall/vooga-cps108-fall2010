package vooga.games.tronupdate.state;

import java.awt.Graphics2D;

import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.games.tronupdate.events.ModeSelectEvent;


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
		//initializeOverlay();

		playField = new PlayField();
		playField.setBackground(new ImageBackground(Resources.getImage("gamestart")));
		initializeEvents();
	}
	
	private void initializeEvents(){
		eventPool = new EventPool();
		eventPool.addEvent(new ModeSelectEvent(game,gameStateManager));
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
