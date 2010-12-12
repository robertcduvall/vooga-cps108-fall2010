package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import arcade.core.ExampleGUI;
import arcade.mod.controller.IPresenter;
import arcade.mod.view.file.IFileOpener;
import arcade.mod.view.frame.ListFrame;

/**
 * As the name suggests, this class represents the View section necessary for the
 * Model-View-Presenter paradigm.  In keeping with the spirit of this model, we
 * made an effort to keep this class as "dumb" as possible.  That is, we tried to
 * limit accesors to data which exists outside of a graphical framework, keeping
 * the View as passive as possible.
 *
 */
public class View extends JPanel implements IViewer {

//	private static final int VIEW_HEIGHT = 600;
//
//	private static final int VIEW_WIDTH = 800;

	private static final long serialVersionUID = 1L;

	IPresenter myPresenter;

	JPanel centralPanel;
	Collection<String> myCategories;
	String currentCategory;
	IFileOpener fileOpener;

	/**
	 * Creates a new instance of View with a reference to the Presenter which instantiated it
	 * @param presenter which instantiated the View
	 */
	public View(IPresenter controller) {
		this.setLayout(new BorderLayout());
		myPresenter = controller;
		myCategories = new ArrayList<String>();
		myCategories.add("<empty>");
	}

	/**
	 * Initialize the View with its initial display
	 */
	public void initialize() {

//		setSize(VIEW_WIDTH, VIEW_HEIGHT);
		
		JPanel contentPane = new JPanel(new BorderLayout());
		
		contentPane.add(makeCategoryBox(), BorderLayout.NORTH);
		contentPane.add(makeButton(), BorderLayout.SOUTH);
		contentPane.add(initializeScrollPane(), BorderLayout.CENTER);

		JPanel toolbarPane = new JPanel(new FlowLayout());
		toolbarPane.add(new ViewFileMenu(this, myPresenter));
		
		add(toolbarPane, BorderLayout.NORTH);
		add(contentPane, BorderLayout.CENTER);

		setVisible(true);

	}

	private JScrollPane initializeScrollPane() {
		
		centralPanel = new JPanel();
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
		
		centralPanel.setBackground(new Color(100,100,100));

		JScrollPane scrollPane = new JScrollPane(centralPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

//		scrollPane.setPreferredSize(new Dimension(VIEW_WIDTH, VIEW_HEIGHT));
		
		return scrollPane;
	
	}

	/**
	 * Switches from one list frame to another when the presenter tells it to do so.
	 */
	public void changeFrames(Collection<ListFrame> frames) {

		centralPanel.removeAll();
		centralPanel.updateUI();

		centralPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		for (ListFrame alf : frames) {
			centralPanel.add(alf);
			centralPanel.add(Box.createRigidArea(new Dimension(0,5)));
		}

		centralPanel.validate();
	}

	/**
	 * Accesor method which provides information on the current category in which the View is
	 */
	public String getCurrentCategory() {
		return currentCategory;
	}

	/**
	 * Creates a "Save All" button that tells the presenter to save on click
	 * @return "Save All" JButton
	 */
	private JButton makeButton() {
		JButton saveButton = new JButton("Save All");

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				myPresenter.save();
			}

		});

		return saveButton;
	}

	/**
	 * A JFileChooser for saving a selected file
	 * @return File to save
	 */
	public File saveFileSelect() {
		JFileChooser saveChooser = new JFileChooser();

		saveChooser.showSaveDialog(this);

		return saveChooser.getSelectedFile();

	}

	/**
	 * Creates a category box for the UI
	 * @return JComboBox
	 */
	private JComboBox makeCategoryBox() {
		
		JComboBox categoryBox = new JComboBox(myCategories.toArray());
		categoryBox.setSelectedIndex(0);
		categoryBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				currentCategory = (String) comboBox.getSelectedItem();

				myPresenter.newCategorySelected();
			}

		});

		return categoryBox;

	}
	
	@Override
	public void changeCategories(Collection<String> categories){
		myCategories = categories;
		
		//TODO this is the worst way in the world to 
		//update the current categories drop down
		//but I'm tired and it's late
		this.removeAll();
		initialize();
	}

	/**
	 * A JFileChooser for opening a selected file
	 * @return File to save
	 */
	public File openFileSelect() {

		
		return fileOpener.openFile();
	}
	
	public void setFileOpener(IFileOpener fileOpener){
		this.fileOpener =fileOpener;

	}

}