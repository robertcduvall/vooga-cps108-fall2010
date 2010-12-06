package arcade.security.util;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

/**
 * Simple encryption using symmetric keys
 * @author Jiaqi Yan
 *
 */
public class SimpleEncryption {
	 private String algorithm = "DESede";
	 private Key key;
	 private Cipher cipher;
	 
	 public SimpleEncryption() throws Exception{
		 key = KeyGenerator.getInstance(algorithm).generateKey();
		 cipher = Cipher.getInstance(algorithm);
	 }
	 
	 public byte[] encrypt(String originalText) throws InvalidKeyException,BadPaddingException,IllegalBlockSizeException{
		 cipher.init(Cipher.ENCRYPT_MODE,key);
		 byte[] inputBytes = originalText.getBytes();
		 return cipher.doFinal(inputBytes);
	 }
	 public String decrypt(byte[] encryptedText) throws InvalidKeyException,BadPaddingException,IllegalBlockSizeException{
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] recoveredBytes = cipher.doFinal(encryptedText);
		String recovered = new String(recoveredBytes);
		return recovered;
	 }
	 
	
	
	
}
