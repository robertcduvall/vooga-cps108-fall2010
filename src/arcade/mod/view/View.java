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

public class View extends JFrame implements Viewer {

	private static final long serialVersionUID = 1L;

	Presenter myPresenter;

	JPanel centralPanel;
	JScrollPane centralScrollPane;
	Collection<String> myCategories;
	String currentCategory;

	public View(Presenter controller) {
		myPresenter = controller;
	}

	public void initialize() {

		setSize(800, 600);

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

	public void changeFrames(Collection<AbstractListFrame> frames) {

		centralPanel.removeAll();

		for (AbstractListFrame alf : frames) {
			centralPanel.add(alf);
		}

		centralPanel.validate();
	}

	public String getCurrentCategory() {
		return currentCategory;
	}

	private JButton makeButton() {
		JButton saveButton = new JButton("Save All");

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				myPresenter.save();
			}

		});

		return saveButton;
	}

	public File saveFileSelect() {
		JFileChooser saveChooser = new JFileChooser();

		saveChooser.showSaveDialog(this);

		return saveChooser.getSelectedFile();

	}

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

	public File openFileSelect() {

		JFileChooser loadChooser = new JFileChooser();

		loadChooser.showOpenDialog(this);

		return loadChooser.getSelectedFile();

	}

}