package arcade.store.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import arcade.core.mvc.Controller;
import arcade.store.control.MainPageController;
import arcade.store.items.IItemInfo;
import arcade.util.guiComponents.ItemThumbnailPanel;

/**
 * 
 * @author 			Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 			12-15-10
 * @description 	The StoreItemThumbnailPanel is subclass of ItemThumbnailPanel
 * 					and is UI component to hold basic game information obtained from
 * 					an IItemInfo inside the the MainPageController. Once clicked,
 * 					the ItemThumbnailPanel then instantiates a Game
 *
 */

public class StoreItemThumbnailPanel extends ItemThumbnailPanel {
	
	/**
	 * This is the default serial number for the ItemThumbnail
	 */
	private static final long serialVersionUID = 1L;
	private static final String CREDDIT_STRING = " Creddits";
	
	private Controller controller;
	private IItemInfo information;
	
	/**
	 * Constructor takes in an IItemInfo and a controller.
	 * @param item
	 * @param control
	 */
	public StoreItemThumbnailPanel(IItemInfo item, Controller control)
	{	
		super(item.getTitle());
		String title = item.getTitle();
		initialize(title);
		information = item;
		controller = control;
		populate();
	}

		/**
	 * This method defines the specific action upon user click on the 
	 * StoreItemThumbnailPanel.
	 */
	@Override
	protected void processMouseEvent() {
		((MainPageController) controller).openGamePurchasePage(getName());
		
	}

	/**
	 * This method overwrites the populate method from the super class
	 * and writes the IItemInfo to the panel. 
	 */
	@Override
	protected void populate() {
		String title = information.getTitle();
		String price = information.getPrice();
		String genre = information.getGenre();
		ImageIcon image = information.getImages().get(IItemInfo.COVER_IMAGE);
		
		populate(title, price, genre, image);
	}
	
	
	/**
	 * This method defines the specific setup for the Store ThumbnailPanel.
	 * @param title
	 * @param price
	 * @param genre
	 * @param image
	 */
	private void populate(String title, String price, String genre, ImageIcon image){
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

}
