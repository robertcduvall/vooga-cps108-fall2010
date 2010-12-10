package arcade.core;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import vooga.engine.core.Game;

@SuppressWarnings("serial")
public class GameView extends JPanel {

	private GameParser gameParser;
	private String gameName;
	private Map<String, String[]> gameProperties;
	private ImageIcon splash;

	public GameView(String game) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameName = game;
		initialize();
	}

	private void initialize() {
		gameParser = new GameParser();
		gameProperties = new HashMap<String, String[]>();
		gameParser.parseGame(gameName, gameProperties);
		add(setSplashScreen());
		add(setText());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(startButton());
		panel.add(backButton());
		add(panel);
	}

	private JLabel setSplashScreen() {
		splash = new ImageIcon(gameProperties.get("splash")[0]);
		JLabel splashImage = new JLabel(splash);
		splashImage.setAlignmentX(CENTER_ALIGNMENT);
		return splashImage;
	}

	private JTextPane setText() {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		String description = "<center><p><b>" + gameProperties.get("name")[0]
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

	private JButton startButton() {

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
					public Integer doInBackground() {
						Class<?> newGame;
						try {
							newGame = Class.forName("vooga.games." + gameName
									+ ".Blah");

							Constructor<?> gameConstructor = newGame
									.getConstructor();
							Game.launch((Game) gameConstructor.newInstance());
						} catch (Throwable e) {
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

	private Component backButton() {
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arcade.switchToTab(0);
			}
		});
		return back;
	}
}
