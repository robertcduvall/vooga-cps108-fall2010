package arcade.mod.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that maps input to a specific kind of console command
 * 
 * @author vitorolivier
 *
 */
public abstract class CommandFactory {
	protected Map<String, IConsoleCommand> myMappings = new HashMap<String, IConsoleCommand>();
	
	public abstract IConsoleCommand runCommand(String command);

}
