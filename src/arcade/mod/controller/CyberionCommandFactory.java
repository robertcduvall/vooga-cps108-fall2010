package arcade.mod.controller;

/**
 * 
 * Simple Cyberion implementation of the factory
 * 
 * @author vitorolivier
 * 
 */
public class CyberionCommandFactory extends CommandFactory {

	public CyberionCommandFactory() {
		myMappings.put("score", new IncreaseScoreCommand());
		myMappings.put("increase", new IncreaseLivesCommand());
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
