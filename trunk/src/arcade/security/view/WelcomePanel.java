package arcade.security.view;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.security.resourcesbundle.StaticFileResources;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel implements Tab,IView{
	
	
	
	public WelcomePanel(){
		setLayout(new MigLayout("wrap 1"));
		JLabel image=new JLabel(new ImageIcon(StaticFileResources.getPath("loginimage")));
		JLabel welcomeText = new JLabel("Welcome to the Arcade!");
		add(image);
		add(welcomeText);
		setVisible(true);
	}
	
	@Override
	public IController getController() {
		return null;
	}

	@Override
	public void refresh() {
	}
	
}
