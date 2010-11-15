package vooga.examples.resource.profile;

import vooga.engine.resource.DBHandler;
import vooga.engine.resource.Entry;

/**
 * 
 * @author David G. Herzka
 *
 */
public class ProfileManager {
	
	public ProfileManager() {
		DBHandler.open("src/vooga/examples/resource/profile/profileDB.sqlite");
		DBHandler.createTable("Profiles", "username", "backgroundColor");
		DBHandler.setTable("Profiles");
	}

	public void setColor(String user, Integer color) {
		DBHandler.deleteRow(new Entry<String>("username", user));
		DBHandler.addRow(new Entry<String>("username", user),
				new Entry<Integer>("backgroundColor", color));
	}

	public Integer getColor(String user) {
		String[] users = DBHandler.getColumnStrings("username");
		Integer[] colors = DBHandler.getColumnInts("backgroundColor");
		int userIndex = -1;
		for(int i = 0; i < users.length; i++) {
			if(users[i].equals(user)) userIndex = i;
		}
		return colors[userIndex];
	}
	
	public String[] getUsers() {
		return DBHandler.getColumnStrings("username");
	}
}
