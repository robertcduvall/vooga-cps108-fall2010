package vooga.engine.overlay;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;



/**
 * This OverlayString class displays some text on the screen.
 * @author Justin Goldsmith
 * 
 * <p>OverlayString overlay = new OverlayString("Hello");</p>
 * <p>Font font = new Font("sampleFont", Font.PLAIN, 22);</p>
 * <p>overlay.setFont(font);</p>
 * <p>overlay.setColor(Color.BLUE);</p>
 * 
 * <p>All overlays must be updated and rendered, This is the responsibility of the game creator</p>
 *
 */


public class OverlayString extends Overlay {
	
	private final static Color DEFAULT_COLOR = Color.black;
	private final static Font DEFAULT_FONT = new Font("mine", Font.PLAIN, 22);
	private final static BufferedImage BUFFERED_IMAGE = new BufferedImage(10, 10,BufferedImage.TYPE_USHORT_GRAY);
	private final static Graphics2D GRAPHIC = BUFFERED_IMAGE.createGraphics();
	
	
	private String myString;
	private GameFont myFont;
	private Color myColor;
	private Font myRealFont;
	private GameFontManager myFontManager;
	private int myWidth;
	private int myHeight;
	
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
		myColor = color;
		myFontManager = new GameFontManager();
		myRealFont = font;
		myFont = myFontManager.getFont(myRealFont, myColor);
	}
	/**
	 * 
	 * @param str String to display
	 * @param font 
	 */
	public OverlayString(String str, GameFont font){
		myString = str;
		myFont = font;
	}
	
	/**
	 * Creats a String to be displayed as an image
	 * @param str String to be printed
	 * @param color
	 */
	public OverlayString(String str, Color color){
		this(str, DEFAULT_FONT, color);	
	}
	
	/**
	 * This method will implemented if this Overlay was created with a Font or only a String and not a GameFont.
	 * It will also not work if the setFont(GameFont) method was ever used.
	 * @param font
	 */
	public void setFont(Font font){
		if(myRealFont != null){
			myFont = myFontManager.getFont(font, myColor);
		}
	}
	
	public void setFont(GameFont font){
		myFont = font;
		myRealFont = null;
	}
	
	/**
	 * This method will implemented if this Overlay was created with a Font or only a String and not a GameFont.
	 * setFont(GameFont)
	 * @param color
	 */
	public void setColor(Color color){
		if(myRealFont != null){
			myColor = color;
			myFont = myFontManager.getFont(myRealFont, myColor);
		}
		
	}
	
	/**
	 * This method is needed for other classes in the Overlay package.
	 * It should never be used by a game creator.
	 */
	protected void print(String str, Graphics2D g) {		
        createImage(str, g);
	}
	
	
	private void createImage(String str, Graphics2D g) {
	
		myWidth = getTextWidth(str) + 1;
		myHeight = getTextHeight();
		myFont.drawString(g,str,(int)getX() , (int)getY());
		
		
		}

	/**
	 * print the String
	 */
	public void render(Graphics2D g)
	{
		print(myString, g);
	}
	
	/**
	 * @return returns the string this Overlay is displaying.
	 */
	public String getString(){
		return myString;
	}
	
	private int getTextWidth(String str){
		return myFont.getWidth(str);
	}
	
	private int getTextHeight(){
		return myFont.getHeight();
	}

	/**
	 * @return returns the width of the string.
	 */
	@Override
	public int getWidth(){
		return myWidth;
	}
	
	/**
	 * @return returns the height of the string.
	 */
	@Override
	public int getHeight(){
		return myHeight;
		
	}
	
		
}
	
	
	


