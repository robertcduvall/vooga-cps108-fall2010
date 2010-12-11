package arcade.store.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.core.mvc.IController;
import arcade.store.control.MainController;
import arcade.store.control.MainPageController;

public class ItemThumbnailPanel extends JPanel {
	
	private static final String CREDDIT_STRING = " Creddits";
	private IController controller;
	
	public ItemThumbnailPanel(String title, String price, String genre, ImageIcon image, 
			IController control) {
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
		gameTitle.setAlignmentX(CENTER_ALIGNMENT);

		JLabel icon = new JLabel(image);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		icon.setSize(150, 150);
		
		JLabel genreLabel = new JLabel(genre);
		genreLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel priceLabel = new JLabel(price + CREDDIT_STRING);
		priceLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(gameTitle);
		this.add(icon);
		this.add(genreLabel);
		this.add(priceLabel);
	}
	
	public void openItemView() {
		((MainPageController) controller).openGamePurchasePage(getName());
	}

}
