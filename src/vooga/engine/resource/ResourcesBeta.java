package vooga.engine.resource;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.golden.gamedev.*;

/**
 * The ResourcesBeta class manages the Images, Animations, and Sounds which will be
 * needed in the game. It maintains two maps:
 * imageMap maps String labels to BufferedImage[] animations.
 * soundMap maps String labels to String filepaths.
 * 
 * Golden T has quite strong Image and Animation management, and so the ResourcesBeta
 * class has been simplified in this most recent version. In Golden T, one should place the following code
 * in their main class' initResources() method:
 * 
 * ResourcesBeta.initialize(this);
 * ResourcesBeta.setDefaultPath("pathname"); [If a default path is desired]
 * ResourcesBeta.loadImageFile("imagelist.txt");
 * ResourcesBeta.loadSoundFile("soundlist.txt");
 * These calls will initialize the ResourcesBeta maps.
 * 
 * Then, one can retrieve an image:
 * ResourcesBeta.getImage("name");
 * an animation:
 * ResourcesBeta.getAnimation("name");
 * or a sound:
 * ResourcesBeta.getSound("name");
 * 
 * Example text file format:
 * #Resources are put into the ResourceHandler.myMap field as follows:
 * #(Lines preceded by a '#' sign are considered comments)
 * *resource1 name*,*resource1 filepath*
 * *resource2 name*,*resource2 filepath*
 * ...
 * 
 * @author John Kline, Daniel Koverman
 *
 */
public class ResourcesBeta {
	private static String defaultPath = "";
	private static Game myGame;
	private static Map<String, BufferedImage[]> imageMap = new HashMap<String, BufferedImage[]>();
	private static Map<String, String> soundMap = new HashMap<String, String>();

	/**
	 * Sets the Game of this ResourcesBeta and sets the default path to be a blank string.
	 * @param game
	 */
	public static void initialize(Game game) {
		initialize(game, "");
	}

	/**
	 * Sets the game of this ResourcesBeta and sets the default path.
	 * @param game
	 * @param defaultFilePath
	 */
	public static void initialize(Game game, String defaultFilePath) {
		setGame(game);
		setDefaultPath(defaultFilePath);
	}
	
	/**
	 * Changes the Game of this ResourcesBeta.
	 * @param game
	 */
	public static void setGame(Game game) {
		myGame = game;
	}

	/**
	 * Changes the default path of this ResourcesBeta.
	 * @param path
	 */
	public static void setDefaultPath(String path) {
		defaultPath = path;
	}
	
	/**
	 * Puts a new entry into this ResourcesBeta's imageMap, with a String key and a BufferedImage[] value. 
	 * This method is used when a single image is being put into the map.
	 * @param key
	 * @param file
	 */
	public static void loadImage(String key, File file) {
		imageMap.put(key,
				new BufferedImage[] { myGame.getImage(file.getPath()) });
	}
	
	/**
	 * Puts a new entry into this ResourcesBeta's imageMap, with a String key and a BufferedImage[] value. 
	 * This method is used when a single image is being put into the map.
	 * @param key
	 * @param filePath
	 */
	public static void loadImage(String key, String filePath) {
		loadImage(key, new File(defaultPath + filePath));
	}

	/**
	 * Puts a new entry into this ResourcesBeta's imageMap, with a String key and a BufferedImage[] value. 
	 * This method is used when multiple images are being put into the map as an animation.
	 * @param key
	 * @param files
	 */
	public static void loadAnimation(String key, File[] files) {
		BufferedImage[] animation = new BufferedImage[files.length];
		for (int i = 0; i < animation.length; i++) {
			animation[i] = myGame.getImage(files[i].getPath());
		}
		imageMap.put(key, animation);
	}
	
