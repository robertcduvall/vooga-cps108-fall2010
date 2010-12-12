package vooga.engine.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import vooga.engine.core.Game;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

import com.golden.gamedev.engine.BaseIO;

/**
 * 
 * The Resources class stores references to various resources which can be 
 * accessed from anywhere in the game. The available types of resources include 
 * BufferedImage Arrays, Sounds, Strings, Integers, and Doubles.
 * <br />
 * All of the available resources can be loaded on a one-at-a-time basis by
 * passing in a String filepath or by passing in a File.
 * <br />
 * Resources also has the ability to load multiple resources at once using an 
 * XML file. This is the main utility of the Resources class.
 * <br />
 * The vooga.engine.core.Game class calls the Resources.loadResourcesXMLFile method 
 * on the file "resources.xml". Each game should have a resources.xml file located in their
 * resources package. Further resources can be added later using any load method.
 * <br />
 * Images are stored as BufferedImage arrays. In the case of images which are
 * not animations, the array only has one element. Images can be retrieved as 
 * animations, but the animation will only have one frame. Likewise, animations 
 * can be retrieved as images and only the first frame will be retrieved. Images 
 * are loaded using the Game.loadImage() method and supports png, gif, bmp, and 
 * jpg files.
 * <br /> 
 * Example files following the standard XML format can be found in the 
 * examples.resources package.
 * 
 * @author John Kline, Daniel Koverman
 * 
 */
public class Resources {
	private static String defaultPath = "";
	private static Game myGame;
	private static Map<String, BufferedImage[]> imageMap = new HashMap<String, BufferedImage[]>();
	private static Map<String, String> soundMap = new HashMap<String, String>();
	private static Map<String, String> stringMap = new HashMap<String, String>();
	private static Map<String, Integer> integerMap = new HashMap<String, Integer>();
	private static Map<String, Double> doubleMap = new HashMap<String, Double>();

	/**
	 * Sets the Game of this Resources and sets the default path to be a
	 * blank string.
	 */
	public static void initialize(Game game) {
		initialize(game, "");
	}

	/**
	 * Sets the game of this Resources and sets the default path.
	 */
	public static void initialize(Game game, String defaultFilePath) {
		setGame(game);
		setDefaultPath(defaultFilePath);
		game.bsLoader.getBaseIO().setMode(BaseIO.CLASS_LOADER);
	}

	/**
	 * Changes the Game of this Resources.
	 */
	public static void setGame(Game game) {
		myGame = game;
	}
	
	/**
	 * Gets the game currently using Resources. Allows
	 * static access to a pointer to the current game.
	 * Maybe it's bad practice but everything requires a 
	 * reference to the top level entity to function properly
	 * and I'm tired of passing 
	 * @return
	 */
	public static Game getGame(){
		return myGame;
	}

	/**
	 * Changes the default path of this Resources.
	 */
	public static void setDefaultPath(String path) {
		defaultPath = path;
	}
	
	/**
	 * Returns the default path of this Resources.
	 */
	public static String getDefaultPath() {
		return defaultPath;
	}

	/**
	 * Puts a new entry into this Resources's imageMap, with a String key
	 * and a BufferedImage[] value. This method is used when a single image is
	 * being put into the map.
	 */
	public static void loadImage(String key, File file) {
		imageMap.put(key,
				new BufferedImage[] { myGame.getImage(file.getPath()) });
	}

	/**
	 * Puts a new entry into this Resources's imageMap, with a String key
	 * and a BufferedImage[] value. This method is used when a single image is
	 * being put into the map.
	 */
	public static void loadImage(String key, String filePath) {
		loadImage(key, new File(defaultPath + filePath));
	}

