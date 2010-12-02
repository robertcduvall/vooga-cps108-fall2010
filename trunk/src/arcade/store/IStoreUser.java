package arcade.store;

public interface IStoreUser {

	public void updateCredits(double amount);
	
	public void getCredits();
	
	public void addGame(GamePage game);
	
	public boolean hasGame(GamePage game);

}
