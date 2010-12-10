package arcade.store.account;

import java.util.*;

import arcade.store.StoreSqlAdapter;
import arcade.store.items.IItemInfo;

public class StoreUser {
	
	private String name;
	private double creddits;
	private int timeCreddits;
	private List<String> cart;
	private List<String> ownedGames;
	
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
	
}
