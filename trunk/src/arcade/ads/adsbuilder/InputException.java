package arcade.ads.adsbuilder;

import javax.swing.JOptionPane;

public class InputException extends RuntimeException{
	public final static InputException URL_PARSING_EXCEPTION = 
		new InputException("Invalid URL");
	
	public final static InputException DATE_PARSING_EXCEPTION =
		new InputException("Invalid Date. Please use the following format: MM/dd/yyyy HH:mm:ss");
	
	public final static InputException INTEGER_PARSING_EXCEPTION =
		new InputException("Invalid Integer Input");
	
	public final static InputException FILE_EXCEPTION = 
		new InputException("No file input");
	
    
	/**
     * Create exception with given meesage
     *  
     * @param message explanation of problem
     */
    public InputException (String message)
    {
		super(message);
		JOptionPane.showMessageDialog(null,
			    message,
			    "Input Error",
			    JOptionPane.ERROR_MESSAGE);
    }
}
