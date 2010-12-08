package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import arcade.mod.controller.IPresenter;
import arcade.mod.view.file.IFileOpener;

/**
 * As the name suggests, this class represents the View section necessary for the
 * Model-View-Presenter paradigm.  In keeping with the spirit of this model, we
 * made an effort to keep this class as "dumb" as possible.  That is, we tried to
 * limit accesors to data which exists outside of a graphical framework, keeping
 * the View as passive as possible.
 *
 */
public class View extends JPanel implements IViewer {

	private static final int VIEW_HEIGHT = 600;

	private static final int VIEW_WIDTH = 800;

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
		myPresenter = controller;
		myCategories = new ArrayList<String>();
		myCategories.add("<empty>");
	}

	/**
	 * Initialize the View with its initial display
	 */
	public void initialize() {

		setSize(VIEW_WIDTH, VIEW_HEIGHT);

		JPanel contentPane = new JPanel(new BorderLayout());
		
		contentPane.add(makeCategoryBox(), BorderLayout.NORTH);
		contentPane.add(makeButton(), BorderLayout.SOUTH);
		contentPane.add(initializeScrollPane(), BorderLayout.CENTER);

		
		add(new ViewFileMenu(this, myPresenter));
		add(contentPane);

		setVisible(true);

	}

	private JScrollPane initializeScrollPane() {
		
		centralPanel = new JPanel();
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
		return new JScrollPane(centralPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

	/**
	 * Switches from one list frame to another when the presenter tells it to do so.
	 */
	public void changeFrames(Collection<AbstractListFrame> frames) {

		centralPanel.removeAll();
		centralPanel.updateUI();

		for (AbstractListFrame alf : frames) {
			centralPanel.add(alf);
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