package vooga.games.zombies.gamestates;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import vooga.games.zombies.Blah;

public class HelpMenu2 extends MenuState {

	public HelpMenu2(Blah g) {
		super(g);
	}

	@Override
	public void initialize() {
		getMouseControl().addInput(MouseEvent.BUTTON1, "main", MAIN_CLASS);
		getKeyboardControl().addInput(KeyEvent.VK_ESCAPE, "main", MAIN_CLASS);
		super.initialize("help2menu");
	}

}
