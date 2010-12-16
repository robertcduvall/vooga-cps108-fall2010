package vooga.games.digger.buttons;

import vooga.games.digger.Blah;
import vooga.widget.Button;

public class SwitchStateButton extends Button{
	
	private String stateName;
	
	public SwitchStateButton(String stateName) {
		super(null);
		this.stateName = stateName;
	}

	@Override
	public void actionPerformed() {
		((Blah)myGame).switchGameState(stateName);
	}

}
