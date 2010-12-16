package arcade.util.xmleditor.views;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloser extends WindowAdapter{
	
	public void windowClosing(WindowEvent e) {
	    Window win = e.getWindow();
	    win.setVisible(false);
	    win.dispose();
	    }

}
