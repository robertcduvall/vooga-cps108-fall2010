package arcade.security.mvc;

public class MVCExceptions extends RuntimeException{

	public static final RuntimeException INVALID_CONTROLLER_REQUEST = 
		new RuntimeException("Attempting to get a IController from an invalid request key");
	
	public static final RuntimeException FILE_PATH_ERROR = 
		new RuntimeException("Attempting to pass in an invalid filepath for MainController");
	
	
}
