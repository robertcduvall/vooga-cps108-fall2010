package vooga.engine.resource;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.InputStream;

import com.golden.gamedev.*;


public class ResourcesBeta {
	private static String defaultPath = "";
	private static Game myGame;
	private static Map<String, BufferedImage[]> imageMap = new HashMap<String, BufferedImage[]>();
	private static Map<String, String> soundMap = new HashMap<String, String>();

	public static void initialize(Game game) {
		initialize(game, "");
	}

	public static void initialize(Game game, String defaultFilePath) {
		setGame(game);
		setDefaultPath(defaultFilePath);
	}
	
	public static void setGame(Game game) {
		myGame = game;
	}

	public static void setDefaultPath(String path) {
		defaultPath = path;
	}
	
	public static void loadImage(String key, File file) {
		imageMap.put(key,
				new BufferedImage[] { myGame.getImage(file.getPath()) });
	}

	public static void loadImage(String key, String filePath) {
		loadImage(key, new File(defaultPath + filePath));
	}

	public static void loadAnimation(String key, String[] filePaths) {
		BufferedImage[] animation = new BufferedImage[filePaths.length];
		for (int i = 0; i < animation.length; i++) {
			System.out.println("loading " + defaultPath + filePaths[i]);
			animation[i] = myGame.getImage(defaultPath + filePaths[i]);
		}
		imageMap.put(key, animation);
	}

	public static void loadAnimation(String key, File[] files) {
		BufferedImage[] animation = new BufferedImage[files.length];
		for (int i = 0; i < animation.length; i++) {
			animation[i] = myGame.getImage(files[i].getPath());
		}
		imageMap.put(key, animation);
	}

	/**
	 * Returns the BufferedImage associated with the given label.
	 * 
	 * @param key
	 *            The label of the image in the map.
	 * @return The BufferedImage.
	 */
	public static BufferedImage getImage(String key) {
		return imageMap.get(key)[0];
	}

	/**
	 * Returns the BufferedImage[] associated with the given label.
	 * 
	 * @param key
	 *            The label of the image in the map.
	 * @return The BufferedImage[].
	 */
	public static BufferedImage[] getAnimation(String key) {
		return imageMap.get(key);
	}
	
	public static void loadSound(String key, File file) {
		soundMap.put(key, file.getPath());
	}

	public static void loadSound(String key, String filePath) {
		loadSound(key, new File(defaultPath + filePath));
	}

	public static String getSound(String key) {
		return soundMap.get(key);
	}

//	public static void loadImageFile(String imageListFile) throws IOException {
//		StringTokenizer st;
//		List<String> lines = getLinesFromFile(imageListFile);
//		for (int y = 0; y < lines.size(); y++) {
//			String line = lines.get(y);
//			st = new StringTokenizer(line, ",");
//			String name = st.nextToken();
//			String filepath = st.nextToken();
//			ArrayList<String> pathList = new ArrayList<String>();
//			if (filepath.equals("")) {
//				System.out.println("Name " + name
//						+ "has no filepath associated with it.");
//			} else {
//				while (!filepath.equals("")) {
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
	
	public static void loadImageFile(String directory) throws IOException {
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

	public static void loadSoundFile(String soundListFile) throws IOException {
		StringTokenizer st;
		List<String> lines = getLinesFromFile(soundListFile);
		for (int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			st = new StringTokenizer(line, ",");
			String name = st.nextToken();
			String filepath = st.nextToken();
			loadSound(name, filepath);
		}
	}
	
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
	
	private static String[] getTokensFromLine(String line){
		//Placeholder. Will reduce redundancy in loading images vs sounds
		return null;
	}

	/**
	 * Returns as an InputStream the file specified by filename.
	 * 
	 * @param filename
	 *            The String representation of the filename.
	 * @return
	 */
	public static InputStream getResourceAsStream(String filename) {
		return ResourceHandler.class.getClassLoader().getResourceAsStream(
				filename);
	}

	
	

}
