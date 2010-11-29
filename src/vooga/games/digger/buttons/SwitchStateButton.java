package vooga.games.digger.buttons;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.digger.DropThis;
import vooga.widget.Button;

public class SwitchStateButton extends Button{
	
	private String stateName;
	
	public SwitchStateButton(Game game, String stateName) {
		super(game);
		this.stateName = stateName;
	}

	@Override
	public void actionPerformed() {
		DropThis game = (DropThis) Resources.getGame();
		game.switchGameState(stateName);
	}

}
