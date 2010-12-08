package arcade.wall.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

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
		List<String> col = myDbAdapter.getColumn(myTable, "User_Name");
		return col.size();
	}

	public boolean addComment(Comment comment) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Game_Name", comment.getGameName());
		row.put("User_Name", comment.getUserName());
		row.put("Comment_String", comment.getCommentString());
		row.put("Rating", comment.getRating());
		return myDbAdapter.insert(myTable, row);
	}
	
	//Doesn't work yet
	public boolean updateCommentRating(Comment comment) {
		Map<String, String> row = new HashMap<String, String>();
		return myDbAdapter.update(myTable, comment.getRating(), row);
	}

	public Comment getComment(int rowNo) {
		return new Comment(myDbAdapter.getColumn(myTable, "Game_Name").get(rowNo),
						   myDbAdapter.getColumn(myTable, "User_Name").get(rowNo),
						   myDbAdapter.getColumn(myTable, "Comment_String").get(rowNo),
						   myDbAdapter.getColumn(myTable, "Rating").get(rowNo));
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
