package vooga.engine.overlay;



import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vooga.engine.resource.Resources;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

/**
 * This class is used for creating overlays from an xml file instead of in the code itself.
 * @author Justin Goldsmith
 * 
 * <p>example</p>
 * <p>OverlayCreator.setGame(this);  //This is assuming you are in the game class</p>
 * <p>//Also this does not need to be done if you are using normal fonts.  If you use any of the three
 * //provided GameFont's then you must set the game.  Eventually the GameFont's should be in Resources 
 * //so you should not have to set the game</p>
 * <p>OverlayTracker track = OverlayCreator.createOverlays(String xmlFileLocation);</p>
 * <p>// the String xmlFileLocation must be path from the root of the project</p>
 * <p> Stat<Integer> myLives = track.getStats().get(2);</p>
 * <p> Stat<Integer> myScore = track.getStats().get(3);</p> *
 *		<p>PlayField myPlayfield = new PlayField();</p>
 *		<p>myPlayfield.addGroup(track.getOverlayGroups().get(0));</p>
 *		<p>myPlayfield.addGroup(track.getOverlayGroups().get(1));</p>
 *		<p>myPlayfield.addGroup(track.getOverlayGroups().get(2));</p>
 *<p>// Playfield can also be replaced with States from the GameState API</p>
 *
 *<A HREF="OverlayExample.xml">Click here to view xml file example</A> *	
 *<p>Here are all the attributes for all of the different overlays/p>
 *<ul>
 *<li>Stat
 *<ul>
 *<li>type - must be "Integer" or "String".  This will ideally change soon so it can be of any type.</li>
 *<li>value - initial value</li>
 *</ul>
 *</li>
 *<li> All Overlays
 *<ul>
 *<li>xLoc - x location of the overlay</li>
 *<li>yLoc - y location of the overlay</li>
 *</ul>
 *<li>OverlayString
 *<ul>
 *<li>label - String to be displayed</li>
 *<li>fontName - any String representing a font, for example "Times New Roman", or "gameFontGreen"</li>
 *<li>fontSize - point size of the font, must be an integer</li>
 *<li>fontStyle - style of the font, for example "ITALIC"</li>
 *<li>color - color of the font, for example "green".  It can also be a octal or hexidecimal representation of the color</li>
 *<li> And all attributes in all Overlays</li>
 *</ul>
 *</li>
 *<li>OverlayStat
 *<ul>
 *<li>stat - The stat to be displayed.  It must be an integer representing the order in which the stat was made.
 *if it was the first one made it would be "0".</li>
 *<li>All attributes in OverlayString</li>
 *</ul>
 *</li>
 *<li>OverlayStatImage
 *<ul>
 *<li>image - String the image is saved as in the Resources API</li>
 *<li>width - scale the size of the image to this width</li>
 *<li>height - scale the size of the image to this height.  In order to scale thw width and height must be set</li>
 *<li>  All of the attributes in all Overlays</li>
 *</ul>
 *</li>
 *<li>OverlayIcon
 *<ul>
 *<li>stat - The stat to be used to display icons.  Must be a Stat of type Integer.  It must be an integer representing the order in which the stat was made.
 *if it was the first one made it would be "0".</li>
 *<li>icon - The icon to be displayed.  It must be an integer representing the order in which the OverlayStatImage was made.
 *if it was the first one made it would be "0".</li>
 *<li>All attributes in OverlayString</li>
 *</ul>
 *</li>
 *<li>OverlayBar
 *<ul>
 *<li>stat - The stat to be displayed on bar.  Must be a Stat of type Integer.  It must be an integer representing the order in which the stat was made.
 *if it was the first one made it would be "0".</li>
 *<li>max - the max value of the bar.  For example if the max is 100 and the stat is at 50 the bar will be half full</li>
 *<li>color - The color of the value of the bar.  By default red.  Same format as the color in OverlayString</li>
 *<li>backColor - The color of the background of the bar.  By default black.  Same format as the color in OverlayString</li>
 *<li>length - The length of the bar</li>
 *<li>height - The height of the bar</li>
 *</ul>
 *</li>
 *</ul>
 * 
 */
public class OverlayCreator {
	
