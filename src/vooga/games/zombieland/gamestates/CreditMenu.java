package vooga.games.zombieland.gamestates;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import vooga.games.zombieland.Blah;

public class CreditMenu extends MenuState {
	public CreditMenu(Blah g) {
		super(g);
	}

	@Override
	public void initialize() {
		// TODO fix mouse input overlap problem. Ex: click on credit and jumping
		// to help1 instead of main as directed
		// getMouseControl().addInput(MouseEvent.BUTTON1, "main", MAIN_CLASS);
		getKeyboardControl().addInput(KeyEvent.VK_ESCAPE, "main", MAIN_CLASS);
		super.initialize("zombielandcredit");
	}

}
