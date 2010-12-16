package arcade.core.mvc;

/**
 * 
 * @author 			Yijia Mu
 * @date			12-16-10
 * @description		This is the class of exceptions that conver all the used control exceptions.
 */

public class ControlExceptions extends RuntimeException{

	public static final RuntimeException METHOD_NOT_FOUND = 
		new RuntimeException("The method under reflection is not found");
	
	public static final RuntimeException DELIMITER_PROBLEM =
		new RuntimeException("The delimiter and the event string are not defined or matching");
	
}
