package arcade.store.gui.tabs;

import javax.swing.JComponent;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.control.MainController;
import arcade.store.control.StoreLibraryController;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StoreLibraryTab extends JPanel implements Tab, IViewer {

	// private JPanel cataloguePanel = null; //
	// @jve:decl-index=0:visual-constraint="220,-13"
	private JLabel rentaltradeLabel = null;
	private JLabel typeLabel = null;
	private JPanel typePanel = null;
	private JList typeList = null;
	private JScrollPane tradeItemsScrollPane = null;
	private JTable itemsTable = null;
	private JLabel searchLabel = null;
	private JComboBox searchComboBox = null;
	private JLabel filterLabel = null;
	private JComboBox filterComboBox = null;
	private JTextField searchTextField = null;
	private JButton searchButton = null;

	private static final String NAME = "Store Library";
	private IController controller;

	public StoreLibraryTab() {
		controller = new StoreLibraryController();
		controller.addViewer(this);
		setName(NAME);

		filterLabel = new JLabel();
		filterLabel.setBounds(new Rectangle(450, 86, 63, 25));
		filterLabel.setText("Filter by:");
		searchLabel = new JLabel();
		searchLabel.setBounds(new Rectangle(218, 85, 71, 22));
		searchLabel.setText("Search by:");
		typeLabel = new JLabel();
		typeLabel.setBounds(new Rectangle(42, 83, 49, 24));
		typeLabel.setText("Type:");
		rentaltradeLabel = new JLabel();
		rentaltradeLabel.setText("The Rental / Trade Center");
		rentaltradeLabel.setBounds(new Rectangle(21, 22, 152, 36));

		setLayout(null);
		add(rentaltradeLabel, null);
		add(typeLabel, null);
		add(getTypePanel(), null);
		add(getTradeItemsScrollPane(), null);
		add(searchLabel, null);
		add(getSearchComboBox(), null);
		add(filterLabel, null);
		add(getFilterComboBox(), null);
		add(getSearchTextField(), null);
		add(getSearchButton(), null);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setController(IController control) {
		controller = control;

	}

	@Override
	public IController getController() {

		return controller;
	}

	/**
	 * This method initializes cataloguePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	@Override
	public JComponent getContent() {
		return this;
	}

	/**
	 * This method initializes typePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTypePanel() {
		if (typePanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			typePanel = new JPanel();
			typePanel.setLayout(new GridBagLayout());
			typePanel.setBounds(new Rectangle(42, 121, 133, 282));
			typePanel.add(getTypeList(), gridBagConstraints);
		}
		return typePanel;
	}

	/**
	 * This method initializes typeList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getTypeList() {
		if (typeList == null) {
			typeList = new JList();
		}
		return typeList;
	}

	/**
	 * This method initializes tradeItemsScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getTradeItemsScrollPane() {
		if (tradeItemsScrollPane == null) {
			tradeItemsScrollPane = new JScrollPane();
			tradeItemsScrollPane.setBounds(new Rectangle(214, 123, 453, 419));
			tradeItemsScrollPane.setViewportView(getItemsTable());
		}
		return tradeItemsScrollPane;
	}

	/**
	 * This method initializes itemsTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getItemsTable() {
		if (itemsTable == null) {
			itemsTable = new JTable();
		}
		return itemsTable;
	}

	/**
	 * This method initializes searchComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSearchComboBox() {
		if (searchComboBox == null) {
			searchComboBox = new JComboBox();
			searchComboBox.setBounds(new Rectangle(306, 85, 74, 21));
		}
		return searchComboBox;
	}

	/**
	 * This method initializes filterComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getFilterComboBox() {
		if (filterComboBox == null) {
			filterComboBox = new JComboBox();
			filterComboBox.setBounds(new Rectangle(528, 87, 68, 22));
		}
		return filterComboBox;
	}

	/**
	 * This method initializes searchTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getSearchTextField() {
		if (searchTextField == null) {
			searchTextField = new JTextField();
			searchTextField.setBounds(new Rectangle(487, 24, 91, 22));
		}
		return searchTextField;
	}

	/**
	 * This method initializes searchButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setBounds(new Rectangle(587, 21, 77, 27));
			searchButton.setText("Search");
		}
		return searchButton;
	}

	@Override
	public void refresh() {
		this.repaint();
	}

}
