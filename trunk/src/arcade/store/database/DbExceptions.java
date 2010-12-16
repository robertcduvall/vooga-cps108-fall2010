package arcade.store.database;

/**
 * This class provides the list of all the exceptions relating the the Database package.
 * 
 * @author Drew Sternesky, Marcus Molchany, 
 *
 */

public class DbExceptions extends RuntimeException{

	public static final RuntimeException USER_TYPE_UNFOUND = 
		new RuntimeException("The user type under reflection is not found");
}
