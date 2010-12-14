package arcade.core;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import arcade.core.examples.HighScore;
import arcade.lobby.model.ProfileSet;
import arcade.util.database.Constants;
import arcade.util.database.MySqlAdapter;

/**
 * This is the example GUI for arcade. It contains scrollable and adjustable
 * panels for the game splash screen, instructions, avatar display, friend lobby
 * display, advertisements, game rating, and display of other games. Most of the
 * components are currently blank, but will be added once each component is done
 * by other groups. The start button will run the game currently on view.
 * 
 * 
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
public class ExampleGUI extends Tab {
	private static HighScoreControl hsc = new HighScoreControl(
			Arcade.myDbAdapter, "HighScores");

	// private static String gameName = "Zombieland";
	// private static String playerName = "Guest";
	private static int gameID = 3;
	private static int playerID = 5;
	private static JPanel content;
	private static JPanel left;
	private static JSplitPane mainPanel;
	private static JComponent highScores;
	private static JSplitPane columnar;
	private static double score;

	public ExampleGUI() {
		score = 0;
		setName("Arcade");
		setToolTipText("Arcade main view");
	}

	public JComponent getContent() {

		columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, makeLeftPanel(),
				content);
		columnar.setOneTouchExpandable(true);

		mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, columnar,
				makeRightPanel());
		mainPanel.setOneTouchExpandable(true);

		refreshContent();
		return mainPanel;
	}

	public static void setPlayer(String player) {
		mainPanel.setRightComponent(makeRightPanel());
	}

	private static void refreshContent() {
		content = new GameView(gameID);
		GameView.initialize();
		columnar.setRightComponent(GameView.getContent());
	}

	public static void setGame(int id) {
		gameID = id;
		// gameName = name;
		refreshContent();
		columnar.setLeftComponent(makeLeftPanel());
		mainPanel.setRightComponent(makeRightPanel());
	}

	public static void updateHighScore(double highScore) {
		score = highScore;
		new HighScore();
	}

	public static boolean addHighScore() {
		boolean isAdded = hsc.addScore(ProfileSet.currentProfile.getUserId(),
				gameID, score);
		columnar.setLeftComponent(makeLeftPanel());
		mainPanel.setRightComponent(makeRightPanel());
		return isAdded;
	}

	// makes the left hand side panel
	private static JComponent makeLeftPanel() {
		left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

		JLabel rateThis = new JLabel("Rate This Game");

		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = icon.getImage().getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		JLabel label = new JLabel(icon);

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setForeground(Color.BLUE);

		JLabel rateOthers = new JLabel("Rate These Other Games");

		JLabel moreLabels = new JLabel(icon);

		String gameName = "";
		highScores = getGameHighScoresPanel(gameName, 5);
		highScores = new JPanel();
		left.add(rateThis);
		left.add(label);
		left.add(separator);
		left.add(highScores);
		left.add(separator);
		left.add(rateOthers);
		left.add(moreLabels);

		left.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		return left;
	}

	// makes the right hand side panel
	private static JComponent makeRightPanel() {
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(3, 0));

		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = icon.getImage().getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		JLabel label = new JLabel(icon);

		// adds panels to the grid layout
		JPanel playerAvatar = new JPanel();
		playerAvatar.setLayout(new BoxLayout(playerAvatar, BoxLayout.Y_AXIS));
		playerAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		String pn;
		// TODO pn returns null;
		// pn=ProfileSet.currentProfile.getFirstName();
		pn = "Guest";
		JLabel player = new JLabel(pn + "s Avatar");
		playerAvatar.add(player);
		playerAvatar.add(label);
		// playerAvatar.add(getPlayerHighScoresPanel(pn, 5));

		JPanel lobby = new JPanel();
		lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
		lobby.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lobby.add(new JLabel("Friends"));

		JLabel moreLabels = new JLabel(icon);
		lobby.add(moreLabels);

		JPanel ads = new JPanel();
		ads.setLayout(new BoxLayout(ads, BoxLayout.Y_AXIS));

		ads.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel randomAd = new JLabel("Buy Coke.");
		ads.add(randomAd);

		JLabel evenMoreLabels = new JLabel(icon);
		ads.add(evenMoreLabels);

		right.add(playerAvatar);
		right.add(lobby);
		right.add(ads);

		right.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		return right;
	}

	public static JTextPane getGameHighScoresPanel(String gameName,
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
	 * display high scores for a game
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
				hsc.getPlayerHighScores(ProfileSet.currentProfile.getUserId(),
						numScores, "Id"));
		textPane.setEditable(false);

		textPane.setText(description);
		textPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		return textPane;
	}

	/**
	 * Format the output of the query to display high scores for a player using
	 * HTML
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

	public static JTextPane getHighScoresPanel(String playerName,
			String gameName, int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = formatAll(playerName, gameName, numScores,
				hsc.getHighScores(playerID, gameID, numScores));
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
		if (rows != null) {
			for (Map<String, String> row : rows) {
				result += "<tr><td>" + i + "</td><td>" + row.get("Score")
						+ "</td></tr>";
				i++;
			}
			result += "</table>";
		}
		return result;
	}

}
