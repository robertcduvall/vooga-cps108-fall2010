package arcade.store.gui.tabs;

import javax.swing.JComponent;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.control.MainController;
import arcade.store.control.RentalTradeController;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RentalTradeTab extends Tab implements IViewer{

	private JPanel rentalTradePanel = null;  //  @jve:decl-index=0:visual-constraint="189,54"
	private JLabel rentalTradeLabel = null;
	private JPanel typesPanel = null;
	private JLabel typeLabel = null;
	private JList genreList = null;
	private JLabel searchLabel = null;
	private JLabel jFilter = null;
	private JComboBox searchComboBox = null;
	private JComboBox filerComboBox = null;
	private JScrollPane tradeListScrollPane = null;
	private JTable jTable = null;

	private static final String NAME = "Rental and Trade";
	private IController controller;
	
	/**
	 * This method initializes rentalTradePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */

	public RentalTradeTab()
	{
		controller = new RentalTradeController();
		controller.addViewer(this);
		setName(NAME);
	}
	
	public String getName()
	{
		return NAME;
	}
	
	@Override
	public void setController(IController control) {
		controller = control;
		
	}
	
	@Override
	public IController getController()
	{
		return controller;
	}
	
	@Override
	public JComponent getContent() {
		if (rentalTradePanel == null) {
			jFilter = new JLabel();
			jFilter.setBounds(new Rectangle(428, 73, 67, 23));
			jFilter.setText("Filter by:");
			searchLabel = new JLabel();
			searchLabel.setBounds(new Rectangle(243, 72, 69, 22));
			searchLabel.setText("Search by: ");
			typeLabel = new JLabel();
			typeLabel.setBounds(new Rectangle(59, 70, 50, 23));
			typeLabel.setText("Genre:");
			rentalTradeLabel = new JLabel();
			rentalTradeLabel.setBounds(new Rectangle(33, 24, 150, 24));
			rentalTradeLabel.setText("The Rental / Trade Center");
			rentalTradePanel = new JPanel();
			rentalTradePanel.setLayout(null);
			rentalTradePanel.setSize(new Dimension(596, 548));
			rentalTradePanel.add(rentalTradeLabel, null);
			rentalTradePanel.add(getTypesPanel(), null);
			rentalTradePanel.add(typeLabel, null);
			rentalTradePanel.add(searchLabel, null);
			rentalTradePanel.add(jFilter, null);
			rentalTradePanel.add(getSearchComboBox(), null);
			rentalTradePanel.add(getFilerComboBox(), null);
			rentalTradePanel.add(getTradeListScrollPane(), null);
		}
		return rentalTradePanel;
	}

	/**
	 * This method initializes typesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTypesPanel() {
		if (typesPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 0;
			typesPanel = new JPanel();
			typesPanel.setLayout(new GridBagLayout());
			typesPanel.setBounds(new Rectangle(56, 111, 138, 422));
			typesPanel.add(getGenreList(), gridBagConstraints3);
		}
		return typesPanel;
	}

	/**
	 * This method initializes genreList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getGenreList() {
		if (genreList == null) {
			genreList = new JList();
		}
		return genreList;
	}

	/**
	 * This method initializes searchComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSearchComboBox() {
		if (searchComboBox == null) {
			searchComboBox = new JComboBox();
			searchComboBox.setBounds(new Rectangle(325, 72, 70, 20));
		}
		return searchComboBox;
	}

	/**
	 * This method initializes filerComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFilerComboBox() {
		if (filerComboBox == null) {
			filerComboBox = new JComboBox();
			filerComboBox.setBounds(new Rectangle(504, 73, 62, 20));
		}
		return filerComboBox;
	}

	/**
	 * This method initializes tradeListScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTradeListScrollPane() {
		if (tradeListScrollPane == null) {
			tradeListScrollPane = new JScrollPane();
			tradeListScrollPane.setBounds(new Rectangle(233, 114, 453, 419));
			tradeListScrollPane.setViewportView(getJTable());
		}
		return tradeListScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

}
