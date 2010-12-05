package arcade.mod.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import com.sun.medialib.mlib.Image;



public class ImageListFrame extends AbstractListFrame {

	String myName;
	String myFilepath;
	ImageIcon myIcon;
	JLabel iconLabel;

	JFileChooser myFileChooser;
	
	private final int DEFAULT_HEIGHT = 200;
	
	public ImageListFrame( String name, String filepath, int width) {
		
		super();
		
		myName = name;
		myFilepath = filepath;
		myFileChooser = new JFileChooser();
				
		this.setMinimumSize(new Dimension(width,DEFAULT_HEIGHT));
		this.setMaximumSize(new Dimension(width,DEFAULT_HEIGHT));
		this.setPreferredSize(new Dimension(width,DEFAULT_HEIGHT));

		
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
		
		myIcon = createImageIcon(myFilepath, "lol");
		
		try {
			iconLabel.setIcon(myIcon);
		} catch (Throwable e) {
			//do nothing because its a null pointer
		}
		
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}


}
