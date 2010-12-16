package arcade.wall.models.data.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

/**
 * A MessageSet contains all the VOOGA Messages. It is linked to our online database.
 * @author John Kline
 * @author David Herzka
 */
public class MessageSet {

	private static DatabaseAdapter myDbAdapter;
	private static String myTable;
	public static int currentID;
	
	public MessageSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
		currentID = size();
	}

	/**
	 * Returns the size of the MessageSet (number of rows).
	 */
	public int size() {
		List<String> col = myDbAdapter.getColumn(myTable, "Id");
		return col.size();
	}
	
	/**
	 * Inserts a new row into the database, based on the given Message.
	 * @param message
	 * 		The message to add
	 * @return
	 * 		Whether the addition was successful
	 */
	public boolean addMessage(Message message) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Id", message.getId());
		row.put("Sender", message.getSender());
		row.put("Receiver", message.getReceiver());
		row.put("Content", message.getContent());
		currentID++;
		return myDbAdapter.insert(myTable, row);
	}
	
	/**
	 * Returns all Messages whose values match the given parameters.
	 * @param fieldName - the field name to be considered
	 * @param value - the value to match
	 * @return a List of all the Messages that match
	 */
	public List<Message> getMessagesByField(String fieldName, String value) {
		List<Message> returnMessages = new ArrayList<Message>();
		for (Map<String, String> row: myDbAdapter.getRows(myTable, fieldName, value)) {
			returnMessages.add(new Message( 
					row.get("Id"),
					row.get("Sender"),
					row.get("Receiver"),
					row.get("Content")
					));
		}
		return returnMessages;
	}
	
	/**
	 * Updates a matching row's field in the database to a new value.
	 */
	public boolean updateField(String fieldToMatch, String valueToMatch, String fieldToUpdate, String newValue) {
		Map<String, String> row = new HashMap<String, String>();
		row.put(fieldToUpdate, ""+newValue);
		return myDbAdapter.update(myTable, fieldToMatch, valueToMatch, row);
	}
	
	/**
	 * Retrieves the value of a desired field of a matched row.
	 */
	public String getValue(String fieldToMatch, String valueToMatch, String desiredField) {
		Map<String, String> row = myDbAdapter.getRows(myTable, fieldToMatch, valueToMatch).get(0);
		return row.get(desiredField);
	}
}
