package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.medialib.mlib.Image;



public class ImageListFrame extends AbstractListFrame {

	String myName;
	String myFilepath;
	ImageIcon myIcon;
	JLabel iconLabel;
	JFileChooser myFileChooser;
	
	private final int DEFAULT_HEIGHT = 300;
	
	public ImageListFrame( String name, String filepath, int width) {
		
		super();
		
		myName = name;
		myFilepath = filepath;
		myFileChooser = new JFileChooser();
				
		this.setLayout(new BorderLayout());
		
		restrictSize(width, DEFAULT_HEIGHT);

		makeComponents();
				
	}


	@Override
	public void makeComponents() {

		iconLabel = new JLabel();
		iconLabel.setText(myName);

		changeIcon();
		
		JButton fileButton = new JButton("Select A File");
		
		fileButton.addActionListener(new ActionListener() {
			
			public void actionPerformed( ActionEvent e ){
				
				myFileChooser.showDialog(ImageListFrame.this, "Select");
				File file = myFileChooser.getSelectedFile();
				
				myFilepath = file.getPath();
				
				ImageListFrame.this.changeIcon();
				
			}
			
		});
		
		add(iconLabel, BorderLayout.CENTER);
		add(fileButton, BorderLayout.SOUTH);

	}

	public void changeIcon() {
		
		
		try {
			System.out.println(myFilepath);

			myIcon = new ImageIcon(ImageIO.read(new File(myFilepath)));
		
			iconLabel.setIcon(myIcon);
			
			this.validate();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}



}
