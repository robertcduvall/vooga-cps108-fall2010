package arcade.core.components;

import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import arcade.core.Arcade;
import arcade.core.ArcadeGUIElements.ArcadeTab;
import arcade.core.ArcadeGUIElements.GameView;
import arcade.core.api.Panel;
import arcade.core.controllers.HighScoreControl;
import arcade.core.examples.HighScore;
import arcade.lobby.model.ProfileSet;

/**
 * A Panel to be added to the Arcade Tab which displays the top 5 high scores for the current game
 * 
 * @author Aaron Choi, Derek Zhou, Yang Su
 *
 */

@SuppressWarnings("serial")
public class HighScorePanel extends Panel{
	private static HighScoreControl hsc = new HighScoreControl(Arcade.myDbAdapter,
	"HighScores");
	private static JComponent highScores;
	private static double score;
	
	public HighScorePanel()
	{
		highScores = getGameHighScoresPanel(GameView.getGame(),GameView.getGameID(), 5);
		add(highScores);
	}
	
	private static JTextPane getGameHighScoresPanel(String gameName, int gameID,
			int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = gameFormat(gameName, numScores,
				hsc.getGameHighScores(gameID, numScores));
		textPane.setEditable(false);

		textPane.setText(description);
		textPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		return textPane;
	}
	
	/**
	 * Creates a new HighScore object asking the user if they want to submit their high score
	 * 
	 * @param highScore The latest High Score for the game
	 */
	public static void updateHighScore(double highScore) {
		score = highScore;
		new HighScore();
	}

	/**
	 * The latest high score is added to the database, and the panels are refreshed.
	 * 
	 * @return If the high score was added correctly
	 */
	public static boolean addHighScore() {
		boolean isAdded = hsc.addScore(ProfileSet.getCurrentProfile().getUserId(),
				GameView.getGameID(), score);
		ArcadeTab.refreshPanels();
		return isAdded;
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
	private static String gameFormat(String gameName,int numScores,
			List<Map<String, String>> rows) {
		String result = "<body bgcolor=\"#EDEDED\" ><h2 align=center>" + "Top " + numScores
				+ " Scores for " + gameName + "</h2>";
		result += "<table align=\"Center\" >"
				+ "<tr><th></th><th>Player</th><th>Score</th></tr>";
		int i = 1;
		for (Map<String, String> row : rows) {
			String pname=(row.get("PlayerName")==null) ?"Guest":row.get("PlayerName");
			result += "<tr><td>" + i + "</td><td>" + pname
					+ "</td><td>" + row.get("Score") + "</td></tr>";
			i++;
		}
		result += "</table></body>";
		return result;
	}
}
