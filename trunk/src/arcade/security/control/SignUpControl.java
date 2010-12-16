package arcade.security.control;

import javax.swing.JFrame;
import arcade.security.view.AdminPanel;


public class SignUpControl implements IControl{
	
	public void PopUpAdminPage(){
		JFrame adminFrame = new JFrame();
		adminFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		AdminPanel adminPanel = new AdminPanel();	
		adminFrame.add(adminPanel);
		adminFrame.pack();
		adminFrame.setVisible(true);
	}

}
