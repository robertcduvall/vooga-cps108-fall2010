package arcade.core.examples;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;

import arcade.core.Arcade;
import arcade.core.GameView;
import arcade.core.HighScoreControl;
import arcade.core.Panel;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

public class ArcadeProfile extends Panel {
	private static HighScoreControl hsc = new HighScoreControl(Arcade.myDbAdapter,
	"HighScores");
	private static double score;
	private static int playerID = 1;
	private static final int AVATAR_WIDTH = 100;
	private static final String AVATAR_DEFAULT = "http://imgur.com/29J5j.png";
	private Profile myProfile;
	
	public ArcadeProfile() {
		myProfile = ProfileSet.getCurrentProfile();
		score = 0;
		
		initialize();
		//add(getPlayerHighScoresPanel(playerName, 5));
	}
	
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel avatar = null;
		try {
			String avatarPath = myProfile == null
					|| myProfile.getAvatar() == null
					|| myProfile.getAvatar().isEmpty() ? AVATAR_DEFAULT
					: myProfile.getAvatar();
			ImageIcon icon = new ImageIcon(ImageIO.read(new URL(avatarPath)));
			scaleImage(icon);
			avatar = new JLabel(icon);
		} catch (MalformedURLException e) {
			avatar = new JLabel("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel nameLabel = new JLabel(myProfile.getUserName());
		add(nameLabel);
		add(avatar);
		add(new JLabel("Name:"));
		add(new JLabel(myProfile.getFullName()));
		add(getPlayerHighScoresPanel(myProfile.getUserId(), 5));
	}
	
//	public static void updateHighScore(double highScore) {
//		score = highScore;
//		new HighScore();
//	}

//	public static boolean addHighScore() {
//		boolean isAdded = hsc.addScore(playerID, GameView.getGameID(), score);
////		columnar.setLeftComponent(makeLeftPanel());
////		mainPanel.setRightComponent(makeRightPanel());
//		return isAdded;
//	}
	
	public JTextPane getPlayerHighScoresPanel(int playerID,
			int numScores) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = playerFormat(myProfile.getFullName(), numScores,
				hsc.getPlayerHighScores(playerID, numScores,"id"));
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
			result += "<tr><td>" + i + ". </td><td>" + row.get("GameName")
					+ "</td><td>" + row.get("Score") + "</td></tr>";
			i++;
		}
		result += "</table>";
		return result;
	}
	
	public void refresh(Profile newProfile) {
		myProfile = newProfile;
		removeAll();
		initialize();
	}
	
	private void scaleImage(ImageIcon icon) {
		Image image = icon.getImage();
		icon.setImage(image.getScaledInstance(AVATAR_WIDTH,
				AVATAR_WIDTH * icon.getIconHeight() / icon.getIconWidth(), 0));
	}
}