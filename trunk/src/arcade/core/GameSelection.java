package arcade.core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameSelection extends Tab {
	private static String[] gameNames = { "asteroids", "cyberion",
			"doodlejump", "galaxyinvaders", "grandius", "jumper", "mariogame",
			"towerdefense", "zombieland" };

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

	@Override
	public JComponent getContent() {
		JPanel panel = new JPanel(new GridLayout(0, 3));
		for (String name : gameNames) {
			panel.add(createItem(name));
		}
		return panel;
	}

}
