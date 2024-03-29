package arcade.wall.models.data.gamereport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

/**
 * A GameReportSet contains all the VOOGA GameReports. It is linked to our online database.
 * @author John Kline
 * @author David Herzka
 */
public class GameReportSet { 

	private static DatabaseAdapter myDbAdapter;
	private static String myTable;
	public static int currentID;
	
	public GameReportSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
		currentID = size();
	}

	/**
	 * Returns the size of the GameReportSet (number of rows).
	 */
	public int size() {
		List<String> col = myDbAdapter.getColumn(myTable, "Id");
		return col.size();
	}
	
	/**
	 * Inserts a new row into the database, based on the given GameReport.
	 * @param gameReport
	 * 		The gameReport to add
	 * @return
	 * 		Whether the addition was successful
	 */
	public boolean addGameReport(GameReport gameReport) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Id", gameReport.getId());
		row.put("GameInfo_Title", gameReport.getGameInfoTitle());
		row.put("AverageRating", gameReport.getAverageRating());
		row.put("NumberOfComments", gameReport.getNumberOfComments());
		currentID++;
		return myDbAdapter.insert(myTable, row);
	}

	/**
	 * Uses the DatabaseAdapter.update() method to update an entry in the database
	 * @param gameInfoTitle
	 * 		The title of the game being considered
	 * @param newRating
	 * 		The new averageRating to assign to the gameReport
	 * @return
	 * 		Whether the update was successful
	 */
	public boolean updateAverageRating(String gameInfoTitle, double newRating) {
		return updateField("GameInfo_Title", gameInfoTitle, "AverageRating", newRating+"");
	}
	
	/**
	 * Returns all GameReports whose values match the given parameters.
	 * @param fieldName - the field name to be considered
	 * @param value - the value to match
	 * @return a List of all the GameReports that match
	 */
	public List<GameReport> getGameReportsByField(String fieldName, String value) {
		List<GameReport> returnGameReports = new ArrayList<GameReport>();
		for (Map<String, String> row: myDbAdapter.getRows(myTable, fieldName, value)) {
			returnGameReports.add(new GameReport( 
					row.get("Id"),
					row.get("GameInfo_Title"),
					row.get("AverageRating"),
					row.get("NumberOfComments")
					));
		}
		return returnGameReports;
	}

	/**
	 * Retrieves the AverageRating for a Game.
	 */
	public double getAverageRating(String gameName) {
		return Double.parseDouble(getValue("GameInfo_Title", gameName, "AverageRating"));
	}

	public void incrementComments(String gameTitle) {
		GameReport gameReport = getGameReportsByField("GameInfo_Title", gameTitle).get(0);
		int numberOfComments = Integer.parseInt(gameReport.getNumberOfComments());
		numberOfComments++;
		updateNumberOfComments(gameTitle, numberOfComments);
	}
	
	public boolean updateNumberOfComments(String gameInfoTitle, int newNumberOfComments) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("NumberOfComments", ""+newNumberOfComments);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("GameInfo_Title", gameInfoTitle);
		return myDbAdapter.update(myTable, conditions, row);
	}

	public String getTopRatedGame() {
		List<String> averageRatingStrings = myDbAdapter.getColumn(myTable, "AverageRating");
		double maxRating = 0;
		for (String s: averageRatingStrings) {
			double newRating = Double.parseDouble(s);
			maxRating = Math.max(maxRating, newRating);
		}
		GameReport gameReport = getGameReportsByField("AverageRating", maxRating+"").get(0);
		return gameReport.getGameInfoTitle();
	}

	/**
	 * Uses an SQL query to construct the GameRankings list.
	 */
	public List<String> getGameRankList() {
		Map<String,String> emptyConditional = new HashMap<String,String>();
		List<Map<String, String>> list = ((MySqlAdapter)(myDbAdapter)).getRows(
		"GameReports", emptyConditional,"AverageRating", false, 100000,"GameInfo_Title", "AverageRating");
		List<String> returnList = new ArrayList<String>();
		for (Map<String, String> row: list) {
			returnList.add(row.get("GameInfo_Title"));
		}
		return returnList;
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
