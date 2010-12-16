package arcade.security.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

/**
 * Model object for the sign up panel
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class SignUpHandler {
	private static DataHandler userHandler = DataHandler.getInstance("User");
	private static DataHandler privilegeHandler = DataHandler
			.getInstance("Privileges");

	/**
	 * Checks a username to see if it is valid.
	 * 
	 * @param name
	 *            The potential username to check
	 * @return true if it is valid
	 */
	public static boolean isValidUserName(String name) {
		if (name.contains(" "))
			return false;
		if (name.length() == 0)
			return false;
		return (userHandler.getUserId(name) < 1);
	}

	/**
	 * Compares two passwords to see if they are the same.
	 * 
	 * @param pwd_1
	 *            The first password to compare
	 * @param pwd_2
	 *            The second password to compare
	 * @return true if they are the same.
	 */
	public static boolean samePassword(char[] pwd_1, char[] pwd_2) {
		if (pwd_1.length != pwd_2.length)
			return false;
		for (int i = 0; i < pwd_1.length; i++) {
			if (pwd_1[i] != pwd_2[i])
				return false;
		}
		return true;
	}

	/**
	 * Creates a new user in the database.
	 * 
	 * @param username
	 *            The new user's username
	 * @param password
	 *            The new user's password
	 * @param questionIndex
	 *            The index of the new user's forgotten password question
	 * @param questionAnswer
	 *            The new user's forgotten password question
	 * @param firstName
	 *            The new user's first name
	 * @param lastName
	 *            The new user's last name
	 * @param email
	 *            The new user's email address
	 * @param birthday
	 *            The new user's birthday
	 * @param avatar
	 *            The new user's avatar's URL
	 */
	public static void createNewUser(String username, char[] password,
			int questionIndex, String questionAnswer, String firstName,
			String lastName, String email, String birthday, String avatar) {
		// The next line is the potentially integration with the lobby group
		// int id = ProfileSet.currentProfile.getUserId();
		PasswordHasher hasher = new PasswordHasher();
		String hashedPassword = hasher.encrypt(String.valueOf(password));

		Map<String, String> userRow = new LinkedHashMap<String, String>();
		userRow.put("UserName", username);
		userRow.put("Password", hashedPassword);
		userRow.put("QuestionIndex", String.valueOf(questionIndex));
		userRow.put("QuestionAnswer", questionAnswer);
		userRow.put("UserType", "Administrator");
		if (userHandler.insert(userRow))
			System.out.println("User created");
		else
			System.out.println("User not created!!!");

		// potentially use userID to sync between different tables. But since
		// the
		// ID is autoincrement and there is no such method that
		// int id = ProfileSet.currentProfile.getUserId();
		int id = userHandler.getUserId(username);

		Map<String, String> privilegeRow = new HashMap<String, String>();
		privilegeRow.put("User_Id", Integer.toString(id));
		privilegeRow.put("UserName", username);
		privilegeRow.put("Privileges", "00000");
		privilegeHandler.insert(privilegeRow);

		// Add a profile for the user
		Profile profile = new Profile(id);
		profile.setName(firstName, lastName);
		profile.setEmail(email);
		profile.setAvatar(avatar);
		profile.setBirthday(birthday);
		ProfileSet.addProfile(profile);
	}

}
