package arcade.security.dao;

import arcade.security.user.AbstractUser;
/**
 * 
 * @author Meng Li
 *
 */
public interface UserDAO {

	public void insert(User user);
	
	public void update(User user);
	
	public void delete(String primaryKey);
	
	public Collection<User> findAll();
	
	public User findByKey(String primaryKey);

}
