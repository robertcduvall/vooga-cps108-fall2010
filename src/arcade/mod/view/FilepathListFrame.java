package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import arcade.mod.model.ResourceNode;

public class FilepathListFrame extends AbstractListFrame {

	private String myName;
	private String myFilepath;
	private JFileChooser myFileChooser;
	private JLabel filepathLabel;
	
	private static final int DEFAULT_HEIGHT = 70;
	
	public FilepathListFrame() {
		//do nothing
	}
	
	public FilepathListFrame( ResourceNode node ) {
		
		super(node);
		
		myFileChooser = new JFileChooser();
				
		restrictSize(DEFAULT_HEIGHT);

		makeComponents();
				
	}
	
	public FilepathListFrame newInstance(ResourceNode node) {
		return new FilepathListFrame(node);
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
				myNode.setAttribute("path", myFilepath);
				
				FilepathListFrame.this.filepathLabel.setText(myFilepath);
								
			}
			
		});
		
		add(label);
		add(fileButton);
		add(filepathLabel);
		
	}

	@Override
	public void handleNode(ResourceNode node) {		
		myName = node.getAttribute("name");
		myFilepath = node.getAttribute("path");
		
	}

}
