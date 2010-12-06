package arcade.security.exceptions;

/**
 * Handle privileges not found in the privilegeMap. 
 * @author Meng Li
 *
 */
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

	public PrivilegeNotFoundException(Throwable e){ 
		super(e.getCause());
	}

}
