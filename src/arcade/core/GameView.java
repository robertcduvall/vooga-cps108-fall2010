package arcade.core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;

import vooga.engine.core.Game;
import arcade.core.examples.Profile;

@SuppressWarnings("serial")
public class GameView extends JPanel {

	private static String gameName = "Zombieland";
	private static Map<String, String[]> gameProperties;
	private static ImageIcon splash;
	private static JPanel main;

	public GameView(String game) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameName = game;
		
		initialize();
	}

	private static void initialize() {
		gameProperties = parseGame(gameName);
		main.add(setSplashScreen());
		main.add(setText());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(startButton());
		panel.add(backButton());
		main.add(panel);
	}

	public static Map<String, String[]> parseGame(String game) {
		Map<String, String[]> gameMap = new HashMap<String, String[]>();
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("title", game);
		List<Map<String, String>> attributes = Arcade.myDbAdapter.getRows(
				"GameInfo", conditions, "title", "genre", "rating",
				"description", "tags", "classname", "imagepaths",
				"instructions");
		for (Map<String, String> m : attributes) {
			for (String key : m.keySet()) {
				gameMap.put(key, (key.equals("tags") || key
						.equals("instructions")) ? m.get(key).split(",")
						: new String[] { m.get(key) });
			}
		}
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
							Game.launch((Game) gameConstructor.newInstance());
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
	
	public static void setGame(String name) {
		gameName = name;
		refreshContent();
		Profile.getGameHighScoresPanel(gameName, 5);
		columnar.setLeftComponent(makeLeftPanel());
		mainPanel.setRightComponent(makeRightPanel());
	}
	
	private static void refreshContent() {
		main.removeAll();
		initialize();
	}
	
	public static String getGame() {
		return gameName;
	}
	
	public JComponent getContent() {
		return main;
	}
}
