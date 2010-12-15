package arcade.core.mvc;

public class ControlExceptions extends RuntimeException{

	public static final RuntimeException METHOD_NOT_FOUND = 
		new RuntimeException("The method under reflection is not found");
	
	public static final RuntimeException DELIMITER_PROBLEM =
		new RuntimeException("The delimiter and the event string are not defined or matching");
	
}
