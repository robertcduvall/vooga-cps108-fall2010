package arcade.mod.model;

public class ModException extends RuntimeException {
	
	private static final String FILE_ERROR_MESSAGE = "please choose another file";
	private static final String INPUT_ERROR_MESSAGE = "unrecognized input";

	public static ModException BAD_INPUT = new ModException(
			INPUT_ERROR_MESSAGE);

	public static ModException BAD_FILE = new ModException(
			FILE_ERROR_MESSAGE);

	public ModException(String message) {
		super(message);
	}
}
