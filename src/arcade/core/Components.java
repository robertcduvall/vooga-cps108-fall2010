package arcade.core;

import java.util.List;
import java.util.Map;

import javax.swing.*;
import arcade.util.database.Constants;

/**
 * Reusable components
 * 
 * @author Yang
 * 
 */
public class Components {
	private static HighScoreControl hsu = new HighScoreControl(Constants.HOST,
			Constants.DBNAME, Constants.USER, Constants.PASSWORD, "HighScores");

	public static JTextPane getGameHighScoresPanel(String gameName,
			int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = gameFormat(gameName, numScores,
				hsu.getGameHighScores(gameName, numScores));
		textPane.setEditable(false);

		textPane.setText(description);
		textPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		return textPane;
	}

	/**
	 * TODO: FEEL FREE TO CHANGE Format the output of the query to display high
	 * scores for a game
	 * 
	 * @param gameName
	 *            game name
	 * @param numScores
	 *            number of scores to display
	 * @param rows
	 *            resulting data of the query
	 * @return a formatted string containing high scores for a game
	 */
	private static String gameFormat(String gameName, int numScores,
			List<Map<String, String>> rows) {
		String result = "<h2 align=center>" + "Top " + numScores
				+ " Scores for " + gameName + "</h2>";
		result += "<table align=\"Center\">"
				+ "<tr><th></th><th>Player</th><th>Score</th></tr>";
		int i = 1;
		for (Map<String, String> row : rows) {
			result += "<tr><td>" + i + "</td><td>" + row.get("Player")
					+ "</td><td>" + row.get("Score") + "</td></tr>";
			i++;
		}
		result += "</table>";
		return result;
	}

	public static JTextPane getPlayerHighScoresPanel(String playerName,
			int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = playerFormat(playerName, numScores,
				hsu.getPlayerHighScores(playerName, numScores));
		textPane.setEditable(false);

		textPane.setText(description);
		textPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		return textPane;
	}

	/**
	 * TODO: FEEL FREE TO CHANGE Format the output of the query to display high
	 * scores for a player using HTML
	 * 
	 * @param playerName
	 *            player name
	 * @param numScores
	 *            number of scores to display
	 * @param rows
	 *            resulting data of the query
	 * @return a formatted string containing high scores for a player
	 */
	private static String playerFormat(String playerName, int numScores,
			List<Map<String, String>> rows) {
		String result = "<h3 align=center>" + playerName + "'s Top Scores"
				+ "</h3>";
		result += "<table align=\"Center\">"
				+ "<tr><th></th><th>Game</th><th>Score</th></tr>";
		int i = 1;
		for (Map<String, String> row : rows) {
			result += "<tr><td>" + i + "</td><td>" + row.get("Game")
					+ "</td><td>" + row.get("Score") + "</td></tr>";
			i++;
		}
		return result;
	}

	public static JTextPane getHighScoresPanel(String playerName,
			String gameName, int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = formatAll(playerName, gameName, numScores,
				hsu.getHighScores(playerName, gameName, numScores));
		textPane.setEditable(false);

		textPane.setText(description);
		textPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		return textPane;
	}

	private static String formatAll(String playerName, String gameName,
			int numScores, List<Map<String, String>> rows) {
		String result = "<h3 align=center>" + playerName + "'s Top Scores on "
				+ gameName + "</h3>";
		result += "<table align=\"Center\">"
				+ "<tr><th></th><th>Score</th></tr>";
		int i = 1;
		for (Map<String, String> row : rows) {
			result += "<tr><td>" + i + "</td><td>" + row.get("Score")
					+ "</td></tr>";
			i++;
		}
		return result;
	}
}
