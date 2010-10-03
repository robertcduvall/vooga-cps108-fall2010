package vooga.engine.overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;



/**
 * This OverlayString class displays some text on the screen.
 * @author Justin Goldsmith
 * 
 * OverlayString overlay = new OverlayString("Hello");
 * Font font = new Font("sampleFont", Font.PLAIN, 22);
 * overlay.setFont(font);
 * overlay.setColor(Color.BLUE);
 *
 */


public class OverlayString extends Overlay {
	
	private final static Color DEFAULT_COLOR = Color.black;
	private final static Font DEFAULT_FONT = new Font("mine", Font.PLAIN, 22);
	private final static BufferedImage BUFFERED_IMAGE = new BufferedImage(10, 10,BufferedImage.TYPE_USHORT_GRAY);
	private final static Graphics2D GRAPHIC = BUFFERED_IMAGE.createGraphics();
	
	
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
        setImage(createImage(str));
	}
	
	
	private BufferedImage createImage(String str) {
	
		int width = getTextWidth(str) + 4;
		int height = getTextHeight(str) + 2;
		// Create a buffered image in
		// which to draw
		BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_USHORT_GRAY);
		//  Create a graphics contents
		// on the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();
		// Draw graphics
		g2d.setColor(myColor);
		g2d.setFont(myFont);
		
		g2d.drawString(str, 2, height - 1);
		
		// Graphics context
		// no longer needed so dispose
		// it
		g2d.dispose(); 
		return bufferedImage; 
		}

	/**
	 * print the String
	 */
	public void update()
	{
		print(myString);
	}
	
	public String getString(){
		return myString;
	}
	
	private int getTextWidth(String str){
		GRAPHIC.setFont(myFont);
		FontMetrics fm = GRAPHIC.getFontMetrics();
		return fm.stringWidth(str);
		
	}
	
	private int getTextHeight(String str){
		GRAPHIC.setFont(myFont);
		FontMetrics fm = GRAPHIC.getFontMetrics();
		return fm.getHeight();
		
	}
	
		
}
	
	
	


