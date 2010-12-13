package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import arcade.util.xmleditor.Controller;

public class OpenFileEventListener implements ActionListener {

	Controller controller;

	public OpenFileEventListener(Controller controller) {
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
