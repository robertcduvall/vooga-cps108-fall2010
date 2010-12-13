package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import arcade.util.xmleditor.Controller;

public class SaveAsEventListener implements ActionListener{

	
	Controller controller;

	public SaveAsEventListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser saveChooser = new JFileChooser();
		JFrame saveFrame = new JFrame();
		saveChooser.showSaveDialog(saveFrame);

		File file =  saveChooser.getSelectedFile();
		controller.save(file);
	}
}