	/**
	 * Returns the BufferedImage associated with the given label.
	 */
	public static BufferedImage getImage(String key) {
		try{
			return imageMap.get(key)[0];
		} catch (NullPointerException e){
			System.out.println("Image for key " + key + " not found.");
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * Puts a new entry into this Resources's imageMap, with a String key
	 * and a BufferedImage[] value. This method is used when multiple images are
	 * being put into the map as an animation.
	 */
	public static void loadAnimation(String key, File[] files) {
		BufferedImage[] animation = new BufferedImage[files.length];
		for (int i = 0; i < animation.length; i++) {
			animation[i] = myGame.getImage(files[i].getPath());
		}
		imageMap.put(key, animation);
	}

	/**
	 * Puts a new entry into this Resources's imageMap, with a String key
	 * and a BufferedImage[] value. This method is used when multiple images are
	 * being put into the map as an animation.
	 */
	public static void loadAnimation(String key, String[] filePaths) {
		File[] files = new File[filePaths.length];
		for (int i = 0; i < filePaths.length; i++) {
			files[i] = new File(defaultPath + filePaths[i]);
		}
		loadAnimation(key, files);
	}
	
	/**
	 * Returns the BufferedImage[] associated with the given label.
	 */
	public static BufferedImage[] getAnimation(String key) {
		return imageMap.get(key);
	}
	
	/**
	 * Returns the BufferedImage[] associated with the given label.
	 */
	public static BufferedImage[] getVisual(String key) {
		return imageMap.get(key);
	}
	
	/**
	 * Puts a new entry into this Resources's soundMap, with a String key
	 * and a String value.
	 */
	public static void loadSound(String key, File file) {
		soundMap.put(key, file.getPath());
	}

	/**
	 * Puts a new entry into this Resources's soundMap, with a String key
	 * and a String value.
	 */
	public static void loadSound(String key, String filePath) {
		loadSound(key, new File(defaultPath + filePath));
	}

	/**
	 * Returns the String associated with the given Sound label.
	 */
	public static String getSound(String key) {
		return soundMap.get(key);
	}
	
	/**
	 * Puts a new entry into this Resources's stringMap, with a String key
	 * and a String value.
	 */
	public static void loadString(String key, String stringToLoad){
		stringMap.put(key, stringToLoad);
	}
	
	/**
	 * Returns the String associated with the given String label.
	 */
	public static String getString(String key){
		return stringMap.get(key);
	}
	
	/**
	 * Puts a new entry into this Resources's integerMap, with a String key
	 * and an int value.
	 */
	public static void loadInt(String key, int intToLoad){
		integerMap.put(key, intToLoad);
	}
	
	/**
	 * Returns the int associated with the given int label.
	 */
	public static int getInt(String key){
		return integerMap.get(key);
	}
	
	/**
	 * Puts a new entry into this Resources's doubleMap, with a String key
	 * and a double value.
	 */
	public static void loadDouble(String key, double doubleToLoad){
		doubleMap.put(key, doubleToLoad);
	}
	
	/**
	 * Returns the double associated with the given double label.
	 */
	public static double getDouble(String key){
		return doubleMap.get(key);
	}
	
	/**
	 * Reads the resources.xml file and initializes the Resources's mappings. Thanks to Lucas Best, Ben Getson, and Sam Kitange
	 * for XML parsing methods.
	 */
	public static void loadResourcesXMLFile(String resourcesXMLFilepath) throws IOException {
		try
        {
			XMLDocumentCreator xmlCreator = new XMLFileParser(defaultPath + resourcesXMLFilepath);
            Document doc = xmlCreator.getDocument();
            List<Element> newElements = loadSectionElements(doc, "Images");
            for (Element e: newElements) {
            	String name = e.getAttribute("name");
            	String path = e.getAttribute("path");
            	loadImage(name, "images/" + path);
            }
            newElements = loadSectionElements(doc, "Sounds");
            for (Element e: newElements) {
            	String name = e.getAttribute("name");
            	String path = e.getAttribute("path");
            	loadSound(name, "sounds/" + path);
            }
            newElements = loadSectionElements(doc, "Strings");
            for (Element e: newElements) {
            	String name = e.getAttribute("name");
            	String value = e.getAttribute("value");
            	loadString(name, value);
            }
            newElements = loadSectionElements(doc, "Integers");
            for (Element e: newElements) {
            	String name = e.getAttribute("name");
            	int value = Integer.parseInt(e.getAttribute("value"));
            	loadInt(name, value);
            }
            newElements = loadSectionElements(doc, "Doubles");
            for (Element e: newElements) {
            	String name = e.getAttribute("name");
            	double value = Double.parseDouble(e.getAttribute("value"));
            	loadDouble(name, value);
            }
            Node animationsSection = doc.getElementsByTagName("Animations").item(0);
            NodeList listOfAnimations = animationsSection.getChildNodes();
            int length = listOfAnimations.getLength();
            for (int i = 0; i < length; i++) {
            	Node node = listOfAnimations.item(i);
            	if (node.getNodeType() == Node.ELEMENT_NODE) {
	            	String animationName = ((Element) node).getAttribute("name");
	            	NodeList frames = node.getChildNodes();
	            	ArrayList<String> pathList = new ArrayList<String>();
	            	for (int j = 1; j < frames.getLength(); j++) {
	            		if (frames.item(j).getNodeType() == Node.ELEMENT_NODE)
	            			pathList.add("images/" + ( (Element) frames.item(j) ).getAttribute("path"));
	            	}
	            	String[] pathArray = new String[pathList.size()];
	    			for (int j = 0; j < pathList.size(); j++) {
	    				pathArray[j] = pathList.get(j);
	    			}
	            	loadAnimation(animationName, pathArray);
            	}
            }
        }
        catch (SAXParseException err)
        {
            System.err.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
            System.err.println(" " + err.getMessage());
        }
        catch (SAXException e)
        {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
	}
	
	/**
	 * Utility method for loadResourcesXMLFile - loads a Node's Elements and returns them in a List.
	 * @param doc The Document object that is being parsed.
	 * @param sectionTag The section being loaded.
	 */
	private static List<Element> loadSectionElements(Document doc,
			String sectionTag) {
		ArrayList<Element> elementList = new ArrayList<Element>();
		Node section = doc.getElementsByTagName(sectionTag).item(0);
		if (section != null) {
			NodeList nodeList = section.getChildNodes();
			int length = nodeList.getLength();
			for (int i = 0; i < length; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					elementList.add(element);
				}
			}
		}
		return elementList;
	}

	/**
	 * Get data to launch the game frame from the main() method of game. The 
	 * file should be named config.properties and contain the width, height, 
	 * and whether or not the game should be full screen.
	 * @param filePath file path to config.properties
	 * @return ResourceBundle containing necessary data for game launch
	 */
	public static ResourceBundle loadPreLaunchData(String filePath){
		return ResourceBundle.getBundle(filePath);
	}
	
}