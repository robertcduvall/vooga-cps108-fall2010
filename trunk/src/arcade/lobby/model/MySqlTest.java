package arcade.lobby.model;

public class MySqlTest {

	public static void main(String[] args) {
		String host = "voogaarcade.db.7093929.hostedresource.com";
		String dbName = "voogaarcade";
		String user = dbName;
		String pass = "Vooga108";
		ProfileSet ps = new ProfileSet(host, dbName, "Users", user, pass);
		Profile prof = new Profile(String.valueOf(Math.random()));
		prof.setName("Testy", "McTesterson");
		ps.addProfile(prof);
		for (Profile p : ps) {
			System.out.println(String.format("Name: %s, Username: %s, Birthday: %s",
					p.getFullName(),p.getUserName(),p.getBirthday()));
		}

	}

}
