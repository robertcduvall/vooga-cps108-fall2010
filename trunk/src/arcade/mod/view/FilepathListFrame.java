package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FilepathListFrame extends AbstractListFrame {

	private String myName;
	private String myFilepath;
	private JFileChooser myFileChooser;
	private JLabel filepathLabel;
	
	private static final int DEFAULT_HEIGHT = 100;
	
	public FilepathListFrame( String name, String filepath, int width) {
		
		super();
		
		myName = name;
		myFilepath = filepath;
		myFileChooser = new JFileChooser();
				
		restrictSize(width, DEFAULT_HEIGHT);

		makeComponents();
				
	}
	
	@Override
	public void makeComponents() {

		JLabel label = new JLabel();
		filepathLabel = new JLabel();
		
		label.setText(myName);
		filepathLabel.setText(myFilepath);
		
		JButton fileButton = new JButton("Select A File");
		
		fileButton.addActionListener(new ActionListener() {
			
			public void actionPerformed( ActionEvent e ){
				
				myFileChooser.showDialog(FilepathListFrame.this, "Select");
				File file = myFileChooser.getSelectedFile();
				
				myFilepath = file.getPath();
				
				FilepathListFrame.this.filepathLabel.setText(myFilepath);
								
			}
			
		});
		
		add(label);
		add(fileButton);
		add(filepathLabel);
		
	}

}
