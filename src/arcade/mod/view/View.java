package arcade.mod.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import arcade.mod.controller.Controller;
import arcade.mod.controller.Presenter;

/**
 * As the name suggests, this class represents the View section necessary for the
 * Model-View-Presenter paradigm.  In keeping with the spirit of this model, we
 * made an effort to keep this class as "dumb" as possible.  That is, we tried to
 * limit accesors to data which exists outside of a graphical framework, keeping
 * the View as passive as possible.
 *
 */
public class View extends JFrame implements Viewer {

	private static final int VIEW_HEIGHT = 600;

	private static final int VIEW_WIDTH = 800;

	private static final long serialVersionUID = 1L;

	Presenter myPresenter;

	JPanel centralPanel;
	JScrollPane centralScrollPane;
	Collection<String> myCategories;
	String currentCategory;

	/**
	 * Creates a new instance of View with a reference to the Presenter which instantiated it
	 * @param presenter which instantiated the View
	 */
	public View(Presenter controller) {
		myPresenter = controller;
	}

	/**
	 * Initialize the View with its initial display
	 */
	public void initialize() {

		setSize(VIEW_WIDTH, VIEW_HEIGHT);

		JPanel contentPane = new JPanel(new BorderLayout());

		contentPane.add(makeCategoryBox(), BorderLayout.NORTH);
		contentPane.add(makeButton(), BorderLayout.SOUTH);

		centralPanel = new JPanel();

		centralPanel
				.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));

		centralScrollPane = new JScrollPane(centralPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(centralScrollPane, BorderLayout.CENTER);

		add(contentPane);

		setVisible(true);

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

		Collection<String> myCategories = myPresenter.getCategories();

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

	/**
	 * A JFileChooser for opening a selected file
	 * @return File to save
	 */
	public File openFileSelect() {

		JFileChooser loadChooser = new JFileChooser();

		loadChooser.showOpenDialog(this);

		return loadChooser.getSelectedFile();

	}

}