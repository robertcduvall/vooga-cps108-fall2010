package vooga.engine.factory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.PlayField;
import vooga.engine.factory.xmltags.Map;
import vooga.engine.factory.xmltags.SpriteGroups;


/**
 * This abstract class should be extended by subclasses in order to process
 * each type of unique object differently.
 * 
 * @author Cameron McCallie
 * @thanks to Ben Getson for providing refactoring suggestions
 */

public abstract class LevelNodeListFactory implements NodeList{
	
	
	protected PlayField myPlayfield;
	
	public abstract void process();
	
	public static boolean isElement(Node node){
		return (node.getNodeType() == Node.ELEMENT_NODE);
	}

	public static LevelNodeListFactory CreateNewLevelNodeList(Node node, String objectToProcess) {
		NodeList nodeList = node.getChildNodes();
        if(objectToProcess.equals("Map"))
        	return new Map(nodeList);
        else if(objectToProcess.equals("SpriteGroups"))
        	return new SpriteGroups(nodeList);
        else if(objectToProcess.equals("CollisionGroups"))
        	return new CollisionGroupsNodeList(nodeList);
        else if(objectToProcess.equals("Background"))
        	return new BackgroundNodeList(nodeList);
        else if(objectToProcess.equals("Music"))
        	return new MusicNodeList(nodeList);
        else if(objectToProcess.equals("Visual"))
        	return new VisualNodeList(nodeList);
        else if(objectToProcess.equals("RegularSprite"))
        	return new RegularSprite(nodeList);
        else if(objectToProcess.equals("SpawnedSprite"))
        	return new SpawnedSpriteList(nodeList);
        else if(objectToProcess.equals("Stat"))
        	return new StatNodeList(nodeList);
        else if(objectToProcess.equals("Control"))
        	return new ControlNodeList(nodeList);
        else
        	return null;
        	
	}
}
