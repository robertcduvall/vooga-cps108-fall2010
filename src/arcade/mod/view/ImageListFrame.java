package arcade.mod.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;



public class ImageListFrame extends AbstractListFrame {

	String myName;
	String myFilepath;
	ImageIcon myIcon;
	JLabel iconLabel;

	JFileChooser myFileChooser;
	
	public ImageListFrame( String name, String filepath, int height, int width) {
		
		super();
		
		myName = name;
		myFilepath = filepath;
		myFileChooser = new JFileChooser();
				
		this.setMinimumSize(new Dimension(height,width));
		this.setMaximumSize(new Dimension(height,width));
		this.setPreferredSize(new Dimension(height,width));

		
		makeComponents();
				
	}


	@Override
	public void makeComponents() {

		JLabel iconLabel = new JLabel();
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
		
		add(fileButton);
		add(iconLabel);
		
	}

	public void changeIcon() {
		
		
		myIcon = new ImageIcon(myFilepath);
		
		try {
			iconLabel.setIcon(myIcon);
		} catch (Throwable e) {
			System.out.println(myIcon);	
		}
	
	}

}
