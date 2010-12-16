package arcade.core;

import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import vooga.engine.core.Game;
import arcade.core.examples.HighScorePanel;
import arcade.lobby.model.ProfileSet;
import arcade.wall.controllers.WallWidget;

/**
 * The main game view where games can be launched. Descriptions and instructions
 * and the splash screen are all displayed in this view. This is a panel that is the
 * center and main view of the arcade. 
 * 
 * @author Aaron Choi, Derek Zhou, Yang Su
 *
 */

@SuppressWarnings("serial")
public class GameView extends JPanel {

	private static int gameID;
	private static String gameName;
	private static Map<String, String[]> gameProperties;
	private static ImageIcon splash;

	public GameView(int id) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameID = id;
		initialize();
	}

	public void initialize() {
		gameProperties = parseGame(gameID);
		add(setSplashScreen());
		add(setText());
		add(createButtons());
	}

	private JPanel createButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(startButton());
		panel.add(backButton());
		return panel;
	}

	private static Map<String, String[]> parseGame(int id) {
		Map<String, String[]> gameMap = new HashMap<String, String[]>();
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("Id", id+"");
		List<Map<String, String>> attributes = ArcadeEdited.myDbAdapter.getRows(
				"GameInfo", conditions, "Title", "Genre",
				"Description", "Tags", "ClassName", "ImagePaths",
				"Instructions");
		for (Map<String, String> m : attributes) {
			for (String key : m.keySet()) {
				gameMap.put(key.toLowerCase(), (key.equals("Tags") || key
						.equals("Instructions")) ? m.get(key).split(",")
						: new String[] { m.get(key) });
			}
		}
		gameName=gameMap.get("title")[0];
		return gameMap;
	}

	private static JLabel setSplashScreen() {
		try {
			splash = new ImageIcon(gameProperties.get("imagepaths")[0]);
		} catch (Exception e) {
			System.out.println(gameProperties.get("imagepaths")[0]);
		}
		JLabel splashImage = new JLabel(splash);
		splashImage.setAlignmentX(CENTER_ALIGNMENT);
		return splashImage;
	}

	private static JTextPane setText() {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = "<center><p><b>" + gameProperties.get("title")[0]
				+ "</b></p>" + "<p>Genre: " + gameProperties.get("genre")[0]
				+ "</p>" + "<p>Description: "
				+ gameProperties.get("description")[0] + "</p>"
				+ "<p><b>Instructions</b></p><p>";
		textPane.setEditable(false);

		for (String instruction : gameProperties.get("instructions")) {
			description += instruction + "<br>";
		}
		description += "</p></center>";
		textPane.setText(description);
		textPane.setAlignmentX(CENTER_ALIGNMENT);
		return textPane;
	}

	private static JButton startButton() {

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
					public Integer doInBackground() {
						Class<?> newGame;
						try {
							newGame = Class.forName("vooga.games."
									+ gameProperties.get("classname")[0]
									+ ".Blah");

							Constructor<?> gameConstructor = newGame
									.getConstructor();
							
							Game launchedGame = (Game) gameConstructor.newInstance();
														
							launchedGame.setMod(JOptionPane.showInputDialog("Enter mod name (leave blank otherwise):"));
							WallWidget gameWidget = new WallWidget(gameName);

							Game.launch(launchedGame, ProfileSet.getCurrentProfile().getUserName());
							
						} catch (Throwable e) {
							System.out.println("vooga.games."
									+ gameProperties.get("classname")[0]
									+ ".Blah");
							e.printStackTrace();
						}
						return 0;
					}
				};
				// Try something with this
				// worker.isDone()
				worker.execute();
			}
		});
		return start;
	}

	private static Component backButton() {
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arcade.switchToTab(0);
			}
		});
		return back;
	}

	/**
	 * Returns the name of the active game
	 * @return Name of current game as a String
	 */
	public static String getGame() {
		return gameName;
	}
	
	/**
	 * Returns the ID number of the active game
	 * @return ID of current game as an integer
	 */
	public static int getGameID() {
		return gameID;
	}
}
