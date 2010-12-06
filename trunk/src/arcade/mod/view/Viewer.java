package arcade.mod.view;

import java.io.File;
import java.util.Collection;

public interface Viewer {
	
	public void initialize();
	
	public File openFileSelect();
	
	public File saveFileSelect();
	
	public String getCurrentCategory();
	
	public void changeFrames(Collection<AbstractListFrame> frames);

}
