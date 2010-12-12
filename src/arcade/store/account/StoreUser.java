package arcade.store.account;

import java.util.*;

import arcade.store.StoreSqlAdapter;
import arcade.store.items.IItemInfo;

public class StoreUser {
	
	private final static String DEFAULT_NAME = "default_user";
	private String name;
	private double creddits;
	private int timeCreddits;
	private List<String> ownedGames;
	
	private List<String> cart;
	private List<String> cartHistory;
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	
	public StoreUser(String name, double creddits, int timeCreddits, String cart, 
			String ownedGames) {
		this.name = name;
		this.creddits = creddits;
		this.timeCreddits = timeCreddits;
		this.cart = new ArrayList<String>();
		this.ownedGames = new ArrayList<String>();
		String[] cartList = cart.split(",");
		for(String s : cartList) this.cart.add(s.trim());
		String[] ownedList = cart.split(",");
		for(String s : ownedList) this.ownedGames.add(s.trim());
	}
	
	public StoreUser()
	{
		name = DEFAULT_NAME;
		creddits = 0;
		timeCreddits = 0;
		cart = new ArrayList<String>();
		ownedGames = new ArrayList<String>();
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getCreddits() {
		return Double.toString(creddits);
	}

	public String getTimeCreddits() {
		return Integer.toString(timeCreddits);
	}
	
	public List<String> getCart() {
		return cart;
	}
	
	
	public List<String> getOwnedGames() {
		return ownedGames;
	}
	
	public boolean processPurchase(IItemInfo item) {
		double price = Double.parseDouble(item.getPrice());
		if(price-creddits >= 0) {
			creddits -= price;
			ownedGames.add(item.getTitle());
			return true;
		}
		return false;
	}
	
	public void updateToCreddits(double newcreddits)
	{
		creddits = newcreddits;
	}
	
	public void addCreddits(double amount) {
		creddits +=amount;
		dbAdapter.updateCreddits(creddits, name);
	}
	
	public void addGame(String title)
	{
		ownedGames.add(title);
		dbAdapter.updateList(ownedGames, name);
	}
	
	public void emptyCart()
	{
		cart = new ArrayList<String>();
	}
	
	public void removeTitleFromCart(String title)
	{
		cart.remove(title);
	}
	
	public void addToCart(String name) {
		cart.add(name);
		dbAdapter.updateList(cart, name);
	}
	
}
