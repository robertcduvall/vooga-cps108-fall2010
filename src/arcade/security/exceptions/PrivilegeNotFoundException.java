package arcade.security.exceptions;

@SuppressWarnings("serial")
public class PrivilegeNotFoundException extends Exception {
	/**
	 * Create exception with given message
	 *  
	 * @param message explanation of problem
	 */
	public PrivilegeNotFoundException (String message)
	{
		super(message);
	}
}
