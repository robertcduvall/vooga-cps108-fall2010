package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import arcade.util.xmleditor.Controller;

public class NewModelEventListener implements ActionListener{
	
	Controller controller;
	
	public NewModelEventListener(Controller controller){
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String filepath = System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "arcade" + File.separatorChar + "util" + File.separatorChar + "xmleditor" + File.separatorChar + "templates" + File.separatorChar + "newXML.xml"; 
		controller.setModel(new File(filepath));		
	}

}
