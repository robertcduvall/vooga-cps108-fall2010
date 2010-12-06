package arcade.security.util;

/**
 * Subject to change provide for the network group when transmitting information
 * @author JiaqiYan
 */

public abstract class Encryption {
	
	public abstract byte[] encrypt(String originalText);
	
	public abstract String decrypt(byte[] encryptedText);
}
