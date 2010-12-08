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
	protected JButton myFileButton;

	public FilepathListFrame() {
		// do nothing
	}

	public FilepathListFrame(IResourceNode node) {

		super(node);

		restrictSize(HEIGHT);
		initializeComponents();

	}

	public FilepathListFrame newInstance(IResourceNode node) {
		return new FilepathListFrame(node);
	}

	@Override
	public void makeComponents() {

		descriptionLabel.setText(myDescription);

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
		add(descriptionLabel);
		add(myFileButton);
		add(stringLabel);

	}

	public void handleFileChange() {
		FilepathListFrame.this.stringLabel.setText(myFilepath);
	}

	@Override
	public void handleNode(IResourceNode node) {

	//	System.out.println(node.getAttribute("name"));

		myName = node.getAttribute("name");
		myFilepath = node.getAttribute("path");
		myDescription = node.getDescription();
	}

<<<<<<< .mine
	@Override
	public boolean confirmValidity(File saveFile) {
		//TODO: do actual error checking here
		return true;
	}

=======
	@Override
	public void initializeComponents() {
		myFileChooser = new JFileChooser();
		stringLabel = new JLabel();
		descriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		myFileButton = new JButton("Select A File");

		makeComponents();
	}

>>>>>>> .r2644
}
