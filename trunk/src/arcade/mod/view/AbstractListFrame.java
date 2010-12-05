package arcade.mod.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import arcade.mod.model.ResourceNode;

public abstract class AbstractListFrame extends JPanel {

	protected final int WIDTH = 750;
	
	public AbstractListFrame() {
		//do nothing
	}
		
	public AbstractListFrame(ResourceNode node) {
				
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		handleNode(node);
		
	}
	
	protected void restrictSize(int height){
		this.setMinimumSize(new Dimension(WIDTH,height));
		this.setMaximumSize(new Dimension(WIDTH,height));
		this.setPreferredSize(new Dimension(WIDTH,height));
	}
	
	public abstract void newInstance(ResourceNode node);
	
	public abstract void handleNode(ResourceNode node);
	
	public abstract void makeComponents();
	
}
