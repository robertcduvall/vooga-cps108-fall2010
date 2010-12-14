package arcade.core.examples;

import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import arcade.core.Arcade;
import arcade.core.HighScoreControl;
import arcade.core.Panel;

public class Profile extends Panel {
	private static HighScoreControl hsc = new HighScoreControl(Arcade.myDbAdapter,
	"HighScores");
	private static double score;
	private static String playerName = "Guest";
	
	public Profile() {
		score = 0;
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = icon.getImage().getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		JLabel label = new JLabel(icon);
		
		JLabel nameLabel = new JLabel(playerName + " Avatar");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(nameLabel);
		add(label);
		add(getPlayerHighScoresPanel(playerName, 5));
	}
	
	public static void updateHighScore(double highScore) {
		score = highScore;
		new HighScore(gameName);
	}

	public static boolean addHighScore() {
		boolean isAdded = hsc.addScore(playerName, gameName, score);
		columnar.setLeftComponent(makeLeftPanel());
		mainPanel.setRightComponent(makeRightPanel());
		return isAdded;
	}
	
	public static void setPlayer(String player){
		playerName = player;
	}
	
	public static JTextPane getPlayerHighScoresPanel(String playerName,
			int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = playerFormat(playerName, numScores,
				hsc.getPlayerHighScores(playerName, numScores,"id"));
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
		String result = "<h3 align=center>" + playerName + "'s Recent Scores"
				+ "</h3>";
		result += "<table align=\"Center\">"
				+ "<tr><th></th><th>Game</th><th>Score</th></tr>";
		int i = 1;
		for (Map<String, String> row : rows) {
			result += "<tr><td>" + i + "</td><td>" + row.get("Game")
					+ "</td><td>" + row.get("Score") + "</td></tr>";
			i++;
		}
		result += "</table>";
		return result;
	}
}