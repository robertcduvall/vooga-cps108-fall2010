package arcade.mod.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

	private Map<String, IConsoleCommand> myMappings = new HashMap<String, IConsoleCommand>();

	/**
	 * Creates a new instance of FrameFactory and populates the map
	 */
	public CommandFactory() {
		myMappings.put("score", new IncreaseScore());
		myMappings.put("increase", new IncreaseLives());
		myMappings.put("pause", new PauseCommand());
		myMappings.put("unpause", new UnpauseCommand());
	}

	public IConsoleCommand runCommand(String command) {
		if (myMappings.containsKey(command))
			return myMappings.get(command);
		else
			return null;
	}
}
