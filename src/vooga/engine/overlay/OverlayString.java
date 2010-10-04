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
	 * Creats a String to be displayed as an image
	 * @param str String to be printed
	 * @param color
	 */
	public OverlayString(String str, Color color){
		this(str, DEFAULT_FONT, color);	
	}
	
	public void setFont(Font font){
		myFont = myFontManager.getFont(font);

	}
	
	public void setColor(Color color){
		myColor = color;
		myFont = myFontManager.getFont(myRealFont, myColor);
		
	}
	
	
	/**
	 * Sets the image of this object to the passed String
	 * @param str
	 */
	public void print(String str, Graphics2D g) {		 //get a image from a string
        createImage(str, g);
	}
	
	
	private void createImage(String str, Graphics2D g) {
	
		myWidth = getTextWidth(str) + 1;
		myHeight = getTextHeight();
		/*// Create a buffered image in
		// which to draw
		BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
		//  Create a graphics contents
		// on the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0));
		g2d.fillRect(0, 0, width, height);

		// Draw graphics
		g2d.setFont(myRealFont);
		g2d.setColor(myColor);
		
		g2d.drawString(str, 2, height - 1);
		
		
		
		
		
		// Graphics context
		// no longer needed so dispose
		// it
		g2d.dispose(); 
		return bufferedImage; 
		*/
		
		myFont.drawString(g,str,(int)getX() , (int)getY());
		
		
		}

	/**
	 * print the String
	 */
	public void render(Graphics2D g)
	{
		print(myString, g);
	}
	
	public String getString(){
		return myString;
	}
	
	private int getTextWidth(String str){
		return myFont.getWidth(str);
	}
	
	private int getTextHeight(){
		return myFont.getHeight();
	}
	
	protected int getMyWidth(){
		return myWidth;
	}
	
	protected int getMyHeight(){
		return myHeight;
		
	}
	
		
}
	
	
	


