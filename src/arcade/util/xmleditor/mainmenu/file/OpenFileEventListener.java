package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import arcade.util.xmleditor.controllers.IBaseController;

public class OpenFileEventListener implements ActionListener {

	private IBaseController controller;

	public OpenFileEventListener(IBaseController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser loadChooser = new JFileChooser();
		loadChooser.setFileFilter(new XMLFileFilter());
		JFrame openFrame = new JFrame();
		loadChooser.showOpenDialog(openFrame);
		File file = loadChooser.getSelectedFile();
		controller.setModel(file);
	}

}
