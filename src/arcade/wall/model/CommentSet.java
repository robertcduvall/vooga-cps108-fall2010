package arcade.wall.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import arcade.lobby.model.DatabaseAdapter;
import arcade.lobby.model.MySqlAdapter;

public class CommentSet implements Iterable<Comment> {
	
	public Comment currentComment = null;
	public DatabaseAdapter myDbAdapter;
	public String myTable;

	public CommentSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
	}

	public int size() {
		String[] col = myDbAdapter.getColumn(myTable, "User_Name");
		return col.length;
	}

	public boolean addComment(Comment comment) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Game_Name", comment.getGameName());
		row.put("User_Name", comment.getUserName());
		row.put("Comment_String", comment.getCommentString());
		return myDbAdapter.insert(myTable, row);
	}
	
	public boolean updateComment(Comment comment) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Comment_String", comment.getCommentString());
		return myDbAdapter.update(myTable, comment.getUserName(), row);
	}

	public Comment getComment(int rowNo) {
		return new Comment(myDbAdapter.getColumn(myTable, "Game_Name")[rowNo],
						   myDbAdapter.getColumn(myTable, "User_Name")[rowNo],
						   myDbAdapter.getColumn(myTable, "Comment_String")[rowNo]);
	}

	@Override
	public Iterator<Comment> iterator() {
		return new Iterator<Comment>() {

			int mySize = size();
			int myLoc = 0;

			@Override
			public boolean hasNext() {
				return myLoc < mySize;
			}

			@Override
			public Comment next() {
				return getComment(myLoc++);
			}

			@Override
			/**
			 * This method will not be implemented
			 */
			public void remove() {
				throw (new UnsupportedOperationException());
			}

		};
	}
}
