package arcade.security.view;

import javax.swing.*;

import org.apache.log4j.Logger;

import arcade.security.control.AdminPanelControl;
import arcade.security.control.IControl;

import arcade.security.exceptions.UserConfigurationNotFoundException;
import arcade.security.gui.SecurityButton;
import arcade.security.gui.AdminBox;
import arcade.security.gui.GoToPrivilegesButton;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.util.AdminHandler;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Map;

/**
 * View object for the Admin panel. Used in conjunction with
 * security.model.AdminProcess and security.control.AdminPanelControl.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class AdminPanel extends JPanel implements IView{
	private final static Logger log=Logger.getLogger(AdminPanel.class);
	private static final long serialVersionUID = 1L;
	private static final int SECURITY_PANEL_WIDTH = 1200;
	private static final int SECURITY_PANEL_HEIGHT = 600;
	private static final String DELIM = ";";
	private JLabel adminUserName; 
	private JScrollPane adminScrollPane;
	private JPanel columnName;
	private Map<String,String> originalMap;
	private Map<String,String> userTypeMap;
	private AdminHandler model;
	private String[] userTypes;
	private JButton submitButton;
	private JButton undoButton;
	
	
	private JPanel userRolePane;
	private JButton LogoutButton;
	private static String listOfPanels;
	private ResourceBundle resources = ResourceBundle
	.getBundle("arcade.security.resources.securitypanels.panels");

	/**
	 * Constructor for the Administrator Panel. Currently under construction.
	 */
	public AdminPanel(){
		setName("Admin page"); 
		model = new AdminHandler();
		//setLayout(new MigLayout("wrap 2"));
		setLayout(new MigLayout("wrap 1"));
		adminUserName = new JLabel("Administrator: "+UserServiceFactory.getCurrentUser().getFullName());
		add(adminUserName);
		//LogoutButton = new SecurityButton(LabelResources.getLabel("Logout"));
		//add(LogoutButton,"gapleft 585");
		createColumnName();
		createScrollList();
		submitButton = model.createSubmitButton();
		add(submitButton);
		undoButton = model.createUndoButton();
		add(undoButton);
		//add(columnName);
		//adminScrollPane = new JScrollPane();
		//adminScrollPane.setViewportView(getUserRolePanel());		
		//addUserSecurityPanel();
		//add(adminScrollPane,"span 2");
		setVisible(true);
	}
	
	
	
	
	private void createColumnName(){
		JPanel panel= new JPanel();
		panel.setSize(SECURITY_PANEL_WIDTH,30);
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(4);
		panel.setLayout(gridLayout);
		panel.add(new JLabel("UserName"));
		panel.add(new JLabel("Current User Type"));
		panel.add(new JLabel("Set User Type"));
		panel.add(new JLabel("Go to detailed User Privilege"));
		add(panel);
	}
	
	private void createScrollList(){
		userTypeMap = model.getUserTypeMap();
		userTypes = model.getUserTypes();
		originalMap = userTypeMap;
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(0,4);
		panel.setLayout(gridLayout);
		for(String name:userTypeMap.keySet()){
			panel.add(new JLabel(name));
			panel.add(new JLabel(userTypeMap.get(name)));
			panel.add(model.createNewAdminBox(name));
			panel.add(new GoToPrivilegesButton(name));
		}
		adminScrollPane = new JScrollPane(panel);
		adminScrollPane.setPreferredSize(new Dimension(SECURITY_PANEL_WIDTH,SECURITY_PANEL_HEIGHT));
		add(adminScrollPane);
	}
	
	
	/**
	 * Returns the current user role panel
	 * 
	 */
	public JPanel getUserRolePanel() {
		if (userRolePane == null) {
			userRolePane = new JPanel(new GridLayout(0, 1));
			userRolePane.setPreferredSize(new Dimension(SECURITY_PANEL_WIDTH,SECURITY_PANEL_HEIGHT));

		}
		return userRolePane;

	}
	/**
	 * Adds a user security panel to the list of panels
	 */
	public void addUserSecurityPanel(){  
		listOfPanels=resources.getString("listofpanels");		
		loadPanels(listOfPanels);

	}
	/**
	 * Adds a listener for the logout button
	 * 
	 * @param listener
	 *            the listener to be added
	 */
	public void addLogoutButtonListener(ActionListener listener){
		LogoutButton.addActionListener(listener);
	}

	/**
	 * loads all the panels in the list of panels
	 * 
	 * @param listOfPanels
	 *            the panels to be loadaed, in the form of a String
	 */
	private void loadPanels(String listOfPanels) {
		String[] panel = listOfPanels.split(DELIM);
		for(String p: panel){
			JPanel temp=createSecurityPane(p);
			getUserRolePanel().add(temp);					
		}				
	}
	/**
	 * Creates and returns a security panel
	 * 
	 */
	private JPanel createSecurityPane(String classPath)
	{			
		try{
			Object securityPanel=Class.forName(classPath).newInstance();  		    	 	
			return (JPanel)securityPanel;      
		}
		catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		catch(InstantiationException e2){
			e2.printStackTrace();
		}
		catch(IllegalAccessException e3){
			e3.printStackTrace();
		}
		return null;					    
	}

}
