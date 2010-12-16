package arcade.store.gui.tabs;

public class TabExceptions extends RuntimeException{

	public static final RuntimeException IMAGE_NOT_FOUND = 
		new RuntimeException("The user image is not found");
	
}
