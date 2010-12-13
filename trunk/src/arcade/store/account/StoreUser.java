package arcade.store.account;

import java.util.*;

import arcade.store.StoreSqlAdapter;
import arcade.store.items.IItemInfo;

public class StoreUser {
	
	private final static String DEFAULT_NAME = "default_user";
	private static final String CART_FIELD = "Cart";
	private static final String OWNED_GAMES_FIELD = "GamesOwned";
	private int id;
	private String name;
	private double creddits;
	private List<String> ownedGames;
	private List<String> cart;
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	
	public StoreUser(String name, double creddits, String cart, 
			String ownedGames) {
		this.name = name;
		this.creddits = creddits;
		this.cart = new ArrayList<String>();
		this.ownedGames = new ArrayList<String>();
		String[] cartList = cart.split(",");
		for(String s : cartList) {
			if(s.length()>1)
			this.cart.add(s.trim());
		}
		String[] ownedList = ownedGames.split(",");
		for(String s : ownedList) {
			if(s.length()>1)
			this.ownedGames.add(s.trim());
		}
	}
	
	public StoreUser()
	{
		this(DEFAULT_NAME, 0, "", "");
	}
	
	public String getName() {
		return name;
	}
	
	public String getCreddits() {
		return Double.toString(creddits);
	}

	
	public List<String> getCart() {
		return cart;
	}
	
	
	public List<String> getOwnedGames() {
		return ownedGames;
	}
	
	
	public void updateToCreddits(double newcreddits)
	{
		creddits = newcreddits;
		dbAdapter.updateCreddits(creddits, Integer.toString(id));
	}
	
	public void addCreddits(double amount) {
		creddits +=amount;
		dbAdapter.updateCreddits(creddits, Integer.toString(id));
	}
	
	public void addGames(List<String> titles)
	{
		for(String s : titles) {
			ownedGames.add(s);
		}
		dbAdapter.updateList(ownedGames, Integer.toString(id), OWNED_GAMES_FIELD);
	}
	
	public void emptyCart()
	{
		cart = new ArrayList<String>();
		dbAdapter.updateList(cart, Integer.toString(id), CART_FIELD);
	}
	
	public void removeTitleFromCart(String title)
	{
		cart.remove(title);
	}
	
	public void addToCart(String name) {
		cart.add(name);
	}
	
	public void saveCart() {
		dbAdapter.updateList(cart, Integer.toString(id), CART_FIELD);
	}
	
}
