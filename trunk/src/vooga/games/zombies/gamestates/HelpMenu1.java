package vooga.games.zombies.gamestates;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import vooga.games.zombies.Blah;

public class HelpMenu1 extends MenuState {

	public HelpMenu1(Blah g) {
		super(g);
	}

	@Override
	public void initialize() {
		getMouseControl().addInput(MouseEvent.BUTTON1, "help2", MAIN_CLASS);
		getKeyboardControl().addInput(KeyEvent.VK_ESCAPE, "main", MAIN_CLASS);
		super.initialize("help1menu");
	}

}
