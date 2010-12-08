package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLNode;

public class ImageListFrame extends FilepathListFrame {

	ImageIcon myIcon;
	

	public ImageListFrame() {
		// do nothing
	}

	public ImageListFrame(IResourceNode node) {

		super(node);
		
	}
	

	public ImageListFrame newInstance(IResourceNode node) {
		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
		return new ImageListFrame(node);
		else return new ImageListFrame();
	}

	@Override
	public void handleFileChange() {
		
		try {
			stringLabel.setText(myFilepath);
			
			myIcon = new ImageIcon(ImageIO.read(new File(myFilepath)));

			restrictSize(myIcon.getIconHeight() + 100);
			
			stringLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			stringLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			
			stringLabel.setIcon(myIcon);

			this.validate();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void handleNode(IResourceNode node) {
		myName = node.getAttribute("name");
		myFilepath = node.getModelPath() + "\\images\\" + node.getAttribute("path");
		myDescription = node.getDescription();
	}

}
