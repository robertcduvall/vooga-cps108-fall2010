package arcade.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.util.database.MySqlAdapter;

/**
 * High Score controller. Used to upload and retrieve highscores for a specific
 * game or player
 * 
 * @author Yang
 * 
 */
public class HighScoreControl {

	private MySqlAdapter myDbAdapter;
	private String myTable;

	public HighScoreControl(MySqlAdapter adapter, String tableName) {
		myDbAdapter = adapter;
		myTable = tableName;
	}

	/**
	 * Add a score to the database
	 * 
	 * @param player
	 *            player identifier (username/name)
	 * @param gameName
	 *            game name
	 * @param score
	 *            score
	 * @return true if successful, false otherwise
	 */
	public boolean addScore(String player, String gameName, double score) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Player", player);
		row.put("Game", gameName);
		row.put("Score", score + "");
		return myDbAdapter.insert(myTable, row);
	}

	/**
	 * Checks to see if the current score is a new personal high score on a game
	 * 
	 * @param playerName
	 *            player identifier (username/name)
	 * @param gameName
	 *            game name
	 * @param score
	 *            score
	 * @return true if it's a new high score, false otherwise
	 */
	public boolean isHighScore(String playerName, String gameName, String score) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Player", playerName);
		conditions.put("Game", gameName);
		List<Map<String, String>> rows = myDbAdapter.getRows(myTable,
				conditions, "Score", false, 1, "Score");
		for (Map<String, String> row : rows) {
			if (Double.parseDouble(score) > Double
					.parseDouble(row.get("Score")))
				return true;
		}
		return false;
	}

	/**
	 * Get the high scores for a game
	 * 
	 * @param gameName
	 *            game name
	 * @param numScores
	 *            number of scores to display
	 * @return a list of values returned by the query
	 */
	public List<Map<String, String>> getGameHighScores(String gameName,
			int numScores) {
		// Query contruction
		// String query = "SELECT Player, Score " +
		// " FROM " + myTable +
		// " WHERE Game = '"+ gameName + "' " +
		// " ORDER BY Score DESC" +
		// " LIMIT 0 , " + numScores;
		// List<Map<String, String>> rows = myDbAdapter.getRows(query);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Game", gameName);
		return myDbAdapter.getRows(myTable, conditions, "Score", false,
				numScores, "Player", "Score");
	}

	/**
	 * Get the high scores for a player
	 * 
	 * @param playerName
	 *            player identifier (username/name)
	 * @param numScores
	 *            number of scores to display
	 * @return a list of values returned by the query
	 */
	public List<Map<String, String>> getPlayerHighScores(String playerName,
			int numScores) {
		return getPlayerHighScores(myTable, numScores, "Score");
	}

	public List<Map<String, String>> getPlayerHighScores(String playerName,
			int numScores, String sortBy) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Player", playerName);
		return myDbAdapter.getRows(myTable, conditions, sortBy, false,
				numScores, "Game", "Score");
	}

	public List<Map<String, String>> getHighScores(String playerName,
			String gameName, int numScores) {
		return getHighScores(playerName, myTable, numScores, "Score");
	}

	public List<Map<String, String>> getHighScores(String playerName,
			String gameName, int numScores, String sortBy) {

		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Player", playerName);
		conditions.put("Game", gameName);
		return myDbAdapter.getRows(myTable, conditions, sortBy, false,
				numScores, "Score");
	}
}
