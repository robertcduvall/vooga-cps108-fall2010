package arcade.mod.view.file;

import java.io.File;

import arcade.core.GameView;

public class CurrentFileOpener implements IFileOpener {

	@Override
	public File openFile() {

		String currentGame = GameView.getGame().toLowerCase().replace(" ", "");
		
		//TODO: this is temporary because currently the currentGame is always zombieland and they arent standards compliant
		currentGame = "digger";
		
		String filepath = System.getProperty("user.dir");
		
		System.out.println(filepath);

		filepath = filepath + File.separatorChar + "src" + File.separatorChar + "vooga" + File.separatorChar + "games" + File.separatorChar + currentGame + File.separatorChar + "resources" + File.separatorChar + "resources.xml";
		
		System.out.println(filepath);
		
		return new File(filepath);
		
	}
	
}