package arcade.security.util;

import java.util.Map;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.event.*;
import java.util.ArrayList;
import arcade.security.gui.AdminBox;

/**
 * Model for the AdminPanel view.
 * 
 * @author Jiaqi Yan, Meng Li, Nick Hawthorne
 * 
 */
public class AdminHandler {

	public static DataHandler userHandler = DataHandler.getInstance("User");
	private static Map<String, String> userTypeMap;
	private static Map<String, Integer> changeMap;
	private static String[] userTypes = { "Guest", "User", "Developer",
			"Administrator" };
	private static ArrayList<AdminBox> AdminBoxList;

	/**
	 * Constructor for AdminHandler. Creates object, initializes maps for the
	 * current user.
	 */
	public AdminHandler() {
		userTypeMap = userHandler.getUserTypeMap();
		changeMap = new HashMap<String, Integer>();
		AdminBoxList = new ArrayList<AdminBox>();
	}

	/**
	 * Creates a new box on the admin page.
	 * 
	 * @param name
	 *            The name of the box
	 * @return The box itself
	 */
	public JComboBox createNewAdminBox(String name) {
		AdminBox box = new AdminBox(name, userTypes);
		AdminBoxList.add(box);
		return box;
	}

	/**
	 * Adds an AdminBox to the panel.
	 * 
	 * @param box
	 *            The box to be added
	 */
	public static void addAdminBox(AdminBox box) {
		AdminBoxList.add(box);
	}

	/**
	 * Gets a map of different usernames and their user types.
	 * 
	 * @return The map from names to types.
	 */
	public Map<String, String> getUserTypeMap() {
		return userTypeMap;
	}

	/**
	 * Get a specific user type. The indices are: 1-Guest 2-User 3-Developer
	 * 4-Administrator
	 * 
	 * @param index
	 *            The number of the user type desired
	 * @return The corresponding user type
	 */
	public static String getUserType(int index) {
		return userTypes[index];
	}

	/**
	 * Returns the index of a specific user type.
	 * 
	 * @param type
	 *            The desired user type
	 * @return The corresponding index
	 */
	public static int getUserTypeIndex(String type) {
		for (int i = 0; i < userTypes.length; i++) {
			if (type.equals(userTypes[i]))
				return i;
		}
		return -1;// error
	}

	/**
	 * Returns an array of Strings containing all possible user types.
	 * 
	 * @return An array of all user types
	 */
	public String[] getUserTypes() {
		return userTypes;
	}

	/**
	 * Creates an "Undo changes" button
	 * 
	 * @return The undo button
	 */
	public JButton createUndoButton() {
		JButton undoButton = new JButton("Undo changes");
		undoButton.addActionListener(new UndoEvent());
		return undoButton;
	}

	/**
	 * Changes the user type of a certain user. Uses an index for the type.
	 * 
	 * @param username
	 *            The user to be changed
	 * @param index
	 *            The index of the type to be changed to.
	 */
	public static void changeUserType(String username, int index) {
		changeMap.put(username, index);
	}

	/**
	 * Creates a "Submit Changes" button
	 * 
	 * @return The Submit button
	 */
	public JButton createSubmitButton() {
		JButton submitButton = new JButton("Submit Changes");
		submitButton.addActionListener(new SubmitEvent());
		return submitButton;
	}

	/**
	 * Event for the Undo button.
	 * 
	 * @author Jiaqi Yan, Meng Li, Nick Hawthorne
	 * 
	 */
	public class UndoEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < AdminBoxList.size(); i++) {
				AdminBox box = AdminBoxList.get(i);
				String name = box.getName();
				if (changeMap.containsKey(name)) {
					String type = userTypeMap.get(name);
					AdminBoxList.get(i).changeDisplayContent(
							getUserTypeIndex(type) + 1);
				}
			}
			changeMap.clear();
		}

	}

	/**
	 * Event for the Submit button.
	 * 
	 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
	 * 
	 */
	public class SubmitEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			userHandler.updateUserTypes(changeMap);
			userTypeMap = userHandler.getUserTypeMap();
			JOptionPane.showMessageDialog(null,"Changes committed");
		}

	}

}
