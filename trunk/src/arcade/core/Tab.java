package arcade.core;

import java.awt.*;
import java.awt.Event.*;

import javax.swing.*;

/**
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
@SuppressWarnings("serial")
public abstract class Tab extends JPanel {
	
	public abstract JComponent getContent();
}