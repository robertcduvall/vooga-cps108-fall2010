package arcade.mod.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public abstract class AbstractListFrame extends JPanel {

	protected final int WIDTH = 750;
	protected final int HEIGHT = 200;
		
	public AbstractListFrame() {
				
		this.setSize(WIDTH, HEIGHT);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	protected void restrictSize(int width, int height){
		this.setMinimumSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(width,height));
		this.setPreferredSize(new Dimension(width,height));
	}
	
	public abstract void makeComponents();
	
}
