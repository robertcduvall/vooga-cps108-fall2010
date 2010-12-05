package arcade.mod.view;

import java.awt.Color;

import javax.swing.*;

public abstract class AbstractListFrame extends JPanel {

	protected final int WIDTH = 750;
	protected final int HEIGHT = 200;
		
	public AbstractListFrame() {
				
		this.setSize(WIDTH, HEIGHT);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		
	}
	
	public abstract void makeComponents();
	
}
