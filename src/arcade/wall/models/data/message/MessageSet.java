package arcade.wall.models.data.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.wall.models.data.DataSet;
import arcade.wall.models.data.gamereport.GameReport;

/**
 * A MessageSet contains all the VOOGA Messages. It is linked to our online database.
 * @author John, David Herzka
 *
 */
public class MessageSet extends DataSet {

	public MessageSet(String host, String dbName, String tableName,
			String user, String pass) {
		super(host, dbName, tableName, user, pass);
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
}
