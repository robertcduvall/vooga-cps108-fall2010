package arcade.store.control;

public class ControlExceptions extends RuntimeException{

	public static final Exception METHOD_NOT_FOUND = 
		new RuntimeException("The method under reflection is not found");
	

}
