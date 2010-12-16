//package arcade.wall.models.data;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import arcade.util.database.DatabaseAdapter;
//import arcade.util.database.MySqlAdapter;
//
///**
// * DataSet acts as a manager of Datum objects, which are linked to our online database tables.
// * @author John
// *
// */
//public class DataSet {
//
//	public static DatabaseAdapter myDbAdapter;
////	public String myTable;
////	public int currentID;
//	
//	public DataSet(String host, String dbName, String tableName,
//			String user, String pass) {
//		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
////		myTable = tableName;
////		currentID = size();
//	}
//	
////	/**
////	 * Returns the size of the DataSet (number of rows).
////	 */
////	public int size() {
////		List<String> col = myDbAdapter.getColumn(myTable, "Id");
////		return col.size();
////	}
//	
////	/**
////	 * Updates a matching row's field in the database to a new value.
////	 */
////	public boolean updateField(String fieldToMatch, String valueToMatch, String fieldToUpdate, String newValue) {
////		Map<String, String> row = new HashMap<String, String>();
////		row.put(fieldToUpdate, ""+newValue);
////		return myDbAdapter.update(myTable, fieldToMatch, valueToMatch, row);
////	}
////	
////	/**
////	 * Retrieves the value of a desired field of a matched row.
////	 */
////	public String getValue(String fieldToMatch, String valueToMatch, String desiredField) {
////		Map<String, String> row = myDbAdapter.getRows(myTable, fieldToMatch, valueToMatch).get(0);
////		return row.get(desiredField);
////	}
//	
//}
