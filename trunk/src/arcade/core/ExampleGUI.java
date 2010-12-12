package arcade.core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import arcade.core.examples.HighScore;
import arcade.util.database.Constants;

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
	static HighScoreControl hsu = new HighScoreControl(Constants.HOST,
			Constants.DBNAME, Constants.USER, Constants.PASSWORD,
			"HighScores");
	private Toolkit tk;

	private static String gameName = "zombieland";
	private static String playerName = "Guest";
	private static JPanel content;
	private static JSplitPane columnar;
	private static double score;
	public ExampleGUI() {
		score=0;
		tk = Toolkit.getDefaultToolkit();
		setName("Arcade");
		setToolTipText("Arcade main view");
	}

	public JComponent getContent() {

		columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, makeLeftPanel(),
				content);
		columnar.setOneTouchExpandable(true);
		columnar.setDividerLocation(.25);

		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				columnar, makeRightPanel());
		mainPanel.setOneTouchExpandable(true);
		mainPanel.setDividerLocation(.75);
		setContent();
		return mainPanel;
	}

	private static void setContent() {
		content = new GameView(gameName);
		columnar.setRightComponent(content);
	}

	public static void setGame(String name) {
		gameName = name;
		setContent();
		System.out.println(gameName);
	}

	public static void updateHighScore(double highScore) {
		score=highScore;
		new HighScore(gameName);
	}

	public static boolean addHighScore() {
		return hsu.addScore(playerName, gameName,score);
	}
	
	// makes the left hand side panel
	private JComponent makeLeftPanel() {
		JPanel left = new JPanel();
		int xSizeOfColumn = (int) (tk.getScreenSize().getWidth() / 5);
		int ySizeOfColumn = ((int) tk.getScreenSize().getHeight() / 2);

		left.setMinimumSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
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

		left.add(rateThis);
		left.add(label);
		left.add(separator);
		left.add(Components.getGameHighScoresPanel(gameName,5));
		left.add(separator);
		left.add(rateOthers);
		left.add(moreLabels);

		left.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		return left;
	}

	// makes the right hand side panel
	private JComponent makeRightPanel() {
		JPanel right = new JPanel();
		int xSizeOfColumn = (int) (tk.getScreenSize().getWidth() / 7);
		int ySizeOfColumn = ((int) tk.getScreenSize().getHeight() / 2);

		right.setMinimumSize(new Dimension(xSizeOfColumn, ySizeOfColumn));
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
		JLabel playerName = new JLabel("Guest Avatar");
		playerAvatar.add(playerName);
		playerAvatar.add(label);
		playerAvatar.add(Components.getPlayerHighScoresPanel("Guest",5));	
		
		JPanel lobby = new JPanel();
		lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
		lobby.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lobby.add(new JLabel("Friends"));
		lobby.add(Components.getHighScoresPanel("PlayerA",gameName,2));

		JLabel moreLabels = new JLabel(icon);
		lobby.add(moreLabels);

		JPanel ads = new JPanel();
		ads.setLayout(new BoxLayout(ads, BoxLayout.Y_AXIS));
		;
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
	
}
