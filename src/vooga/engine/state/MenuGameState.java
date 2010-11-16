package vooga.engine.state;

import vooga.engine.core.Button;
import vooga.engine.core.PlayField;

public class MenuGameState extends GameState {

	private PlayField menuPlayfield = new PlayField();

	public MenuGameState(Iterable<Button> buttons) {

		for (Button button : buttons) {
			addButton(button);
		}

	}

	@Override
	public void initialize() {

	}

	public void addButton(Button button) {

		menuPlayfield.add(button);
		menuPlayfield.add(button);

		addPlayField(menuPlayfield);

	}

}
