package arcade.store.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.store.control.Control;

public class ItemThumbnailPanel extends JPanel {
	
	private static final String CREDDIT_STRING = " Creddits";
	private Control controller;
	
	public ItemThumbnailPanel(String title, String price, String genre, ImageIcon image, 
			Control control) {
		this(title, price, genre, image);
		controller = control;
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				openItemView();
			}
		});
	}
	
	
	public ItemThumbnailPanel(String title, String price, String genre, ImageIcon image) {
		addLabels(title, price, genre, image);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setName(title);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	
	private void addLabels(String title, String price, String genre, ImageIcon image) {
		JLabel gameTitle = new JLabel(title);
		gameTitle.setHorizontalTextPosition(JLabel.CENTER);

		JLabel icon = new JLabel(image);
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setSize(150, 150);
		
		JLabel genreLabel = new JLabel(genre);
		genreLabel.setHorizontalTextPosition(JLabel.CENTER);
		
		JLabel priceLabel = new JLabel(price + CREDDIT_STRING);
		priceLabel.setHorizontalTextPosition(JLabel.CENTER);
		
		this.add(gameTitle);
		this.add(icon);
		this.add(genreLabel);
		this.add(priceLabel);
	}
	
	public void openItemView() {
		controller.openGamePage(getName());
	}

}
