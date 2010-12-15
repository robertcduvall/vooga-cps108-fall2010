package arcade.core.mvc;

public class ControlExceptions extends RuntimeException{

	public static final RuntimeException METHOD_NOT_FOUND = 
		new RuntimeException("The method under reflection is not found");
	

}
