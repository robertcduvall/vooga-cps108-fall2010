package arcade.core.examples;

import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import arcade.core.Arcade;
import arcade.core.GameView;
import arcade.core.HighScoreControl;
import arcade.core.Panel;
import arcade.lobby.model.ProfileSet;

public class HighScorePanel extends Panel{
	private static HighScoreControl hsc = new HighScoreControl(Arcade.myDbAdapter,
	"HighScores");
	private static JComponent highScores;
	private static double score;
	
	public HighScorePanel()
	{
		highScores = getGameHighScoresPanel(GameView.getGame(),GameView.getGameID(), 5);
		//add(new JLabel("HIGHSCORES!!!"));
		add(highScores);
	}
	
	public static JTextPane getGameHighScoresPanel(String gameName, int gameID,
			int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		//TODO
		String description = gameFormat(gameName, numScores,
				hsc.getGameHighScores(gameID, numScores));
//		String description="test";
		textPane.setEditable(false);

		textPane.setText(description);
		textPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		return textPane;
	}
	
	public static void updateHighScore(double highScore) {
		score = highScore;
		new HighScore();
	}

	public static boolean addHighScore() {
		boolean isAdded = hsc.addScore(ProfileSet.getCurrentProfile().getUserId(),
				GameView.getGameID(), score);
		Arcade.refreshLeft();
//		mainPanel.setRightComponent(makeRightPanel());
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
}
