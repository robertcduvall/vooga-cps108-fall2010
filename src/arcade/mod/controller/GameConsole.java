package arcade.mod.controller;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.Game;

import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import arcade.mod.view.ConsoleView;

/**
 * 
 * gives access for the commands to be able to modify all the major parts of the
 * game
 * 
 * @author vitorolivier
 * 
 */
public class GameConsole {
	protected static GameStateManager stateManager;
	protected static GameState playState;
	protected static ArrayList<Sprite> allSprites;

	public static int PLAY_STATE;
	protected ConsoleView myView;
	protected CommandFactory myCommandFactory;

	public GameConsole() {
		myView = new ConsoleView();
		myCommandFactory = new GenericFactory();
	}

	public void update(long t) {
		if (!myView.myInput.equals(ConsoleView.NO_INPUT)) {
			runCommand(myView.textInput.getText());
			myView.myInput = ConsoleView.NO_INPUT;
		}

	}

	private void runCommand(String textInput) {
		IConsoleCommand command = myCommandFactory.runCommand(textInput);
		if (command != null) {
			command.performCommand(textInput);
		}
	}

	public void toggleVisible() {
		myView.setVisible(!myView.isVisible());
	}
}
