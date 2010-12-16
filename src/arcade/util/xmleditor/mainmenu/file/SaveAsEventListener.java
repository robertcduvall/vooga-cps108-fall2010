package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import arcade.util.xmleditor.controllers.IBaseController;

public class SaveAsEventListener implements ActionListener{

	
	private IBaseController controller;

	public SaveAsEventListener(IBaseController controller) {
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
