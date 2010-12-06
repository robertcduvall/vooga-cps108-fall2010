package arcade.security.exceptions;

public class UserConfigurationNotFoundException extends Exception {
	
		private static final long serialVersionUID = 1L;

		public UserConfigurationNotFoundException(String message){
			super(message);
		}

		public UserConfigurationNotFoundException(Throwable e){  //?? right?
			super(e.getCause());
		}
}

