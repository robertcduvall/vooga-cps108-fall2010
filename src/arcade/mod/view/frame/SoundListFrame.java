package arcade.mod.view.frame;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLNode;

/**
 * A SoundListFrame is essentially just a FilepathListFrame with unique
 * file-confirmation needs.
 * 
 * @author Brian
 *
 */
public class SoundListFrame extends FilepathListFrame{
	
	String[] acceptableSoundTags = {"wav","mid","mp3","tga"};
	
	public SoundListFrame() {
		// do nothing
	}

	public SoundListFrame(IResourceNode node) {
		super(node);
		
	}

	public SoundListFrame newInstance(IResourceNode node) {
		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
			return new SoundListFrame(node);
		else return new SoundListFrame();
	}
	
	@Override
	public boolean confirmValidity(){
		for (String tag: acceptableSoundTags){
			if (myFilepath.endsWith(tag))
				return true;
		}
		return false;
	}
	
}
