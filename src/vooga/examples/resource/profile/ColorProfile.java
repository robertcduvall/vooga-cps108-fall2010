package vooga.examples.resource.profile;

import java.util.List;

import vooga.engine.resource.DBHandler;
import vooga.engine.resource.Entry;

/**
 * 
 * @author David G. Herzka
 *
 */
public class ColorProfile {
	
	public ColorProfile() {
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
		List<String> users = DBHandler.getColumn("username",new String());
		List<Integer>colors = DBHandler.getColumn("backgroundColor",new Integer(0));
		int userIndex = -1;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).equals(user)) userIndex = i;
		}
		return colors.get(userIndex);
	}
	
	public String[] getUsers() {
		return DBHandler.getColumn("username").toArray(new String[0]);
	}
}
