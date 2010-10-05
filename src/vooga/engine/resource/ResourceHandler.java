package vooga.engine.resource;

import java.awt.Image;
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

import javax.swing.ImageIcon;

/**
 * The ResourceHandler class manages the Images, "Animations", and Sounds which will be
 * needed in the game. It maintains a map that connects String labels to Objects, which 
 * currently are Images, BufferedImage[]'s, and String filepaths for Images, "Animations",
 * and Sounds, respectively. When the game initializes its resources, the following code
 * should be executed:
 * 
 * ResourceHandler.loadFile("resourcelist.txt");
 * 
 * This call will create the ResourceHandler's map, and its elements (such as a background 
 * image that has been labeled as "Background") can be accessed using this example code:
 * 
 * ResourceHandler.getMapping("Background");
 * 
 * Sounds are currently handled by the basic mapping of "Label"->filepath.
 * 
 * @author John Kline
 *
 */
public class ResourceHandler {
	private static List<String> myDirectories = new ArrayList<String>();
	private static Map<String, Object> myMap = new HashMap<String, Object>();

	/**
	 * Adds the Image specified at "filepath" to myMap with key="name".
	 * @param name The name to be associated with the image.
	 * @param filepath The filesystem location of the image to be added to the map. 
	 */
	public static void addImageMapping(String name, String filepath) {
		Image image = new ImageIcon(ResourceHandler.class.getClassLoader().getResource(name)).getImage();
		addMapping(name, image);
	}

	/**
	 * Adds the Image "image" to myMap with key="name".
	 * @param name The name to be associated with the image.
	 * @param image The image to be added to the map.
	 */
	public static void addImageMapping(String name, Image image) {
		addMapping(name, image);
	}

	/**
	 * Adds the Animation "animation" to myMap with key="name".
	 * @param name The name to be associated with the animation.
	 * @param animation The animation to be added to the map.
	 */
	public static void addAnimationMapping(String name, BufferedImage[] animation) {
		addMapping(name, animation);
	}

	/**
	 * Adds the filepath associated with the Sound to myMap with key="name".
	 * @param name The name to be associated with the Sound located at the filepath.
	 * @param filepath The location of the Sound in the filesystem.
	 */
	public static void addSoundMapping(String name, String filepath) {
		addMapping(name, filepath);
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
		boolean animationMode = false;
		boolean imageMode = false;
		boolean soundMode = false;
		for (int y=0; y<size; y++) {
			String line = (String)lines.get(y);
			if (line.equals("$Images")) {
				animationMode = false;
				imageMode = true;
				soundMode = false;
				y++;
			} else if (line.equals("$Animations")) {
				animationMode = true;
				imageMode = false;
				soundMode = false;
				y++;
			} else if (line.equals("$Sounds")) {
				animationMode = false;
				imageMode = false;
				soundMode = true;
			}
			if (imageMode) {
				line = lines.get(y);
				st = new StringTokenizer(line, ",");
				String filepath = st.nextToken();
				String name = st.nextToken();
				Image img = null;
				if (name.equals("")) {
					System.out.println("Filepath " + y + "has no reference name associated with it.");
				} else {
					img = new ImageIcon(ResourceHandler.class.getClassLoader().getResource(filepath)).getImage(); 
				}
				if (img != null)
					addImageMapping(name, img);

			} else if (animationMode) {
				st = new StringTokenizer(lines.get(y), ",");
				String animationName = st.nextToken();
				int animationSize = Integer.parseInt(st.nextToken());	
				BufferedImage[] animation = new BufferedImage[animationSize];
				y++;
				line = lines.get(y);
				st = new StringTokenizer(line, ",");
				int index = 0;
				while(st.hasMoreTokens()) {
					String filepath = st.nextToken();
					BufferedImage image = (BufferedImage) new ImageIcon(
							ResourceHandler.class.getClassLoader().getResource(filepath)).getImage();
					animation[index] = image;
					index++;
				}
				addAnimationMapping(animationName, animation);
			} else if (soundMode) {
				line = lines.get(y);
				st = new StringTokenizer(line, ",");
				String filepath = st.nextToken();
				String name = st.nextToken();
				if (name.equals("")) {
					System.out.println("Filepath " + y + "has no reference name associated with it.");
				} else {
					addSoundMapping(name, filepath);
				}
			}
		}
	}

	/**
	 * Adds a new mapping to myMap.
	 * @param key The String key to add.
	 * @param object The Object value associated with key.
	 */
	private static void addMapping(String key, Object object) {
		myMap.put(key, object);
	}

	/**
	 * Returns the Object associated with key from myMap.
	 * @param name
	 * @return
	 */
	public static Object getMapping(String key) {
		return myMap.get(key);
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
