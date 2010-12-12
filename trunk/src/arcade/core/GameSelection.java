package arcade.core;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.*;

public class GameSelection extends Tab {

	private static String[] gameNames = {"Cyberion", "Doodle Jump", "Grandius", "Jumper", "Super Mario Bros.",
		"Tower Defense", "Tron", "Zombieland" };
	public JPanel games;
	public static JTextField searchArea;
	public static String currentGame = "";

	public GameSelection() {
		setName("Games");
		setToolTipText("A list of all the game available");
	}

	public JPanel createItem(String name) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel title = new JLabel(name);

		ImageIcon image = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = image.getImage().getScaledInstance(100, 100,
				java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(scaled);
		JLabel icon = new JLabel(image);

		JButton button = new JButton("Play");
		button.addActionListener(new buttonActionListener(name));

		panel.add(title);
		panel.add(icon);
		panel.add(button);
		return panel;
	}

	public class buttonActionListener implements ActionListener {
		private String gameName;

		public buttonActionListener(String name) {
			gameName = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Arcade.play(gameName);
		}
	}

	public JComponent addSearchFunction() {
		JPanel search = new JPanel(new FlowLayout());
		searchArea = new JTextField("Enter Search Terms", 25);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
		JButton revert = new JButton("See all games");
		revert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayAllGames();
			}
		});
		search.add(searchArea);
		search.add(searchButton);
		search.add(revert);
		return search;
	}

	public class SearchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String term = searchArea.getText();
			games.removeAll();
			try {
				for (String game : getGames()) {
					for (String tag : getTags(game)) {
						if (tag.contains(term.toLowerCase())) {
							games.add(createItem(game));
							break;
						}
					}
				}
				if (games.getComponents().length == 0)
					games.add(new JLabel(
							"No Games Found With Those Search Terms"));
				games.validate();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			games.repaint();
		}

	}

	@Override
	public JComponent getContent() {
		JPanel panel = new JPanel();
		games = new JPanel(new GridLayout(0, 3));
		displayAllGames();
		panel.setLayout(new BorderLayout());
		panel.add(addSearchFunction(), BorderLayout.NORTH);
		panel.add(games, BorderLayout.CENTER);
		return panel;
	}

	public void displayAllGames() {
		games.removeAll();
		for (String name : getGames()) {
			games.add(createItem(name));
		}
		games.validate();
	}

	private String[] getGames() {
		return gameNames;
	}

	private String[] getTags(String game) {
		ResourceBundle resources = ResourceBundle.getBundle("vooga.games."
				+ game + ".resources.game");
		String[] tags = resources.getString("tags").split(",");
		return tags;
	}

}
