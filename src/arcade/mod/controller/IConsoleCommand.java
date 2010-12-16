package arcade.mod.controller;

public interface IConsoleCommand {

	/**
	 * Interface that runs performCommand when a user inputs myInput
	 * @param myInput
	 */
	public void performCommand(String myInput);

}
