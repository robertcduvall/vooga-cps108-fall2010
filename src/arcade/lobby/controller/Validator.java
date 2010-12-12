package arcade.lobby.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {

	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public static boolean isValidDate(String date) {
		// set date format, this can be changed to whatever format
		// you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
		// you can read more about it here:
		// http://java.sun.com/j2se/1.4.2/docs/api/index.html
		

		// declare and initialize testDate variable, this is what will hold
		// our converted string

		Date testDate = null;

		// we will now try to parse the string into date form
		try {
			testDate = sdf.parse(date);
		}

		// if the format of the string provided doesn't match the format we
		// declared in SimpleDateFormat() we will get an exception

		catch (ParseException e) {
			return false;
		}

		// dateformat.parse will accept any date as long as it's in the format
		// you defined, it simply rolls dates over, for example, december 32
		// becomes jan 1 and december 0 becomes november 30
		// This statement will make sure that once the string
		// has been checked for proper formatting that the date is still the
		// date that was entered, if it's not, we assume that the date is
		// invalid

		if (!sdf.format(testDate).equals(date)) {
			return false;
		}

		// if we make it to here without getting an error it is assumed that
		// the date was a valid one and that it's in the proper format

		return true;

	}
	
	public static boolean checkEmailFormat(String input) {
		return Pattern.matches("^[A-Za-z0-9\\.]+[^\\.]@([A-Za-z0-9]+\\.)+[A-Za-z]{2,6}$", input);
	}
	

	public static boolean checkUsername(String user){
		//uncomment this part for error check upon log-in
		/*
		Profile profile = Main.ProfileSet.getProfile(user);
		return profile == null;
		*/
		return false;
	}
	
	

	public static SimpleDateFormat getDateFormat() {
		return sdf;
	}
	
}
