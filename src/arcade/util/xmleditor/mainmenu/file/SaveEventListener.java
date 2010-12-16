package arcade.util.xmleditor.mainmenu.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arcade.util.xmleditor.controllers.IBaseController;

public class SaveEventListener implements ActionListener{

	
	private IBaseController controller;

	public SaveEventListener(IBaseController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.save();
	}
}
