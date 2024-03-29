package arcade.store.account;

import java.util.*;

/**
 * StoreUser contains the information for a user using the store. This
 * information is the users id, name, total creddits, a list of the games they
 * own and a list of their shopping cart.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class StoreUser {

	public static final int MINIMAL_BALANCE = 0;
	private static final String DEFAULT_ID = "0";
	private static final String SPLIT_CART_STRING = ",";
	private static final String STORE_USER_TYPE = "1";
	private String id;
	private double creddits;
	private List<String> cart;


	/**
	 * Constructor for StoreUser. This sets the users names to the parameter
	 * name, their total creddits to the parameter creddits, their cart to the
	 * parameter cart and their ownedGames to the parameter ownedGames.
	 * 
	 * @param name
	 * @param creddits
	 * @param cart
	 */
	public StoreUser(String id, double creddits, String cart) {
		this.id = id;
		this.creddits = creddits;
		this.cart = new ArrayList<String>();
		String[] cartList = cart.split(SPLIT_CART_STRING);
		for (String s : cartList) {
			if (s.length() > 1)
				this.cart.add(s.trim());
		}
	}

	/**
	 * Default constructor for a StoreUser. This sets the user's Id to the
	 * DEFAULT_ID constant, their total creddits to 0, their cart to an empty
	 * String.
	 */
	public StoreUser() {
		this(DEFAULT_ID, 0, "");
	}


	/**
	 * 
	 * @return number of creddits that a user has
	 */
	public double getCreddits() {
		return creddits;
	}

	/**
	 * 
	 * @return a String List of all the game titles currently in a 
	 * user's shopping cart.
	 */
	public List<String> getCart() {
		return cart;
	}
	
	public void setUSerType(String type)
	{
		
	}
	
	/**
	 * Checks for whether the user's cart is empty. 
	 * @return
	 */
	public boolean cartEmpty()
	{
		return cart.isEmpty();
	}

	/**
	 * Takes the parameter newCreddits and sets the StoreUsers creddits to this
	 * value. Then it updates the database with the StoreUsers current creddits.
	 * 
	 * @param newCreddits
	 *            the new value for the StoreUser's creddits
	 */
	public void setCreddits(double newCreddits) {
		creddits = newCreddits;
	}


	/**
	 * Makes the StoreUser's cart a new ArrayList of Strings.
	 */
	public void emptyCart() {
		cart = new ArrayList<String>();
	}

	/**
	 * Takes in the parameter title and removes that item from the StoreUser's
	 * cart.
	 * 
	 * @param title
	 *            the item to remove from the StoreUser's cart
	 */
	public void removeTitleFromCart(String title) {
		cart.remove(title);
	}

	/**
	 * Takes in the parameter name and adds this item to the StoreUser's cart.
	 * 
	 * @param name
	 *            the item to add to the StoreUser's cart
	 */
	public void addToCart(String name) {
		cart.add(name);
	}
	
	/**
	 * 
	 * Returns current user's unique ID number. This is the same as the current user
	 * Profile's ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the current user's account type. 
	 * 
	 * @return user's account type (is an int, but returned as String for database
	 * convenience). 
	 */
	public String getAccountType() {
		return STORE_USER_TYPE;
	}

}
