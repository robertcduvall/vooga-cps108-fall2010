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

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static Collection<SecurityFrameData> apps=new LinkedList<SecurityFrameData>();
	private PrivilegeDesktop desktop; 
	
	public MainFrame() throws UserConfigurationNotFoundException{
		super(LabelResources.getLabel("DesktopTitle"));
		this.setIconImage(new ImageIcon(StaticFileResources.getPath("mainframeicon")).getImage());
		apps=bootstrapAppData();		
		setSize(1000,600);
		setLocationRelativeTo(this.getParent());
		this.getRootPane().setBackground(Color.blue);
		SecurityToolBar toolbar=new SecurityToolBar();
		add(toolbar);	
		desktop=new PrivilegeDesktop(apps,this);
		add(desktop);
		createMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);


	}



	public void createMenu(){
		JMenuBar menuBar = new JMenuBar();
		// Create a menu
		JMenu menu = new JMenu(LabelResources.getLabel("DesktopMenu1"));
		menuBar.add(menu);
		JMenu menu2=new JMenu(LabelResources.getLabel("DesktopMenu2"));
		menuBar.add(menu2);
		// Create a menu item
		JMenuItem item = new JMenuItem(LabelResources.getLabel("DesktopMenu1Item1"));
		menu.add(item);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}		
		});

		for(SecurityFrameData app:apps){
			JMenuItem teamItem = new JMenuItem(app.getName(),new ImageIcon(System.getProperty("icon")+"/"+app.getName()+"Icon"+".jpg"));
			teamItem.addActionListener(new LaunchApplications(app));
			menu2.add(teamItem);
		}			
		// Install the menu bar in the frame
		this.setJMenuBar(menuBar);  //don't use add(menuBar);
	}


	class LaunchApplications implements ActionListener{
		private SecurityFrameData app;

		public LaunchApplications(SecurityFrameData app){
			this.app=app;
		}	
		public void actionPerformed(ActionEvent e){			 
			
					Object internalFrame;
					UserConfigurationFrame appframe;
					try {
						internalFrame = Class.forName(app.getClazz()).newInstance();
						appframe=(UserConfigurationFrame)internalFrame;
						desktop.add(appframe);
					} catch (InstantiationException e1) {
	
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
					
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
					
						e1.printStackTrace();
					}
		  		    	 	
		}
	}


	private Collection<SecurityFrameData> bootstrapAppData()
	throws UserConfigurationNotFoundException {
		return SecurityFrameProperties.loadProperties(StaticFileResources.getPath("securityframeconfigfile"));
	}
}
