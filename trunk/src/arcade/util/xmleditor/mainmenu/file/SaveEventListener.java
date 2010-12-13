package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import arcade.util.xmleditor.Controller;

public class SaveEventListener implements ActionListener{

	
	Controller controller;

	public SaveEventListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.save();
	}
}
