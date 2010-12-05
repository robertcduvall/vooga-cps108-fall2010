package arcade.mod.controller;

import java.util.HashMap;
import java.util.Map;

import arcade.mod.model.ResourceNode;
import arcade.mod.view.AbstractListFrame;
import arcade.mod.view.FilepathListFrame;
import arcade.mod.view.ImageListFrame;

public class FrameFactory {
	
	private Map<String, AbstractListFrame> myMappings = new HashMap<String, AbstractListFrame>(); 
	
	public FrameFactory(){
		myMappings.put("Images", new ImageListFrame());
		myMappings.put("Filepath", new FilepathListFrame());
	}
	
	public AbstractListFrame createFrame(String resourceType, ResourceNode node){
		myMappings.get(resourceType).newInstance(node);
		return null;	
	}
}
