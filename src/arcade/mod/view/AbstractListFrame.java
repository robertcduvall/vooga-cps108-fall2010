package arcade.mod.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import arcade.mod.model.ResourceNode;
import arcade.mod.model.XMLNode;

public abstract class AbstractListFrame extends JPanel {
	protected static int HEIGHT = 70;
	protected final int WIDTH = 750;
	protected ResourceNode myNode;

	public AbstractListFrame() {
		HEIGHT = 0;

	}

	public AbstractListFrame(ResourceNode node) {

		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		{
			HEIGHT = 70;
			myNode = node;

			handleNode(node);
		}

	}

	protected void restrictSize(int height) {
		this.setMinimumSize(new Dimension(WIDTH, height));
		this.setMaximumSize(new Dimension(WIDTH, height));
		this.setPreferredSize(new Dimension(WIDTH, height));
	}

	public abstract AbstractListFrame newInstance(ResourceNode node);

	public abstract void handleNode(ResourceNode node);

	public abstract void makeComponents();

}
