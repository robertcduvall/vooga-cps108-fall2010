package arcade.core;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.*;

import vooga.engine.core.Game;

public class GameSelection extends Tab {
	private static String[] gameNames = { "asteroids", "cyberion", 

			"doodlejump", "galaxyinvaders", "grandius", "jumper", "mariogame",
			"towerdefense", "zombieland" };
	public static JPanel panel;
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
		JTextField searchArea = new JTextField("Enter Search Terms", 25);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener(searchArea.getText()));
		search.add(searchArea);
		search.add(searchButton);
		return search;
	}

	public class SearchButtonListener implements ActionListener
	{
		ResourceBundle resources;
  	   List<String> rightGames = new ArrayList<String>();
  	   String[] searchTerms;
		public SearchButtonListener(String searchTerms)
		{
			this.searchTerms = searchTerms.split(" ");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

     	   panel.removeAll();
     	   panel.setLayout(new GridLayout(0,3));
     	   panel.add(addSearchFunction());
				try {
					for (String game : gameNames)
					{
						resources = ResourceBundle.getBundle("vooga.games." + game + ".resources.game");
						String[] tags = resources.getString("tags").split(",");
						for (String tag : tags)
						{
							for (String term : searchTerms)
							{
								if (tag.equals(term.toLowerCase())){
									panel.add(createItem(game));
								}
							}
						}
					}
					
					
				}
				catch (Throwable e1) {
					e1.printStackTrace();
				}
			
		}
		
	}
	
	@Override
	public JComponent getContent() {
		panel = new JPanel(new GridLayout(0, 3));
		panel.add(addSearchFunction());
		for (String name : gameNames) {
			panel.add(createItem(name));
		}
		return panel;
	}

}
