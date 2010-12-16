package arcade.mod.view.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import arcade.mod.model.IResourceNode;

public class FilepathListFrame extends ListFrame {

	protected String myFilepath;
	protected JFileChooser myFileChooser;
	protected JButton myFileButton;

	public FilepathListFrame() {
		// do nothing
	}

	public FilepathListFrame(IResourceNode node) {

		super(node);

		restrictSize(HEIGHT);
		
	}

	public FilepathListFrame newInstance(IResourceNode node) {
		return new FilepathListFrame(node);
	}

	@Override
	public void makeComponents() {

		myDescriptionLabel.setText(myDescription);

		myNameLabel.setText(myName);

		handleFileChange();

		myFileButton.addActionListener(new ActionListener() {

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
				
		add(myNameLabel);
		add(myDescriptionLabel);
		add(myFileButton);
		add(myStringLabel);

	}

	public void handleFileChange() {
		FilepathListFrame.this.myStringLabel.setText(myFilepath);
	}

	@Override
	public void handleNode(IResourceNode node) {

		myName = node.getAttribute("File name: ");
		myFilepath = node.getAttribute("File path: ");
		myDescription = node.getDescription();
	}




	@Override
	public void initializeComponents() {
		myFileChooser = new JFileChooser();
		myStringLabel = new JLabel();
		myDescriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		myFileButton = new JButton("Select A File");
	}

	/**
	 * Error checking to confirm that a list frame is holding valid data.
	 * This will be overridden in subclasses that have more specific error-checking
	 * @return boolean true if a file is valid
	 */
	@Override
	public boolean confirmValidity() {
		return true;
	}

}
