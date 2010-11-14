package vooga.engine.overlay;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
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
 * This class is used for creating overlays from an xml file instead of in the
 * code itself.
 * 
 * <p>
 *         example
 *         </p>
 *         <xmp>
 *         OverlayCreator.setGame(this); //This is assuming you are in the game class
 *         
 *         //Also this does not need to be done if you are using normal fonts.
 *         //If you use any of the three provided GameFont's then you must set
 *         //the game. Eventually the GameFont's should be in Resources so you
 *         //should not have to set the game

 *         OverlayTracker track = OverlayCreator.createOverlays(String xmlFileLocation);

 *         // the String xmlFileLocation must be path from the root of the project

 *         Stat<Integer> myLives = track.getStat("lives", new Integer(0));
		   Stat<Integer> myScore = track.getStat("score", new Integer(0));

 *         PlayField myPlayfield = new PlayField();
 *         
 *         myPlayfield.addGroup(track.getOverlayGroup(name));
 *         myPlayfield.addGroup(track.getOverlayGroup(name));

 *         // Playfield can also be replaced with States from the GameState API
 *         </xmp>
 * 
 *         <A HREF="OverlayExample.xml">Click here to view xml file example</A>
 *         <p>
 *         Here are all the attributes for all of the different overlays/p>
 *         <ul>
 *         <li>OverlayGroup
 *         <ul>name - key in map</ul>
 *         </li>
 *         <li>Stat
 *         <ul>
 *         <li>type - must be "Integer" or "String". This will ideally change
 *         soon so it can be of any type.</li>
 *         <li>value - initial value</li>
 *         <li>name - key in map</li>
 *         </ul>
 *         </li>
 *         <li>All Overlays
 *         <ul>
 *         <li>xLoc - x location of the overlay</li>
 *         <li>yLoc - y location of the overlay</li>
 *         <li>name - key in map</li>
 *         </ul>
 *         <li>OverlayString
 *         <ul>
 *         <li>label - String to be displayed</li>
 *         <li>fontName - any String representing a font, for example
 *         "Times New Roman", or "gameFontGreen"</li>
 *         <li>fontSize - point size of the font, must be an integer</li>
 *         <li>fontStyle - style of the font, for example "ITALIC"</li>
 *         <li>color - color of the font, for example "green". It can also be a
 *         octal or hexidecimal representation of the color</li>
 *         <li>And all attributes in all Overlays</li>
 *         </ul>
 *         </li>
 *         <li>OverlayStat
 *         <ul>
 *         <li>stat - The stat to be displayed. It must be an integer
 *         representing the order in which the stat was made. if it was the
 *         first one made it would be "0".</li>
 *         <li>All attributes in OverlayString</li>
 *         </ul>
 *         </li>
 *         <li>OverlayStatImage
 *         <ul>
 *         <li>image - String the image is saved as in the Resources API</li>
 *         <li>width - scale the size of the image to this width</li>
 *         <li>height - scale the size of the image to this height. In order to
 *         scale thw width and height must be set</li>
 *         <li>All of the attributes in all Overlays</li>
 *         </ul>
 *         </li>
 *         <li>OverlayIcon
 *         <ul>
 *         <li>stat - The stat to be used to display icons. Must be a Stat of
 *         type Integer. It must be an integer representing the order in which
 *         the stat was made. if it was the first one made it would be "0".</li>
 *         <li>All attributes in OverlayIcon</li>
 *         <li>All attributes in OverlayString</li>
 *         </ul>
 *         </li>
 *         <li>OverlayBar
 *         <ul>
 *         <li>stat - The stat to be displayed on bar. Must be a Stat of type
 *         Integer. It must be an integer representing the order in which the
 *         stat was made. if it was the first one made it would be "0".</li>
 *         <li>max - the max value of the bar. For example if the max is 100 and
 *         the stat is at 50 the bar will be half full</li>
 *         <li>color - The color of the value of the bar. By default red. Same
 *         format as the color in OverlayString</li>
 *         <li>backColor - The color of the background of the bar. By default
 *         black. Same format as the color in OverlayString</li>
 *         <li>length - The length of the bar</li>
 *         <li>height - The height of the bar</li>
 *         </ul>
 *         </li>
 *         </ul>
 * 
 * 
 * @author Justin Goldsmith
 */
public class OverlayCreatorTemp {

	private static OverlayTrackerTemp myOverlayTracker;
	private static final String[] avalibleGameFonts = new String[] { "red",
			"orange", "green" };
	private static Game myGame;
	private static Map<String, String> myClassMap;
	private static int myOverlayCount;
	private static int myOverlayGroupCount;

