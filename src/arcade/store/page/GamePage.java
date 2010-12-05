package arcade.store.page;

import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import arcade.store.account.UserShopAccount;

/**
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 11-05-10
 * @description This is the basic gamePage class
 *
 */

public class GamePage extends StorePage {

	private int numberOfBuyers;
	
	private String gameName;
	private String developerName;
	
	private ImageIcon coverImage;
	private String description;
	private double rating;

	private String gameID;
	
	private double price;
	private String genre;

	public GamePage(JFrame surface) {
		super(surface);
		numberOfBuyers = 0;
	}

	public String getGameTitle() {

		return gameName;
	}

	public int getNumberOfBuyers()
	{
		return numberOfBuyers;
	}
	
	public void updateNumberOfBuyers()
	{
		numberOfBuyers++;
	}
	
	public void processOrder(UserShopAccount user) {
		// TODO: fill in here!
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub

	}
	
	public void paintUserComment(String comment)
	{
		//TODO Fill his area up with user comment
	}

	@Override
	public void processOnEvent(InputEvent event) {
		// TODO Auto-generated method stub

	}
	
	public void setRating(double value)
	{
		rating = value;
	}
	
	public void setCoverImage(ImageIcon image)
	{
		coverImage = image;
	}
	
	public void setDeveloper(String developer)
	{
		developerName = developer;
	}
	
	public void setDescription(String descript)
	{
		description = descript;
	}
	
	public String getDescription()
	{
		return description;
	}

	public double getRating()
	{
		return rating;
	}
	
	public String getDeveloper()
	{
		return developerName;
	}
	
	public String getGameName() {
		return gameName;
	}

	public String getGenre() {
		return genre;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getGameID()
	{
		return gameID;
	}
	
	public ImageIcon getCoverImage()
	{
		return coverImage;
	}

}
