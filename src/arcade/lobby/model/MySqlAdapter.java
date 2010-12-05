package arcade.lobby.model;

import java.util.Map;

public class MySqlAdapter implements DatabaseAdapter {

	public MySqlAdapter(String host, String dbName, String user, String pass) {
		connect(host, user, pass);
		selectDB(dbName);
	}

	private boolean connect(String host, String user, String pass) {
		return false;

	}

	private boolean selectDB(String dbName) {
		return false;

	}

	@Override
	public String[] getColumn(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumn(String tableName, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRow(String pkName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(String[] columns, String[] values) {
		// TODO Auto-generated method stub
		return false;
	}

}
