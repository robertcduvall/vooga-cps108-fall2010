package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import arcade.mod.model.ResourceNode;

public class ImageListFrame extends AbstractListFrame {

	String myName;
	String myFilepath;
	String myDescription;
	ImageIcon myIcon;
	JLabel iconLabel;
	JLabel descriptionLabel;
	JFileChooser myFileChooser;

	private final int DEFAULT_HEIGHT = 300;

	public ImageListFrame() {
		// do nothing
	}

	public ImageListFrame(ResourceNode node) {

		super(node);

		myFileChooser = new JFileChooser();

		this.setLayout(new BorderLayout());

		restrictSize(DEFAULT_HEIGHT);

		makeComponents();

	}

	public ImageListFrame newInstance(ResourceNode node) {
		return new ImageListFrame(node);
	}

	@Override
	public void makeComponents() {
		descriptionLabel = new JLabel();
		descriptionLabel.setText(myDescription);
		iconLabel = new JLabel();
		iconLabel.setText(myName);

		changeIcon();

		JButton fileButton = new JButton("Select A File");

		fileButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				myFileChooser.showDialog(ImageListFrame.this, "Select");
				File file = myFileChooser.getSelectedFile();

				if (file != null) {
					myFilepath = file.getPath();
					myNode.setAttribute("path", myFilepath);
				}

				ImageListFrame.this.changeIcon();

			}

		});

		add(iconLabel, BorderLayout.CENTER);
		add(descriptionLabel);
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

	@Override
	public void handleNode(ResourceNode node) {
		myDescription = node.getDescription();
		myName = node.getAttribute("name");
		myFilepath = node.getAttribute("path");

	}

}
