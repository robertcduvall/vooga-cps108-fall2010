package engine.overlay;

import java.awt.Color;
import java.awt.Font;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * This OverlayString class displays some text on the screen.
 * @author Justin Goldsmith
 *
 */


public class OverlayString extends Overlay {
	
	private final static Color DEFAULT_COLOR = Color.black;
	private final static Font DEFAULT_FONT = new Font("mine", Font.PLAIN, 22);
	
	private String myString;
	Font myFont;
	Color myColor;
	
	/**
	 * Creats a String to be displayed as an image
	 * @param str String to be printed
	 */
	public OverlayString(String str){
		this(str, DEFAULT_FONT, DEFAULT_COLOR);
	}
	/**
	 * Creats a String to be displayed as an image
	 * @param str String to be printed
	 * @param font
	 */
	public OverlayString(String str, Font font){
		this(str, font, DEFAULT_COLOR);
		
	}
	/**
	 * Creats a String to be displayed as an image
	 * @param str String to be printed 
	 * @param font
	 * @param color
	 */
	public OverlayString(String str, Font font, Color color){
		myString = str;
		myFont = font;
		myColor = color;
	}
	/**
	 * Creats a String to be displayed as an image
	 * @param str String to be printed
	 * @param color
	 */
	public OverlayString(String str, Color color){
		this(str, DEFAULT_FONT, color);	
	}
	
	public void setFont(Font font){
		myFont = font;
	}
	
	public void setColor(Color color){
		myColor = color;
	}
	
	
	/**
	 * Sets the image of this object to the passed String
	 * @param str
	 */
	public void print(String str) {		 //get a image from a string
		int width = OverlayHelp.getTextWidth(str, myFont);  // get needed width and height of the image to fit the string
		int height = OverlayHelp.getTextHeight(str, myFont);
		GreenfootImage gfi = new GreenfootImage(width + 5, height + 2);
		gfi.setFont(myFont);
	    gfi.setColor(myColor);
		gfi.drawString(str, 2, height - 1);
        setImage(gfi);
	}

	/**
	 * print the String
	 */
	public void act()
	{
		print(myString);
	}
	
	public String getString(){
		return myString;
	}
	
		
}
	
	
	


