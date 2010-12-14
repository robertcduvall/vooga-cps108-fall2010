package arcade.core;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.*;

public class GameSelection extends Tab {
	private static final String DELIMITER = ",";
	//TODO ORDER?
	private static int[] gameIDs = {12,13,14,15,16,17,18,19,20};
	public JPanel games;
	public static JTextField searchArea;
	public static String currentGame = "";
	private List<Map<String, String>> gameData;
	private Map<Integer, JPanel> panels;
	public GameSelection() {
		setName("Games");
		setToolTipText("A list of all the game available");
		panels=new HashMap<Integer, JPanel>();
		gameData=getGames(gameIDs);
		addPanels();
	}
	
	private static List<Map<String, String>> getGames(int[] games) {
		String query = "SELECT * FROM " + "GameInfo" + " WHERE ";
		for (int id : games) {
			query += "Id=" + id + " OR ";
		}
		return Arcade.myDbAdapter.getRows(query.substring(0, query.length() - 4));
	}
	
	private void addPanels() {
		for (Map<String, String> m : gameData) {
			panels.put(Integer.parseInt(m.get("Id")), createItem(m));
		}
	}
	public JPanel createItem(Map<String, String> m) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel title = new JLabel(m.get("Title"));
		title.setAlignmentX(CENTER_ALIGNMENT);

		ImageIcon image = new ImageIcon(m.get("ImagePaths"));
		Image scaled = image.getImage().getScaledInstance(100, 100,
				java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(scaled);
		JLabel icon = new JLabel(image);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton button = new JButton("Play");
		button.addActionListener(new buttonActionListener(m.get("Title"),Integer.parseInt(m.get("Id"))));
		button.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(title);
		panel.add(icon);
		panel.add(button);
		return panel;
	}
	
	private class buttonActionListener implements ActionListener {
		private int gameID;
		public buttonActionListener(String name, int id) {
			gameID=id;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Arcade.play(gameID);
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
				for (Map<String, String> m : gameData) {
					for (String tag : m.get("Tags").split(DELIMITER)) {
						if (tag.toLowerCase().contains(term.toLowerCase())) {
							games.add(panels.get(Integer.parseInt(m.get("Id"))));
							break;
						}
					}
				}
				if (games.getComponents().length == 0) {
					games.add(new JLabel(
							"No Games Found With Those Search Terms"));
				}
				games.validate();
				games.repaint();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			
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
		for (Integer i : panels.keySet()) {
			games.add(panels.get(i));
		}
		games.repaint();
	}
}
