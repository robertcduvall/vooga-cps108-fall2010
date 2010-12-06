package arcade.security.exceptions;

/**
 * Handle exceptions when launching the InternalFrames in SecurityDesktop
 * @author Meng Li
 *
 */
@SuppressWarnings("serial")
public class UserConfigurationNotFoundException extends Exception {

	/**
	 * Create exception with given message
	 *  
	 * @param message explanation of problem
	 */
	public UserConfigurationNotFoundException(String message){
		super(message);
	}

	public UserConfigurationNotFoundException(Throwable e){ 
		super(e.getCause());
	}
}

