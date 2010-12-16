package arcade.security.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 * Password Hasher
 * 
 * Utility class that hashes a password.
 * 
 * Taken from http://www.devarticles.com/c/a/Java/Password-Encryption-Rationale-and-Java-Example/
 * 
 * @author Andrew Brown
 * 
 */

public final class PasswordHasher
{
  
  public String encrypt(String plaintext)
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA"); //step 2
    }
    catch(NoSuchAlgorithmException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      md.update(plaintext.getBytes("UTF-8")); //step 3
    }
    catch(UnsupportedEncodingException e)
    {
      System.out.println(e.getMessage());
    }
    byte raw[] = md.digest(); //step 4
    String hash = (new BASE64Encoder()).encode(raw); //step 5
    return hash; //step 6
  }

}
