package arcade.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import arcade.security.user.AbstractUser;
import arcade.security.user.Guest;
/**
 * 
 * @author Meng Li
 *
 */
public class GuestDAO implements UserDAO {

	private String primaryKey;
	private Connection c = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;



	public GuestDAO(String primaryKey){
		this.primaryKey = primaryKey;
	}

	@Override
	public boolean deleteUser(String primaryKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Guest getUser(String primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser(AbstractUser user) {
		// TODO Auto-generated method stub
		return false;
	}


}
