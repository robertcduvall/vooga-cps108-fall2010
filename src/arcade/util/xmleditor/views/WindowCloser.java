package arcade.util.xmleditor.views;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Designed to close the JFrame window without 
 * ending the program to prevent the whole Arcade 
 * from closing when this window is closed.
 * 
 * @author Daniel Koverman
 *
 */
public class WindowCloser extends WindowAdapter{
	
	public void windowClosing(WindowEvent e) {
	    Window win = e.getWindow();
	    win.setVisible(false);
	    win.dispose();
	    }

}
