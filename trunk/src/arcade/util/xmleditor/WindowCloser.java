package arcade.util.xmleditor;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloser extends WindowAdapter{
	
	public void windowClosing(WindowEvent e) {
	    Window win = e.getWindow();
	    win.setVisible(false);
	    System.exit(0);
	    }

}
