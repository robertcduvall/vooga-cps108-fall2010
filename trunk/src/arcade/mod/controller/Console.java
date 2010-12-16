package arcade.mod.controller;

import arcade.mod.view.ConsoleView;

public class Console {

	protected ConsoleView myView;
	protected CommandFactory myCommandFactory;

	public Console() {
		myView = new ConsoleView();
		myCommandFactory = new CommandFactory();

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
