package arcade.mod.view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.mod.model.IResourceNode;

/**
 * Abstract List Frames are the graphical display of the Resource Nodes derived
 * from the model. These List Frames are reduced to the core graphical data
 * needed for the GUI so as to avoid coupling between the Model and View
 * 
 */
public abstract class AbstractListFrame extends JPanel {

	protected String myName;
	protected String myDescription;

	protected JLabel myNameLabel;
	protected JLabel newValue;
	protected JLabel currentValue;
	protected JLabel stringLabel;
	protected JLabel descriptionLabel;

	protected static final int HEIGHT = 70;
	protected final int WIDTH = 750;
	protected IResourceNode myNode;

	/**
	 * Null constructor for the List Frame
	 */
	public AbstractListFrame() {

	}

	/**
	 * Creates a new instance of ListFrame from a resource node
	 * 
	 * @param node
	 *            representing data from the XML file
	 */
	public AbstractListFrame(IResourceNode node) {

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		myNode = node;
		

		handleNode(node);

	}

	/**
	 * Ensure that the list frame does not fall outside of specified boundary
	 * constraints
	 * 
	 * @param height
	 *            to bound the the frame within
	 */
	protected void restrictSize(int height) {
		this.setMinimumSize(new Dimension(WIDTH, height));
		this.setMaximumSize(new Dimension(WIDTH, height));
		this.setPreferredSize(new Dimension(WIDTH, height));
	}

	/**
	 * Create a new instance of a specific type of list frame (to avoid
	 * reflection in the Frame Factory)
	 * 
	 * @param node
	 *            from which to create a new instance of the list frame
	 * @return a new instance of a subclass of AbstractListFrame
	 */
	public abstract AbstractListFrame newInstance(IResourceNode node);

	/**
	 * Logic of the list frame which is specific to each subclass
	 * 
	 * @param node
	 */
	public abstract void handleNode(IResourceNode node);

	/**
	 * Create the subcomponents, whose logic is differant for each type of list
	 * frame
	 */
	public abstract void makeComponents();

	/**
	 * Error checking to confirm that a list frame is holding a valid file type
	 * @return boolean true if a file is valid
	 */
	public abstract boolean confirmValidity(File saveFile);
	
	public abstract void initializeComponents();

}
