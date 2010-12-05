package arcade.lobby.model;

public class MySqlTest {
	
	public static void main(String[] args) {
		String host = "voogaarcade.db.7093929.hostedresource.com";
		String dbName = "voogaarcade";
		String user = dbName;
		String pass = "Vooga108";
		DatabaseAdapter db = new MySqlAdapter(host, dbName, user, pass);
		
		printColumn(db,"User_Name");
	}
	
	private static void printColumn(DatabaseAdapter db, String field) {
		String[] userNames = db.getColumn("users", field);
		for(String s : userNames) {
			System.out.println(s);
		}
	}

}
