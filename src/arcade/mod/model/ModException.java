package arcade.mod.model;

import javax.swing.JOptionPane;

/**
 * 
 * Exception if a bad input is selected
 *
 */

public class ModException extends RuntimeException {
	
	private static final String INPUT_ERROR_MESSAGE = "you have entered an invalid image, son";

	public static ModException BAD_INPUT = new ModException(
			INPUT_ERROR_MESSAGE);

	public ModException(String message) {
		super(message);
		
		JOptionPane.showMessageDialog(null,
		    message,
		    "WARNING",
		    JOptionPane.ERROR_MESSAGE);
	}
}