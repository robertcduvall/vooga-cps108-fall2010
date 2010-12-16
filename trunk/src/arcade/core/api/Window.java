package arcade.core.api;

import java.awt.*;
import java.awt.Event.*;

import javax.swing.*;

/**
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
@SuppressWarnings("serial")
public abstract class Window extends JFrame{
	private static final int defaultXSize = 640;
	private static final int defaultYSize = 480;

	public Window() {
		this(defaultXSize, defaultYSize);
	}

	public Window(int xSize, int ySize) {
		createContents();
		setSize(xSize, ySize);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	protected abstract void createContents();
}
