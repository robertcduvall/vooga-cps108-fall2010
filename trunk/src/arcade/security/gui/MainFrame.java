package arcade.security.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import arcade.security.etc.SecurityFrameData;
import arcade.security.etc.SecurityFrameProperties;
import arcade.security.exceptions.UserConfigurationNotFoundException;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;

/**
 * MainFrame will be launched after successfully login and contain SecurityDesktop.
 * @author Meng Li
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static Collection<SecurityFrameData> apps=new LinkedList<SecurityFrameData>();
	private SecurityDesktop desktop; 
	
	public MainFrame() throws UserConfigurationNotFoundException{
		super(LabelResources.getLabel("DesktopTitle"));
		this.setIconImage(new ImageIcon(StaticFileResources.getPath("mainframeicon")).getImage());
		apps=bootstrapAppData();		
		setSize(1000,600);
		setLocationRelativeTo(this.getParent());
		this.getRootPane().setBackground(Color.blue);
		SecurityToolBar toolbar=new SecurityToolBar();
		add(toolbar);	
		desktop=new SecurityDesktop(apps,this);
		add(desktop);
		createMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		System.out.println("Current User role: "+user.getRole());
		
		setVisible(true);


	}



	public void createMenu(){
		JMenuBar menuBar = new JMenuBar();
		// Create a menu
		JMenu menu = new JMenu(LabelResources.getLabel("DesktopMenu1"));
		menuBar.add(menu);

		// Create a menu item
		JMenuItem item = new JMenuItem(LabelResources.getLabel("DesktopMenu1Item1"));
		menu.add(item);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}		
		});		
		// Install the menu bar in the frame
		this.setJMenuBar(menuBar);  //don't use add(menuBar);
	}


	private Collection<SecurityFrameData> bootstrapAppData()
	throws UserConfigurationNotFoundException {
		return SecurityFrameProperties.loadProperties(StaticFileResources.getPath("securityframeconfigfile"));
	}
}
