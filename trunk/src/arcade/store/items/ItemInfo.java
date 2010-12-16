package arcade.store.items;

import java.util.List;
import javax.swing.ImageIcon;

/**
 * Concrete implementation of the IItemInfo interface.
 * This is a container class for all the information the store
 * needs about each item in its catalog.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 *
 */
public class ItemInfo implements IItemInfo {
	
	private String description;
	private int id;
	private String price;
	private String title;
	private String numberOfBuyer;
	private List<ImageIcon> images;
	private String genre;
	private List<String> tags;

	/**
	 * Constructs an ItemInfo container class. Takes in all necessary information.
	 * @param id game's unique database id.
	 * @param description description of the game
	 * @param price game price
	 * @param title game title
	 * @param numberOfBuyer number of purchases
	 * @param images List of ImageIcons to be used for 
	 * @param genre
	 */
	public ItemInfo(int id, String description, String price, String title, String numberOfBuyer,
			 List<ImageIcon> images, String genre, List<String> tags) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.title = title;
		this.numberOfBuyer = numberOfBuyer;
		this.images = images;
		this.genre = genre;
		this.tags = tags;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getPrice() {
		return price;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getNumberOfBuyer() {
		return numberOfBuyer;
	}

	public String getGenre() {
		return genre;
	}
	@Override
	public List<ImageIcon> getImages() {
		return images;
	}
	@Override
	public List<String> getTags() {
		return tags;
	}
	@Override
	public int getId() {
		return id;
	}

}
