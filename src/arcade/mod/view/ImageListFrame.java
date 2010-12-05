package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;



public class ImageListFrame extends AbstractListFrame {

	String myName;
	String myFilepath;
	ImageIcon myIcon;
	JLabel iconLabel;

	JFileChooser myFileChooser;
	
	public ImageListFrame( String name, String filepath ) {
		
		super();
		
		myName = name;
		myFilepath = filepath;
		myFileChooser = new JFileChooser();
		
		makeComponents();
				
	}

	private static final long serialVersionUID = 1L;

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
		
		//iconLabel.setIcon(myIcon);
		
	}

}