	/**
	 * 
	 * @param xmlFileLocation
	 *            - the XML File Location. String xmlFileLocation must be path
	 *            from the root of the project
	 * @return OverlayTracker containing all stats and overlays.
	 */
	public static OverlayTrackerTemp createOverlays(String xmlFileLocation) {
		myOverlayTracker = new OverlayTrackerTemp();
		initializeClassMap();
		myOverlayCount = 0;
		myOverlayGroupCount = 0;
		try {
			File file = new File(xmlFileLocation);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList statList = doc.getElementsByTagName("Stat");
			NodeList overlayList = doc.getElementsByTagName("OverlayGroup");
			if (!processStats(statList)) {
				return null;
			}
			if (!processOverlays(overlayList)) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return myOverlayTracker;

	}

	private static void initializeClassMap() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("src/vooga/engine/overlay/overlays.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<String> set = props.stringPropertyNames();
		myClassMap = new TreeMap<String, String>();
		for (String str : set) {
			myClassMap.put(str, props.getProperty(str));
		}

	}

	private static boolean processOverlays(NodeList overlayList) {
		Set<String> classSet = myClassMap.keySet();
		for (int s = 0; s < overlayList.getLength(); s++) {
			SpriteGroup tempGroup = new SpriteGroup("group" + s);
			Node group = overlayList.item(s);
			if (isElement(group)) {
				Element eleGroup = (Element) group;
				myOverlayGroupCount++;
				for (String tagName : classSet) {
					NodeList currentOverlays = eleGroup
							.getElementsByTagName(tagName);
					for (int i = 0; i < currentOverlays.getLength(); i++) {
						Node overlay = currentOverlays.item(i);
						if (isElement(overlay)) {
							Element eleOverlay = (Element) overlay;
							tempGroup.add(createOverlay(eleOverlay, tagName));
						}
					}
				}

				String name = eleGroup.getAttribute("name");
				if (name == null) {
					name = "OverlayGroup_" + myOverlayGroupCount;
				}

				myOverlayTracker.putOverlayGroup(name, tempGroup);

			} else {
				return false;
			}
		}

		return true;
	}

	private static Overlay createOverlay(Element overlay, String type) {
		try {
			myOverlayCount++;
			Class<?> cls = Class.forName(myClassMap.get(type));
			NamedNodeMap nNM = overlay.getAttributes();
			Map<String, String> attributes = new TreeMap<String, String>();
			for (int i = 0; i < nNM.getLength(); i++) {
				Attr temp = (Attr) nNM.item(i);
				String name = temp.getName();
				attributes.put(name, overlay.getAttribute(name));
			}
			Class partypes[] = { Map.class, OverlayTrackerTemp.class };
			Object arglist[] = { attributes, myOverlayTracker };
			Constructor ct = cls.getConstructor(partypes);
			Overlay current = (Overlay) ct.newInstance(arglist);
			String name = attributes.get("name");
			if (name == null) {
				name = "overlay_" + myOverlayCount;
			}
			myOverlayTracker.putOverlay(name, current);
			return current;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private static boolean processStats(NodeList statList) {
		for (int s = 0; s < statList.getLength(); s++) {
			Node stat = statList.item(s);
			if (isElement(stat)) {
				Element elmnt = (Element) stat;
				String type = elmnt.getAttribute("type");
				String value = elmnt.getAttribute("value");
				String name = elmnt.getAttribute("name");
				if(name.equals("")){
					name = "Stat_" + s + 1;
				}
				if (type.toLowerCase().equals("integer")) {
					myOverlayTracker.putStat(name , 
							new Stat<Integer>(Integer.valueOf(value)));
				} else if (type.toLowerCase().equals("string")) {
					myOverlayTracker.putStat(name , new Stat<String>(value));
				} else {
					return false;
				}
			} else {
				return false;
			}

		}

		return true;
	}

	private static boolean isElement(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			return true;
		}
		return false;
	}

	/**
	 * Converts a given string into a color.
	 * 
	 * @param value
	 *            the string, either a name or a hex-string.
	 * @return the color.
	 */
	protected static Color stringToColor(String value) {
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

	protected static int stringToFontStyle(String style) {
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

	protected static GameFont getGameFont(String str) {
		boolean there = false;
		for (String fonts : avalibleGameFonts) {
			if (fonts.toLowerCase().equals(str.toLowerCase())) {
				there = true;
			}
		}
		if (there && myGame != null) {
			GameFontManager fontManager = new GameFontManager();
			return fontManager.getFont(myGame.getImages(
					"./src/vooga/engine/overlay/fontImages/font"
							+ str.toLowerCase() + ".png", 20, 3),
					" !            .,0123" + "456789:   -? ABCDEFG"
							+ "HIJKLMNOPQRSTUVWXYZ ");
		} else {
			return null;
		}
	}

	public static void setGame(Game game) {
		myGame = game;
	}

}
