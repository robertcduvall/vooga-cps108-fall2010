package arcade.core;

import java.util.*;

import arcade.lobby.model.ProfileSet;
import arcade.util.database.MySqlAdapter;

/**
 * High Score controller. Used to upload and retrieve highscores for a specific
 * game or player
 * 
 * @author Yang
 * 
 */
public class HighScoreControl {

	private MySqlAdapter dbAdapter;
	private String tableName;
	private static Map<Integer, String> gameMemo;
	private static Map<Integer, String> playerMemo;

	public HighScoreControl(MySqlAdapter adapter, String tblName) {
		dbAdapter = adapter;
		tableName = tblName;
		gameMemo = new HashMap<Integer, String>();
		playerMemo = new HashMap<Integer, String>();

		initializeMemos();
	}

	private void initializeMemos() {
		for (Map<String, String> m : dbAdapter.getColumns("Profile", "User_Id",
				"FirstName")) {
			playerMemo.put(Integer.parseInt(m.get("User_Id")),
					m.get("FirstName"));
		}
		for (Map<String, String> m : dbAdapter.getColumns("GameInfo", "Id",
				"Title")) {
			gameMemo.put(Integer.parseInt(m.get("Id")), m.get("Title"));
		}
	}

	/**
	 * Add a score to the database
	 * 
	 * @param player
	 *            player identifier (username/name)
	 * @param gameID
	 *            game name
	 * @param score
	 *            score
	 * @return true if successful, false otherwise
	 */
	public boolean addScore(int playerID, int gameID, double score) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Player_Id", playerID + "");
		row.put("Game_Id", gameID + "");
		row.put("Score", score + "");
		return dbAdapter.insert(tableName, row);
	}

	/**
	 * Checks to see if the current score is a new personal high score on a game
	 * 
	 * @param playerID
	 *            player identifier (username/name)
	 * @param gameID
	 *            game name
	 * @param score
	 *            score
	 * @return true if it's a new high score, false otherwise
	 */
	public boolean isHighScore(int playerID, int gameID, String score) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Player_Id", playerID + "");
		conditions.put("Game_Id", gameID + "");
		List<Map<String, String>> rows = dbAdapter.getRows(tableName,
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
	 * @param gameID
	 *            game name
	 * @param numScores
	 *            number of scores to display
	 * @return a list of values returned by the query
	 */
	public List<Map<String, String>> getGameHighScores(int gameID, int numScores) {
		// Query contruction
		// String query = "SELECT Player_Id, Score " +
		// " FROM " + myTable +
		// " WHERE Game_Id = '"+ gameID + "' " +
		// " ORDER BY Score DESC" +
		// " LIMIT 0 , " + numScores;
		// List<Map<String, String>> rows = myDbAdapter.getRows(query);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Game_Id", gameID + "");

		return fillPlayerInfo(dbAdapter.getRows(tableName, conditions, "Score",
				false, numScores, "Player_Id", "Score"));
	}

	/**
	 * Get the high scores for a player
	 * 
	 * @param playerID
	 *            player identifier (username/name)
	 * @param numScores
	 *            number of scores to display
	 * @return a list of values returned by the query
	 */
	public List<Map<String, String>> getPlayerHighScores(int playerID,
			int numScores) {
		return getPlayerHighScores(playerID, numScores, "Score");
	}

	public List<Map<String, String>> getPlayerHighScores(int playerID,
			int numScores, String sortBy) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Player_Id", playerID + "");
		return fillGameInfo(dbAdapter.getRows(tableName, conditions, sortBy,
				false, numScores, "Game_Id", "Score"));
	}

	public List<Map<String, String>> getHighScores(int playerID, int gameID,
			int numScores) {
		return getHighScores(playerID, gameID, numScores, "Score");
	}

	public List<Map<String, String>> getHighScores(int playerID, int gameID,
			int numScores, String sortBy) {

		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Player_Id", playerID + "");
		conditions.put("Game_Id", gameID + "");
		return fillAllInfo(dbAdapter.getRows(tableName, conditions, sortBy,
				false, numScores, "Score"));
	}

	private List<Map<String, String>> fillGameInfo(
			List<Map<String, String>> queryResults) {
		return fillInfo(queryResults, true, false);
	}

	private List<Map<String, String>> fillPlayerInfo(
			List<Map<String, String>> queryResults) {
		return fillInfo(queryResults, false, true);
	}

	private List<Map<String, String>> fillAllInfo(
			List<Map<String, String>> queryResults) {
		return fillInfo(queryResults, true, true);
	}

	private List<Map<String, String>> fillInfo(
			List<Map<String, String>> queryResults, boolean game, boolean player) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Map<String, String> m : queryResults) {
			Map<String, String> scores = new HashMap<String, String>();
			if (player) {
				int pid = Integer.parseInt(m.get("Player_Id"));
				String pname = ProfileSet.getProfile(pid).getUserName();//playerMemo.get(pid);
				scores.put("PlayerName", pname);
			}
			if (game) {
				int gid = Integer.parseInt(m.get("Game_Id"));
				String gname = gameMemo.get(gid);
				scores.put("GameName", gname);
			}
			scores.put("Score", m.get("Score"));
			list.add(scores);
		}
		return list;
	}
}
