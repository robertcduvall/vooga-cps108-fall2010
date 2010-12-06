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
	
	private final int DEFAULT_HEIGHT = 300;

	public ImageListFrame() {
		// do nothing
	}

	public ImageListFrame(ResourceNode node) {

		super(node);

		restrictSize(DEFAULT_HEIGHT);

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

			stringLabel.setIcon(myIcon);

			this.validate();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
