package arcade.core;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.*;

import arcade.core.mvc.IController;

public class GameSelection extends JPanel implements Tab {
	private static final String DELIMITER = ",";
	//TODO ORDER?
	public JPanel games;
	public static JTextField searchArea;
	public static String currentGame = "";
	private List<Map<String, String>> gameData;
	private Map<Integer, JPanel> panels;
	public GameSelection() {
		setName("Games");
		setToolTipText("A list of all the game available");
		panels=new HashMap<Integer, JPanel>();
		gameData=getGames();
		
		initPanels();
		
		games = new JPanel(new GridLayout(0, 3));
		displayAllGames();
		setLayout(new BorderLayout());
		add(addSearchFunction(), BorderLayout.NORTH);
		add(games, BorderLayout.CENTER);
		
		initPanels();
	}
	
	private static List<Map<String, String>> getGames() {
		return Arcade.myDbAdapter.getColumns("GameInfo");
	}
	
	private void initPanels() { 
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
			panels.get(i).repaint();
		}
		refresh();
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		repaint();
	}
}
