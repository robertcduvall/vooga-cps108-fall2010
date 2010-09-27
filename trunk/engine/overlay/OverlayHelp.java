package engine.overlay;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * The OverlayHelp class has helper methods that are needed in multiple places.
 * @author Justin Goldsmith
 *
 */


public class OverlayHelp {  //This is Just to get the width of a string.
	private static BufferedImage bI = new BufferedImage(10, 10,BufferedImage.TYPE_USHORT_GRAY);
	private static Graphics2D g = bI.createGraphics();
	
	
	public static int getTextWidth(String str, Font font){
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		return fm.stringWidth(str);
		
	}
	
	public static int getTextHeight(String str, Font font){
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		return fm.getHeight();
		
	}
	
	
	

}
