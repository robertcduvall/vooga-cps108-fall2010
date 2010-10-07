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
public class Resources {
	private static List<String> myDirectories = new ArrayList<String>();
	private static Map<String, String> myMap = new HashMap<String, String>();
	private static Game myGame;
	private static Map<String, BufferedImage[]> imageMap = new HashMap<String, BufferedImage[]>();
	
	
	public static void loadImage(String name, File file){
		imageMap.put(name, new BufferedImage[]{myGame.getImage(file.getPath())});
	}
	
	public static void loadImage(String name, String filePath){
		loadImage(name, new File(filePath));
	}
	
	public static void loadAnimation(String name, String[] filePaths) {
		BufferedImage[] animation = new BufferedImage[filePaths.length];
		for(int i=0; i<animation.length; i++){
			animation[i] = myGame.getImage(filePaths[i]);
		}
		imageMap.put(name, animation);
	}
	
	public static void loadAnimation(String name, File[] files){
		BufferedImage[] animation = new BufferedImage[files.length];
		for(int i=0; i<animation.length; i++){
			animation[i] = myGame.getImage(files[i].getPath());
		}
		imageMap.put(name, animation);
	}
	
	/**
	 * Returns the BufferedImage associated with the given label.
	 * @param key The label of the image in the map.
	 * @return The BufferedImage.
	 */
	public static BufferedImage getImage(String key) {
		return imageMap.get(key)[0];
	}
	
	/**
	 * Returns the BufferedImage[] associated with the given label.
	 * @param key The label of the image in the map.
	 * @return The BufferedImage[].
	 */
	public static BufferedImage[] getAnimation(String key) {
		return imageMap.get(key);
	}
	
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
		URL url = Resources.class.getClassLoader().getResource(directory);
		url = new File(directory).toURI().toURL();
		System.out.println(url);
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
			String line = lines.get(y);
			line = lines.get(y);
			st = new StringTokenizer(line, ",");
			String name = st.nextToken();
			String filepath = st.nextToken();
			ArrayList<String> pathList = new ArrayList<String>();
			if (filepath.equals("")) {
				System.out.println("Name " + name + "has no filepath associated with it.");
			} else {
				while(!filepath.equals("")) {
					pathList.add(filepath);
					if (st.hasMoreTokens())
						filepath = st.nextToken();
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
    
    
    // alternate loadFile code:
    
//	public static void loadFile(URL url) throws IOException {
//		addDirectory(url.toString());
//		ArrayList<String> lines = new ArrayList<String>();
//		int size = 0;
//		StringTokenizer st;
//		
//		System.out.println(url);
//		if (url == null) {
//			throw new IOException("No such directory: " + url.toString());
//		}
//		BufferedReader reader = new BufferedReader(
//				new InputStreamReader(url.openStream()));
//		while (true) {
//			String line = reader.readLine();
//			if (line == null) {
//				reader.close();
//				break;
//			}
//			if (!line.equals("") && !line.startsWith("#")) {
//				lines.add(line);
//			}
//		}
//		size = lines.size();
//		for (int y=0; y<size; y++) {
//			String line = (String)lines.get(y);
//			line = lines.get(y);
//			st = new StringTokenizer(line, ",");
//			String name = st.nextToken();
//			String filepath = st.nextToken();
//			ArrayList<String> pathList = new ArrayList<String>();
//			if (filepath.equals("")) {
//				System.out.println("Name " + name + "has no filepath associated with it.");
//			} else {
//				while(!filepath.equals("")) {
//					pathList.add(filepath);
//					if (st.hasMoreTokens())
//						filepath = st.nextToken();
//					else
//						break;
//				}
//			}
//			String[] pathArray = new String[pathList.size()];
//			for (int i = 0; i < pathList.size(); i++) {
//				pathArray[i] = pathList.get(i);
//				System.out.print(pathList.get(i));
//			}
//			System.out.println();
//			loadAnimation(name, pathArray);
//		}
//	}
//	/**
//	 * Loads a file from a directory string. Adds the directory string to myDirectories,
//	 * and puts the filepath->Object mappings into myMap. This method handles both
//	 * Images and "Animations".
//	 * @param directory The location of the file to load.
//	 * @throws IOException
//	 */
//	public static void loadFile(String directory) throws IOException {
//		URL url = Resources.class.getClassLoader().getResource(directory);
//		url = new File(directory).toURI().toURL();
//		loadFile(url);
//	}
//    
    
    
}
