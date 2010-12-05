package arcade.mod.controller;

import java.util.HashMap;
import java.util.Map;

import arcade.mod.model.ResourceNode;
import arcade.mod.view.AbstractListFrame;
import arcade.mod.view.FilepathListFrame;
import arcade.mod.view.ImageListFrame;

public class FrameFactory {
	
	private static Map<String, AbstractListFrame> myMappings = new HashMap<String, AbstractListFrame>(); 
	
	public FrameFactory(){
		
		//TODO:abstract this to make it more extendable
		myMappings.put("Images", new ImageListFrame());
		myMappings.put("Filepath", new FilepathListFrame());
	}
	
	public static AbstractListFrame createFrame(String resourceType, ResourceNode node){
		return myMappings.get(resourceType).newInstance(node);
	}
}
