package vooga.games.digger.buttons;

import vooga.games.digger.DropThis;
import vooga.widget.Button;

public class SwitchStateButton extends Button{
	
	private String stateName;
	
	public SwitchStateButton(String stateName) {
		super();
		this.stateName = stateName;
	}

	@Override
	public void actionPerformed() {
		((DropThis)myGame).switchGameState(stateName);
	}

}
