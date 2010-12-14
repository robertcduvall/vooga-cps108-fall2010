package vooga.games.zombies.gamestates;

import java.awt.event.KeyEvent;
import vooga.games.zombies.Blah;

public class MainMenu extends MenuState {

	public MainMenu(Blah g) {
		super(g);
	}

	@Override
	public void initialize() {
		
		getKeyboardControl().addInput(KeyEvent.VK_H, "help1", MAIN_CLASS);
		getKeyboardControl().addInput(KeyEvent.VK_C, "credit", MAIN_CLASS);
		getKeyboardControl().addInput(KeyEvent.VK_ENTER, "play", MAIN_CLASS);
		super.initialize("zombielandmenu");
	}

}
