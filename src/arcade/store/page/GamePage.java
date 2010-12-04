package arcade.store.page;

import java.awt.image.BufferedImage;
import java.util.*;

import arcade.store.account.UserShopAccount;

public class GamePage {
	
	private String gameName;
	
	private BufferedImage coverImage;
	
	
	private String description;
	private double rating;
	
	private int gameId;
	private double price;
	private String genre;
	
	private List<String> tags;
	
	public GamePage()
	{
		
		
	}
	
	
	public String getGameTitle() {
		
		return gameName;
	}
	
	public void processOrder(UserShopAccount user)
	{
		//TODO: fill in here!
	}
	
	private boolean hasPrivilege(UserShopAccount user)
	{
		//TODO : fill in here!
		return false;
	}
	
	
	

}
