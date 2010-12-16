package arcade.store.gui.tabs;

public class TabExceptions extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final RuntimeException IMAGE_NOT_FOUND = new RuntimeException(
			"The user image is not found");

}