	/**
	 * Puts a new entry into this ResourcesBeta's imageMap, with a String key and a BufferedImage[] value. 
	 * This method is used when multiple images are being put into the map as an animation.
	 * @param key
	 * @param files
	 */
	public static void loadAnimation(String key, String[] filePaths) {
		File[] files = new File[filePaths.length];
		for (int i = 0; i < filePaths.length; i++) {
			files[i] = new File(defaultPath + filePaths[i]);
		}
		loadAnimation(key, files);
	}

	/**
	 * Returns the BufferedImage associated with the given label.
	 * @param key
	 *            The label of the image in the map.
	 * @return The BufferedImage.
	 */
	public static BufferedImage getImage(String key) {
		return imageMap.get(key)[0];
	}

	/**
	 * Returns the BufferedImage[] associated with the given label.
	 * @param key
	 *            The label of the image in the map.
	 * @return The BufferedImage[].
	 */
	public static BufferedImage[] getAnimation(String key) {
		return imageMap.get(key);
	}
	
	/**
	 * Puts a new entry into this ResourcesBeta's soundMap, with a String key and a String value. 
	 * @param key
	 * @param file
	 */
	public static void loadSound(String key, File file) {
		soundMap.put(key, file.getPath());
	}

	/**
	 * Puts a new entry into this ResourcesBeta's soundMap, with a String key and a String value. 
	 * @param key
	 * @param filePath
	 */
	public static void loadSound(String key, String filePath) {
		loadSound(key, new File(defaultPath + filePath));
	}

	/**
	 * Returns the String associated with the given sound label.
	 * @param key
	 *            The label of the sound in the map.
	 * @return The String filepath.
	 */
	public static String getSound(String key) {
		return soundMap.get(key);
	}

	/**
	 * Reads an imageListFile and initializes the ResourcesBeta's imageMap.
	 * @param imageListFile
	 * @throws IOException
	 */
	public static void loadImageFile(String imageListFile) throws IOException {
		List<String> lines = getLinesFromFile(defaultPath + imageListFile);
		for (int y = 0; y < lines.size(); y++) {
			String[] tokens = getTokensFromLine(lines.get(y));
			System.out.println("tokens="+tokens.length);
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
					}
					else
						break;
				}
			}
			String[] pathArray = new String[pathList.size()];
			for (int i = 0; i < pathList.size(); i++) {
				pathArray[i] = pathList.get(i);
				System.out.print(pathList.get(i));
			}
			System.out.println();
			loadAnimation(name, pathArray);
		}
	}

	/**
	 * Reads an soundListFile and initializes the ResourcesBeta's soundMap.
	 * @param soundListFile
	 * @throws IOException
	 */
	public static void loadSoundFile(String soundListFile) throws IOException {
		List<String> lines = getLinesFromFile(defaultPath+soundListFile);
		for (int y = 0; y < lines.size(); y++) {
			String[] tokens = getTokensFromLine(lines.get(y));
			String name = tokens[0];
			String filepath = tokens[1];
			loadSound(name, filepath);
		}
	}
	
	/**
	 * Utility method for the loadImageFile and loadSoundFile methods. This method returns
	 * the lines in a given file as a List.
	 * @param filePath
	 * @return The list of lines.
	 * @throws IOException
	 */
	private static List<String> getLinesFromFile(String filePath) throws IOException{
		List<String> lines = new ArrayList<String>();
		URL url = Resources.class.getClassLoader().getResource(filePath);
		url = new File(filePath).toURI().toURL();
		System.out.println(url);
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
	 * Utility method for the loadImageFile and loadSoundFile methods. This method returns
	 * a String[] of tokens in a given line.
	 * @param line
	 * @return The String[] of tokens.
	 */
	private static String[] getTokensFromLine(String line){
		StringTokenizer st = new StringTokenizer(line, ",");
		int totalTokens = st.countTokens();
		String[] tokens = new String[totalTokens];
		for (int i = 0; i < totalTokens; i++) {
			tokens[i] = st.nextToken();
		}
		return tokens;
	}
}