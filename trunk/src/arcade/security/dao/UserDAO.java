package arcade.security.dao;

import java.util.Collection;
import arcade.security.user.AbstractUser;
/**
 * 
 * @author Meng Li
 *
 */
public interface UserDAO {

	public void insert(AbstractUser user);
	
	public void update(AbstractUser user);
	
	public void delete(String primaryKey);
	
	public Collection<AbstractUser> findAll();
	
	public AbstractUser findByKey(String primaryKey);

}
