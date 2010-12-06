package arcade.security.exceptions;

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

