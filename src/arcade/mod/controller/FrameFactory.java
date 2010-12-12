package arcade.mod.controller;

import java.util.HashMap;
import java.util.Map;

import arcade.mod.model.IResourceNode;
import arcade.mod.view.frame.DoubleListFrame;
import arcade.mod.view.frame.ImageListFrame;
import arcade.mod.view.frame.IntegerListFrame;
import arcade.mod.view.frame.ListFrame;
import arcade.mod.view.frame.ObjectListFrame;
import arcade.mod.view.frame.SoundListFrame;
import arcade.mod.view.frame.StringListFrame;

/**
 * Create a ListFrame object based on a specified resource type. Right now these
 * are hard coded from a map, but eventually we would like to abstract to make
 * this process more data driven
 * 
 * @author Brian
 * @author Brent
 * @author Daniel
 * @author Vitor
 * 
 */
public class FrameFactory {

	private Map<String, ListFrame> myMappings = new HashMap<String, ListFrame>();

	/**
	 * Creates a new instance of FrameFactory and populates the map
	 */
	public FrameFactory() {
		// TODO:abstract this to make it more extendable
		myMappings.put("Images", new ImageListFrame());
		myMappings.put("Sounds", new SoundListFrame());
		myMappings.put("Strings", new StringListFrame());
		myMappings.put("Doubles", new DoubleListFrame());
		myMappings.put("Integers", new IntegerListFrame());
		myMappings.put("Sprites", new ObjectListFrame()); // not sure about this one
	}

	/**
	 * Creates a ListFrame from a ResourceNode for use in the Controller
	 * 
	 * @param resourceType
	 *            respresents the type of resource (i.e.image, sound, etc.)
	 * @param node
	 *            ResourceNode to be converted
	 * @return AbstractListFrame to be used for the user interface
	 */
	public ListFrame createFrame(String resourceType, IResourceNode node) {
		return myMappings.get(resourceType).newInstance(node);
	}
}
