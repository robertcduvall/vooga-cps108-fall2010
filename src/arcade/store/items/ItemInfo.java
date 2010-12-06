package arcade.store.items;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ItemInfo implements IItemInfo {
	
	private String description;
	private String price;
	private String title;
	private String numberOfBuyer;
	private String rating;
	private List<ImageIcon> images;
	private String genre;
	private List<String> tags;

	
	public ItemInfo(String description, String price, String title, String numberOfBuyer,
			String rating, List<ImageIcon> images, String genre) {
		this.description = description;
		this.price = price;
		this.title = title;
		this.numberOfBuyer = numberOfBuyer;
		this.rating = rating;
		this.images = images;
		this.genre = genre;
		this.tags = new ArrayList<String>();
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

	@Override
	public String getRating() {
		return rating;
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

}
