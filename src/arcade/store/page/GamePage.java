package arcade.store.page;

import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JFrame;

import arcade.store.account.UserShopAccount;
import arcade.store.organizer.IOrganizablePage;

public class GamePage extends StorePage {

	private String gameName;
	private String developerName;
	
	private BufferedImage coverImage;

	private String description;
	private double rating;

	private int gameId;
	private double price;
	private String genre;

	public GamePage(JFrame surface) {
		super(surface);

	}

	public String getGameTitle() {

		return gameName;
	}

	public void processOrder(UserShopAccount user) {
		// TODO: fill in here!
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processOnEvent(InputEvent event) {
		// TODO Auto-generated method stub

	}

	public String getGameName() {
		return gameName;
	}

	public String getGenre() {
		return genre;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

}
