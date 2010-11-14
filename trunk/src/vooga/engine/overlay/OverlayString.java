package vooga.engine.overlay;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;



/**
 * This OverlayString class displays some text on the screen.
 * @author Justin Goldsmith
 * 
 * <pre>
 * <p>OverlayString overlay = new OverlayString("Hello");</p>
 * <p>Font font = new Font("sampleFont", Font.PLAIN, 22);</p>
 * <p>overlay.setFont(font);</p>
 * <p>overlay.setColor(Color.BLUE);</p>
 * </pre>
 * 
 * <p>All overlays must be updated and rendered, This is the responsibility of the game creator</p>
 *
 */


public class OverlayString extends Overlay {
	
	
	public final static Color DEFAULT_COLOR = Color.black;
	public final static Font DEFAULT_FONT = new Font("mine", Font.PLAIN, 22);

	
	
	private String myString;
	private GameFont myFont;
	private Color myColor;
	private Font myRealFont;
	
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
		GameFontManager fontManager = new GameFontManager();
		myRealFont = font;
		myFont = fontManager.getFont(myRealFont, myColor);
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
	
	
	public OverlayString(Map<String, String> attributes, OverlayTrackerTemp tracker){
		this(attributes.get("label"));
		
		String fontName = attributes.get("fontName");
		if (fontName != null && fontName.startsWith("gameFont")) {
			GameFont gameFont = OverlayCreatorTemp.getGameFont(fontName.substring(8));
			if (gameFont != null) {
				setFont(gameFont);
			} 
		} else {
			String fontStyleStr = attributes.get("fontStyle");
			int fontStyle = OverlayCreatorTemp.stringToFontStyle(fontStyleStr);
			String fontSizeStr = attributes.get("fontSize");
			int fontSize;
			if (fontSizeStr == null) {
				fontSize = 22;
			} else {
				fontSize = Integer.valueOf(fontSizeStr);
			}
			String fontColorStr = attributes.get("color");
			Color color = OverlayCreatorTemp.stringToColor(fontColorStr);
			setFont(new Font(fontName, fontStyle, fontSize));
			setColor(color);
		}	
		setLocation(attributes);
	}
	
	
	
	
	/**
	 * This method will implemented if this Overlay was created with a Font or only a String and not a GameFont.
	 * It will also not work if the setFont(GameFont) method was ever used.
	 * @param font
	 */
	public void setFont(Font font){
		if(myRealFont != null){
			myRealFont = font;
			GameFontManager fontManager = new GameFontManager();
			myFont = fontManager.getFont(font, myColor);
		}
	}
	
	public void setString(String str){
		myString = str;
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
			GameFontManager fontManager = new GameFontManager();
			myFont = fontManager.getFont(myRealFont, color);
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
		myFont.drawString(g,str,(int)getX() , (int)getY());		
		}

	/**
	 * print the String
	 */
	@Override
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
		return getTextWidth(myString);
	}
	
	/**
	 * @return returns the height of the string.
	 */
	@Override
	public int getHeight(){
		return getTextHeight();
		
	}	
		
}
	
	
	


