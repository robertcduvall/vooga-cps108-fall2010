package vooga.examples.networking.zombies.gamestates;

import java.awt.event.KeyEvent;
import vooga.examples.networking.zombies.Blah;

public class CreditMenu extends MenuState {
	public CreditMenu(Blah g) {
		super(g);
	}

	@Override
	public void initialize() {
		// TODO fix mouse input overlap problem.
		// getMouseControl().addInput(MouseEvent.BUTTON1, "main", MAIN_CLASS);
		getKeyboardControl().addInput(KeyEvent.VK_ESCAPE, "main", MAIN_CLASS);
		super.initialize("zombielandcredit");
	}

}
