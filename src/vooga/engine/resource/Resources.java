package vooga.engine.resource;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;

import com.golden.gamedev.engine.BaseIO;

/**
 * 
 * The Resources class stores references to various resources which can be 
 * accessed from anywhere in the game. The available types of resources include 
 * BufferedImage Arrays, Sounds, Strings, Integers, and Doubles.
 * 
 * All of the available resources can be loaded on a one-at-a-time basis by
 * passing in a String filepath or by passing in a File.
 * 
 * Resources also has the ability to load multiple resources at once using an 
 * XML file. This is the main utility of the Resources class.
 * 
 * The vooga.engine.core.Game class calls the Resources.loadResourcesXMLFile method 
 * on the file "resources.xml". Each game should have a resources.xml file located in their
 * resources package. Further resources can be added later using any load method.
 * 
 * Images are stored as BufferedImage arrays. In the case of images which are //TODO is this implemented?
 * not animations, the array only has one element. Images can be retrieved as 
 * animations, but the animation will only have one frame. Likewise, animations 
 * can be retrieved as images and only the first frame will be retrieved. Images 
 * are loaded using the Game.loadImage() method and supports png, gif, bmp, and 
 * jpg files. //TODO provide example of how to play/use a sound
 *  
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

	public static String getDefaultPath() {
		return defaultPath;
	}
	/**
	 * Changes the Game of this Resources.
	 */
	public static void setGame(Game game) {
		myGame = game;
	}

	/**
	 * Changes the default path of this Resources.
	 */
	public static void setDefaultPath(String path) {
		defaultPath = path;
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

	//TODO use only the getVisual method?
	/**
	 * Returns the BufferedImage associated with the given label.
	 */
	public static BufferedImage getImage(String key) {
		return imageMap.get(key)[0];
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
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(defaultPath + resourcesXMLFilepath));
            doc.getDocumentElement().normalize();
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
	 * @param doc
	 * @param sectionTag
	 * @return
	 */
	private static List<Element> loadSectionElements(Document doc, String sectionTag) {
    	Node section = doc.getElementsByTagName(sectionTag).item(0);
    	NodeList nodeList = section.getChildNodes();
    	int length = nodeList.getLength();
    	ArrayList<Element> elementList = new ArrayList<Element>();
    	for (int i = 0; i < length; i++) {
    		Node node = nodeList.item(i);
    		if (node.getNodeType() == Node.ELEMENT_NODE) {
    			Element element = (Element) node;
    			elementList.add(element);
    		}
    	}
    	return elementList;
    }
	
	/**
     * Reads an imageListFile and initializes the ResourcesBeta's imageMap.
     * 
     * @param imageListFile
     * @throws IOException
     */
    public static void loadImageFile(String imageListFile) throws IOException {
            List<String> lines = getLinesFromFile(defaultPath + imageListFile);
            for (int y = 0; y < lines.size(); y++) {
                    String[] tokens = getTokensFromLine(lines.get(y));
                    String name = tokens[0];
                    String filepath = tokens[1];
                    int counter = 1;
                    ArrayList<String> pathList = new ArrayList<String>();
                    if (filepath.equals("")) {
                            System.out.println("Name " + name
                                            + "has no filepath associated with it.");
                    } else {
                            while (!filepath.equals("")) {
                                    pathList.add(filepath);
                                    if (counter < tokens.length) {
                                            filepath = tokens[counter];
                                            counter++;
                                    } else
                                            break;
                            }
                    }
                    String[] pathArray = new String[pathList.size()];
                    for (int i = 0; i < pathList.size(); i++) {
                            pathArray[i] = pathList.get(i);
                    }
                    loadAnimation(name, pathArray);
            }
    }

    /**
     * Reads an soundListFile and initializes the ResourcesBeta's soundMap.
     * 
     * @param soundListFile
     * @throws IOException
     */
    public static void loadSoundFile(String soundListFile) throws IOException {
            List<String> lines = getLinesFromFile(defaultPath + soundListFile);
            for (int y = 0; y < lines.size(); y++) {
                    String[] tokens = getTokensFromLine(lines.get(y));
                    loadSound(tokens[0], tokens[1]);
            }
    }

    public static void loadStringFile(String stringListFile) throws IOException {
            List<String> lines = getLinesFromFile(defaultPath + stringListFile);
            for (int y = 0; y < lines.size(); y++) {
                    String[] tokens = getTokensFromLine(lines.get(y));
                    loadString(tokens[0], tokens[1]);
            }
    }
    
    public static void loadIntFile(String intListFile) throws IOException {
            List<String> lines = getLinesFromFile(defaultPath + intListFile);
            for (int y = 0; y < lines.size(); y++) {
                    String[] tokens = getTokensFromLine(lines.get(y));
                    loadInt(tokens[0], Integer.parseInt(tokens[1]));
            }
    }
    
    public static void loadDoubleFile(String doubleListFile) throws IOException {
            List<String> lines = getLinesFromFile(defaultPath + doubleListFile);
            for (int y = 0; y < lines.size(); y++) {
                    String[] tokens = getTokensFromLine(lines.get(y));
                    loadDouble(tokens[0], Double.parseDouble(tokens[1]));
            }
    }
    
    public static void loadPropertiesFile(String resourceListFile)
                    throws IOException {
            List<String> lines = getLinesFromFile(defaultPath + resourceListFile);
            for (int y = 0; y < lines.size(); y++) {
                    String[] tokens = getTokensFromLine(lines.get(y));
                    String type = tokens[0];
                    String filepath = tokens[1];
                    int counter = 1;
                    ArrayList<String> pathList = new ArrayList<String>();
                    while (!filepath.equals("")) {
                            pathList.add(filepath);
                            if (counter < tokens.length) {
                                    filepath = tokens[counter];
                                    counter++;
                            } else
                                    break;
                    }
                    loadResourceFile(type, pathList);
            }
    }
    
    private static void loadResourceFile(String type, Collection<String> pathList) throws IOException{
            if(type.equalsIgnoreCase("Image")){
                    for(String path: pathList){
                            loadImageFile(path);
                    }
            } else if(type.equalsIgnoreCase("Sound")){
                    for(String path: pathList){
                            loadSoundFile(path);
                    }
            } else if(type.equalsIgnoreCase("String")){
                    for(String path: pathList){
                            loadStringFile(path);
                    }
            } else if(type.equalsIgnoreCase("Int")){
                    for(String path: pathList){
                            loadIntFile(path);
                    }
            }else if(type.equalsIgnoreCase("Double")){
                    for(String path: pathList){
                            loadDoubleFile(path);
                    }
            }else if(type.equalsIgnoreCase("Property")){
                    for(String path: pathList){
                            loadPropertiesFile(path);
                    }
            }
                    
    }

    /**
     * Utility method for the loadImageFile and loadSoundFile methods. This
     * method returns the lines in a given file as a List.
     * 
     * @param filePath
     * @return The list of lines.
     * @throws IOException
     */
    private static List<String> getLinesFromFile(String filePath)
                    throws IOException {
            List<String> lines = new ArrayList<String>();
            URL url = Resources.class.getClassLoader().getResource(filePath);
            url = new File(filePath).toURI().toURL();
            if (url == null) {
                    throw new IOException("No such directory: " + filePath);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(url
                            .openStream()));
            while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                            reader.close();
                            break;
                    }
                    if (!line.equals("") && !line.startsWith("#")) {
                            lines.add(line);
                    }
            }
            return lines;
    }

    /**
     * Utility method for the loadImageFile and loadSoundFile methods. This
     * method returns a String[] of tokens in a given line.
     * 
     * @param line
     * @return The String[] of tokens.
     */
    private static String[] getTokensFromLine(String line) {
            StringTokenizer st = new StringTokenizer(line, ",");
            int totalTokens = st.countTokens();
            String[] tokens = new String[totalTokens];
            for (int i = 0; i < totalTokens; i++) {
                    tokens[i] = st.nextToken();
            }
            return tokens;
    }
	
	
}