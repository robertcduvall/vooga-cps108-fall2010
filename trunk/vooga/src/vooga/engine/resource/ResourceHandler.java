package vooga.engine.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ResourceHandler class represents a type of entity that keeps track of the resources being
 * used by the game. Currently, there are two types of ResourceHandler: ImageHandler and SoundHandler.
 * A ResourceHandler contains both a list of directories from which resources may be loaded, as well as
 * a map that maps String names to Objects, which currently may be Images, Animations, Sounds, or Sequences.
 * @author John Kline
 * @date September 26, 2010
 *
 */
public abstract class ResourceHandler {

	private List<String> myDirectories;
	private Map<String, Object> myMap;
	
	public ResourceHandler() {
		this.myDirectories = new ArrayList<String>();
		this.myMap = new HashMap<String, Object>();
	}
	
	public ResourceHandler(List<String> dirs) {
		this.myDirectories = dirs;
		this.myMap = new HashMap<String, Object>();
	}
	
	public void setDirectories(List<String> newDirs) {
		this.myDirectories = newDirs; 
	}

	public void addDirectory(String newDir) {
		this.myDirectories.add(newDir);
	}

	public void removeDirectory(String remDir) {
		this.myDirectories.remove(remDir);
	}

	public void addMapping(String string, Object object) {
		this.myMap.put(string, object);
	}
	
	public void removeMapping(String name) {
		this.myMap.remove(name);
	}
	
	public Object getMapping(String name) {
		return this.myMap.get(name);
	}

	/**
	 * This method should be implemented by subclasses to allow for resources to be loaded from
	 * a file specified by a directory string.
	 * @param directory The directory from which to load the file.
	 * @throws IOException
	 */
	public abstract void loadFile(String directory) throws IOException; 

	/**
	 * Invokes the loadFile() method on all directories in myDirectories.
	 */
	public void loadMyDirectories() {
		for (String str: myDirectories) {
			try {
				loadFile(str);
			} catch(IOException ex) {
			}
		}
	}
}

