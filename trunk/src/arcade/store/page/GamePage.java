package arcade.store.page;

import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JFrame;

import arcade.store.account.UserShopAccount;
import arcade.store.organizer.IOrganizablePage;

public class GamePage extends StorePage implements IOrganizablePage{
	
	private String gameName;
	
	private BufferedImage coverImage;
	
	private String description;
	private double rating;
	
	private int gameId;
	private double price;
	private String genre;
	
	private List<String> tags;
	
	public GamePage(JFrame surface)
	{
		super(surface);
		
	}
	
	
	public String getGameTitle() {
		
		return gameName;
	}
	
	public void processOrder(UserShopAccount user)
	{
		//TODO: fill in here!
	}
	


	@Override
	public void paint() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processOnEvent(InputEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getGameName() {
		return gameName;
	}


	@Override
	public List<String> getTags() {
		return tags;
	}


	@Override
	public String getGenre() {
		return genre;
	}
	
	
	

}
