package vooga.engine.resource;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.InputStream;

import com.golden.gamedev.Game;

/**
 * The ResourceHandler class manages the Images, "Animations", and Sounds which will be
 * needed in the game. It maintains a map that connects String labels to String filepaths. 
 * 
 * Golden T luckily has very strong Image and Animation management, and so the ResourceHandler
 * class has been simplified in this most recent version. In Golden T, one should place the following code
 * in their main class' initResources() method:
 * 
 * ResourceHandler.setGame(this);
 * ResourceHandler.loadFile("resourcelist.txt");
 * 
 * Then, one can retrieve an image as follows:
 * ResourceHandler.getImage("name");
 * 
 * Example text file format::
 * #Resources are put into the ResourceHandler.myMap field as follows:
 * #*filepath1*,*resource1 name*
 * #*filepath2*,*resource2 name*
 * #...
 * 
 * @author John Kline
 *
 */
public class ResourceHandler {
	private static List<String> myDirectories = new ArrayList<String>();
	private static Map<String, String> myMap = new HashMap<String, String>();
	private static Game myGame;
	
	/**
	 * Adds a new directory to the ImageHandler's list of directories.
	 * @param newDirectory The directory to add.
	 */
	public static void addDirectory(String newDirectory) {
		myDirectories.add(newDirectory);
	}

	/**
	 * Loads a file from a directory string. Adds the directory string to myDirectories,
	 * and puts the filepath->Object mappings into myMap. This method handles both
	 * Images and "Animations".
	 * @param directory The location of the file to load.
	 * @throws IOException
	 */
	public static void loadFile(String directory) throws IOException {
		addDirectory(directory);
		ArrayList<String> lines = new ArrayList<String>();
		int size = 0;
		StringTokenizer st;
		URL url = ResourceHandler.class.getClassLoader().getResource(directory);
		if (url == null) {
			throw new IOException("No such directory: " + directory);
		}
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(url.openStream()));
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
		size = lines.size();
		for (int y=0; y<size; y++) {
			String line = (String)lines.get(y);
			line = lines.get(y);
			st = new StringTokenizer(line, ",");
			String filepath = st.nextToken();
			String name = st.nextToken();
			if (name.equals("")) {
				System.out.println("Filepath " + y + "has no reference name associated with it.");
			} else {
				addMapping(name, filepath);
			}
		}
	}

	/**
	 * Adds a new mapping to myMap.
	 * @param key The String key to add.
	 * @param object The Object value associated with key.
	 */
	private static void addMapping(String key, String value) {
		myMap.put(key, value);
	}

	/**
	 * Returns the Object associated with key from myMap.
	 * @param name
	 * @return
	 */
	public static String getMapping(String key) {
		return myMap.get(key);
	}
	
	/**
	 * Returns the BufferedImage associated with the given label.
	 * @param key The label of the image in the map.
	 * @return The BufferedImage.
	 */
	public static BufferedImage getImage(String key) {
		return myGame.getImage(getMapping(key));
	}
	
	/**
	 * Returns the BufferedImage[] associated with the given label.
	 * @param key The label of the image in the map.
	 * @return The BufferedImage[].
	 */
	public static BufferedImage[] getImages(String key, int c, int r) {
		return myGame.getImages(getMapping(key), c, r);
	}
	
	/**
	 * Sets the Game field in ResourceHandler.
	 * @param game The Game.
	 */
	public static void setGame(Game game) {
		myGame = game;
	}
	
    /**
     * Returns as an InputStream the file specified by filename.
     * @param filename The String representation of the filename.
     * @return
     */
    public static InputStream getResourceAsStream(String filename) {
        return ResourceHandler.class.getClassLoader().
            getResourceAsStream(filename);
    }
}
