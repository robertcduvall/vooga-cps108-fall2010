package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import arcade.mod.model.ResourceNode;

public class ImageListFrame extends FilepathListFrame {

	ImageIcon myIcon;
	

	public ImageListFrame() {
		// do nothing
	}

	public ImageListFrame(ResourceNode node) {

		super(node);

	}
	

	public ImageListFrame newInstance(ResourceNode node) {
		return new ImageListFrame(node);
	}

	@Override
	public void handleFileChange() {
		
		try {
			stringLabel.setText(myFilepath);
			
			System.out.println(myFilepath);

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
	public void handleNode(ResourceNode node) {

		System.out.println(node.getAttribute("name"));

		myName = node.getAttribute("name");
		myFilepath = node.getModelPath() + "\\images\\" + node.getAttribute("path");
		myDescription = node.getDescription();
	}

}