	private static OverlayTracker myOverlayTracker;	
	private static final String[] avalibleGameFonts = new String[]{"red", "orange", "green"};
	private static SpriteGroup tempGroup;
	private static Game myGame;
	
	
	/**
	 * 
	 * @param xmlFileLocation - the XML File Location. String xmlFileLocation must be path from the root of the project
	 * @return OverlayTracker containing all stats and overlays.
	 */
	public static OverlayTracker createOverlays(String xmlFileLocation){
		myOverlayTracker = new OverlayTracker();
		try {
		File file = new File(xmlFileLocation);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		NodeList statList = doc.getElementsByTagName("Stat");	
		NodeList overlayList = doc.getElementsByTagName("OverlayGroup");
		if(!processStats(statList)){
			return null;
		}
		if(!processOverlays(overlayList)){
			return null;
		}
		
		
		
		
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return myOverlayTracker;
		
	}

	private static boolean processOverlays(NodeList overlayList) {
		for(int s = 0; s<overlayList.getLength(); s++){
			tempGroup = new SpriteGroup("group" + s);
			Node group = overlayList.item(s);
			if (isElement(group)) {
				Element eleGroup = (Element)group;
				makeOverlayBars(eleGroup);
				makeOverlayStats(eleGroup);
				makeOverlayStrings(eleGroup);
				makeOverlayStatImages(eleGroup);
				makeOverlayIcons(eleGroup);
			}else{
				return false;
			}
			myOverlayTracker.getOverlayGroups().add(tempGroup);
		}
		
		
		return true;
	}

	private static void makeOverlayIcons(Element group) {
		NodeList overlayIcon = group.getElementsByTagName("OverlayIcon");
		for(int a = 0; a<overlayIcon.getLength(); a++){
			Node icon = overlayIcon.item(a);
			if(isElement(icon)){
				Element eleIcon = (Element)icon;
				String iconStr = eleIcon.getAttribute("icon");
				int iconIndex = Integer.valueOf(iconStr);
				OverlayIcon oi = new OverlayIcon(generalStat(eleIcon), myOverlayTracker.getImageOverlays().get(iconIndex),eleIcon.getAttribute("label") );
				generalString(oi.getOverlayString(), eleIcon);
				getLocation(oi, eleIcon);
				tempGroup.add(oi);
				myOverlayTracker.getIconOverlays().add(oi);
			}
		}
		
	}

	private static void makeOverlayStatImages(Element group) {
		NodeList overlayStatImage = group.getElementsByTagName("OverlayStatImage");
		for(int a = 0; a<overlayStatImage.getLength(); a++){
			Node image = overlayStatImage.item(a);
			if(isElement(image)){
				Element eleImg = (Element)image;
				String resourceName = eleImg.getAttribute("image");
				String width = eleImg.getAttribute("width");
				String height = eleImg.getAttribute("height");
				OverlayStatImage osi;
				if(!width.equals("") && !height.equals("")){
					osi = new OverlayStatImage(Resources.getImage(resourceName), Integer.valueOf(width), Integer.valueOf(height));
				}else{
					osi = new OverlayStatImage(Resources.getImage(resourceName));
				}
				getLocation(osi, eleImg);
				tempGroup.add(osi);
				myOverlayTracker.getImageOverlays().add(osi);
			}
		}
		
	}

	private static void makeOverlayStrings(Element group) {
		NodeList overlayString = group.getElementsByTagName("OverlayString");
		for(int a = 0; a<overlayString.getLength(); a++){
			Node string = overlayString.item(a);
			if(isElement(string)){
				Element eleStr = (Element)string;
				OverlayString os = new OverlayString(eleStr.getAttribute("label"));
				generalString(os, eleStr);
				getLocation(os, eleStr);
				tempGroup.add(os);
				myOverlayTracker.getStringOverlays().add(os);
			}
		}
	}

	private static boolean processStats(NodeList statList) {
		for(int s = 0; s<statList.getLength(); s++){
			Node stat = statList.item(s);
			if (isElement(stat)) {
				Element elmnt = (Element)stat;
				String type = elmnt.getAttribute("type");
				String value = elmnt.getAttribute("value");
				if(type.toLowerCase().equals("integer")){
					myOverlayTracker.getStats().add(new Stat<Integer>(Integer.valueOf(value)));
				}else if(type.toLowerCase().equals("string")){
					myOverlayTracker.getStats().add(new Stat<String>(value));
				}else{
					return false;
				}
			}else{
			return false;
			}
			
			
		}
		
		
		return true;
	}
	
	private static boolean isElement(Node node){
		if(node.getNodeType() == Node.ELEMENT_NODE){
			return true;
		}
		return false;
	}
	
	private static void getLocation(Overlay overlay,Element ele){
		String xLocStr = ele.getAttribute("xLoc");
		int xLoc;
		if(xLocStr.equals("")){
			xLoc = 0;
		}else{
			xLoc = Integer.valueOf(xLocStr);
		}
		String yLocStr = ele.getAttribute("yLoc");
		int yLoc;
		if(yLocStr.equals("")){
			yLoc = 0;
		}else{
			yLoc = Integer.valueOf(yLocStr);
		}
		overlay.setLocation(xLoc, yLoc);
	}
	
	private static Stat generalStat(Element ele){
		String statLoc = ele.getAttribute("stat");
		int stat = Integer.valueOf(statLoc);
		return myOverlayTracker.getStats().get(stat);
	}
	
	private static void generalString(OverlayString overlay, Element ele) {
		String fontName = ele.getAttribute("fontName");
		if (fontName.startsWith("gameFont")) {
			GameFont gameFont = getGameFont(fontName.substring(8));
			if (gameFont != null) {
				overlay.setFont(gameFont);
			} 
			return;
		} else {
			String fontStyleStr = ele.getAttribute("fontStyle");
			int fontStyle = stringToFontStyle(fontStyleStr);
			String fontSizeStr = ele.getAttribute("fontSize");
			int fontSize;
			if (fontSizeStr.equals("")) {
				fontSize = 22;
			} else {
				fontSize = Integer.valueOf(fontSizeStr);
			}
			String fontColorStr = ele.getAttribute("color");
			Color color = stringToColor(fontColorStr);
			overlay.setFont(new Font(fontName, fontStyle, fontSize));
			overlay.setColor(color);
		}

	}

	private static void makeOverlayBars(Element group){
		NodeList overlayBars = group.getElementsByTagName("OverlayBar");
		for(int a = 0; a<overlayBars.getLength(); a++){
			Node bar = overlayBars.item(a);
			if(isElement(bar)){
				Element eleBar = (Element)bar;
				String strMax = eleBar.getAttribute("max");
				int max = Integer.valueOf(strMax);
				OverlayBar ob = new OverlayBar(generalStat(eleBar), max);
				String strLength = eleBar.getAttribute("length");
				if(!strLength.equals("")){
					ob.setMaxLength(Integer.valueOf(strLength));
				}
				String strHeight = eleBar.getAttribute("height");
				if(!strHeight.equals("")){
					ob.setMaxLength(Integer.valueOf(strHeight));
				}
				getLocation(ob, eleBar);
				String color = eleBar.getAttribute("color");
				if(!color.equals("")){
					ob.setColor(stringToColor(color));
				}
				String backColor = eleBar.getAttribute("backColor");
				if(!backColor.equals("")){
					ob.setBackgroundColor(stringToColor(backColor));
				}
				tempGroup.add(ob);
				myOverlayTracker.getBarOverlays().add(ob);
			}
		}
	}
	
	private static void makeOverlayStats(Element group){
		NodeList overlayStats = group.getElementsByTagName("OverlayStat");
		for(int a = 0; a<overlayStats.getLength(); a++){
			Node stat = overlayStats.item(a);
			if(isElement(stat)){
				Element eleStat = (Element)stat;
				OverlayStat os = new OverlayStat(eleStat.getAttribute("label"), generalStat(eleStat));
				generalString(os, eleStat);
				getLocation(os, eleStat);
				tempGroup.add(os);
				myOverlayTracker.getStatOverlays().add(os);
			}
		}
	}
	
	
	
	
	  /**
	   * Converts a given string into a color.
	   * 
	   * @param value
	   *          the string, either a name or a hex-string.
	   * @return the color.
	   */
	  private static Color stringToColor(String value) {
	    if (value == null) {
	      return Color.black;
	    }
	    try {
	      // get color by hex or octal value
	      return Color.decode(value);
	    } catch (NumberFormatException nfe) {
	      // if we can't decode lets try to get it by name
	      try {
	        // try to get a color by name using reflection
	        Field f = Color.class.getField(value);

	        return (Color) f.get(null);
	      } catch (Exception ce) {
	        // if we can't get any color return black
	        return Color.black;
	      }
	    }
	  }
	  
	  private static int stringToFontStyle(String style) {
		    if (style == null) {
		      return Font.PLAIN;
		    }
		      // if we can't decode lets try to get it by name
		      try {
		        // try to get a color by name using reflection
		        Field f = Font.class.getField(style);

		        return f.getInt(null);
		      } catch (Exception ce) {
		        // if we can't get any color return black
		        return Font.PLAIN;
		      }
		    }
	  
	  private static GameFont getGameFont(String str){
		  boolean there = false;
		  for(String fonts: avalibleGameFonts){
			  if(fonts.toLowerCase().equals(str.toLowerCase())){
				  there = true;
			  }
		  }
		  if(there && myGame != null){
			  GameFontManager fontManager = new GameFontManager();
			  return fontManager.getFont(myGame.getImages("./src/vooga/engine/overlay/fontImages/font" + str.toLowerCase() + ".png", 20, 3),
						" !            .,0123" + "456789:   -? ABCDEFG"
								+ "HIJKLMNOPQRSTUVWXYZ ");
		  }else{
			  return null;
		  }
	  }
	  
	  public static void setGame(Game game){
		  myGame = game;
	  }
		  
	

}
