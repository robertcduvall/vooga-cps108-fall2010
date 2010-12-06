package arcade.lobby.view;

import javax.swing.JFrame;

import arcade.lobby.model.MySqlAdapter;
import arcade.lobby.model.ProfileSet;

public class Main {
	public static ProfileSet ProfileSet = new ProfileSet("voogaarcade.db.7093929.hostedresource.com" , "voogaarcade", "Users", "voogaarcade", "Vooga108");

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
	    	
	    	JFrame frame = new Login();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        Menu userMenu = new Menu(frame);
	        frame.setJMenuBar(userMenu.setUpMenu());
	        
	        frame.setVisible(true);
	        frame.getJMenuBar().setVisible(false);
	        
			MySqlAdapter myDbAdapter = new MySqlAdapter("voogaarcade.db.7093929.hostedresource.com" , "voogaarcade", "voogaarcade", "Vooga108");
			System.out.println(myDbAdapter.getRow("Users", "test1"));
			
	    }
}