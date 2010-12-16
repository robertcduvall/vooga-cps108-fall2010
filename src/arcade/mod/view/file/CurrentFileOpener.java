package arcade.mod.view.file;

import java.io.File;

import arcade.core.ArcadeGUIElements.GameView;

public class CurrentFileOpener implements IFileOpener {

	@Override
	public File openFile() {

		String currentGame = GameView.getGame().toLowerCase().replace(" ", "");
				
		String filepath = System.getProperty("user.dir");
		
		filepath = filepath + File.separatorChar + "src" + File.separatorChar + "vooga" + File.separatorChar + "games" + File.separatorChar + currentGame + File.separatorChar + "resources" + File.separatorChar + "resources.xml";
				
		return new File(filepath);
		
	}
	
}
