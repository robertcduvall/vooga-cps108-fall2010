package arcade.store.gui.tabs;

/**
 * TabExceptions contains all of the possible exceptions that can be produced by
 * a tab in the store.gui.tabs package.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class TabExceptions extends RuntimeException {

	/**
	 * An exception when the user image cannot be found
	 */
	private static final long serialVersionUID = 1L;
	public static final RuntimeException IMAGE_NOT_FOUND = new RuntimeException(
			"The user image is not found");

}
