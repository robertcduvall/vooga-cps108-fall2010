package vooga.games.doodlejump.states;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.widget.Button;


import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;
import vooga.games.doodlejump.buttons.RestartButton;

public class GameWonState extends MenuGameState{

	private RestartButton myRestartButton;
	private Game myGame;
	
	public GameWonState (Game game){
		super();
		this.myGame = game;
	}
	
	@Override
	public void initialize() {
		this.myRestartButton = new RestartButton(myGame);
		addButton(myRestartButton);
	}

}
