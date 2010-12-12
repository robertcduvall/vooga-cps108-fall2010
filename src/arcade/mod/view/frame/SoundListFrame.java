package arcade.mod.view.frame;

/**
 * A SoundListFrame is essentially just a FilepathListFrame with unique
 * file-confirmation needs.
 * 
 * @author Brian
 *
 */
public class SoundListFrame extends FilepathListFrame{
	
	String[] acceptableSoundTags = {"wav","mid","mp3","tga"};
	
	@Override
	public boolean confirmValidity(){
		for (String tag: acceptableSoundTags){
			if (myFilepath.endsWith(tag))
				return true;
		}
		return false;
	}
	
}
