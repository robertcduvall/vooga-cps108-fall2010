package arcade.mod.model;

public class ModException extends RuntimeException {
	public static ModException BAD_INPUT = new ModException(
			"unrecognized input");

	public static ModException BAD_FILE = new ModException(
			"please choose another file");

	public ModException(String message) {
		super(message);
	}
}
