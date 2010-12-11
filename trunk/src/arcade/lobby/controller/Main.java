package arcade.lobby.controller;

import javax.swing.JFrame;

import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.LoginPanel;
import arcade.lobby.view.Menu;

public class Main {
	public static ProfileSet ProfileSet = new ProfileSet("voogaarcade.db.7093929.hostedresource.com" , "voogaarcade", "Profile", "voogaarcade", "Vooga108");
	public static JFrame MainFrame;
	
	  public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
	  
	  /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event-dispatching thread.
	     */
	    public static void createAndShowGUI() {
	        //Create and set up the window.
	    	
	    	MainFrame = new JFrame("Main Window");
	        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        MainFrame.setSize(400, 300);
	        
	        MainFrame.setJMenuBar(new Menu());
	        
	        MainFrame.setContentPane(new LoginPanel());
	        
	        MainFrame.setVisible(true);
	        
//			MySqlAdapter myDbAdapter = new MySqlAdapter("voogaarcade.db.7093929.hostedresource.com" , "voogaarcade", "voogaarcade", "Vooga108");
//			System.out.println(myDbAdapter.getRow("Users", "test1"));
			
	    }
}
