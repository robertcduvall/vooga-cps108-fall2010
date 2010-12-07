package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import arcade.mod.model.IResourceNode;

public class FilepathListFrame extends AbstractListFrame {

	protected String myFilepath;
	protected JFileChooser myFileChooser;
	
	public FilepathListFrame() {
		// do nothing
	}

	public FilepathListFrame(IResourceNode node) {

		super(node);

		myFileChooser = new JFileChooser();

		restrictSize(HEIGHT);

		makeComponents();

	}

	public FilepathListFrame newInstance(IResourceNode node) {
		return new FilepathListFrame(node);
	}

	@Override
	public void makeComponents() {

		descriptionLabel = new JLabel();
		descriptionLabel.setText(myDescription);
		
		JLabel label = new JLabel();
		stringLabel = new JLabel();
		
		label.setText(myName);

		handleFileChange();
		
		JButton fileButton = new JButton("Select A File");

		fileButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				myFileChooser.showDialog(FilepathListFrame.this, "Select");
				File file = myFileChooser.getSelectedFile();
				if (file != null) {
					myFilepath = file.getPath();
					myNode.setAttribute("path", myFilepath);
				}

				handleFileChange();

			}

		});

		add(label);
		add(descriptionLabel);
		add(fileButton);
		add(stringLabel);

	}
	
	public void handleFileChange() {
		FilepathListFrame.this.stringLabel.setText(myFilepath);
	}

	@Override
	public void handleNode(IResourceNode node) {

		System.out.println(node.getAttribute("name"));
		
		myName = node.getAttribute("name");
		myFilepath = node.getAttribute("path");
		myDescription = node.getDescription();
	}

}
