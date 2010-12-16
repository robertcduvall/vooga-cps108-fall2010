package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import arcade.util.xmleditor.IBaseController;

public class OpenTemplateEventListener implements ActionListener {

	IBaseController controller;

	public OpenTemplateEventListener(IBaseController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String filepath = System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "arcade" + File.separatorChar + "util" + File.separatorChar + "xmleditor" + File.separatorChar + "templates"; 
		JFileChooser loadChooser = new JFileChooser();
		loadChooser.setFileFilter(new XMLFileFilter());
		loadChooser.setCurrentDirectory(new File(filepath));
		JFrame openFrame = new JFrame();
		loadChooser.showOpenDialog(openFrame);
		File file = loadChooser.getSelectedFile();
		controller.setModel(file);
	}
}
