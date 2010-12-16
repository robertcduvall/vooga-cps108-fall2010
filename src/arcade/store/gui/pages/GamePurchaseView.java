package arcade.store.gui.pages;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import arcade.core.api.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.gui.StoreTab;
import arcade.store.control.MainController;
import arcade.store.control.MainPageController;
import arcade.store.control.PurchaseItemController;

/**
 * 
 * @author Drew Sternesky, Yijia Mu, Marcus Molchany
 * 
 */
public class GamePurchaseView extends StoreTab {

	private static final String FILE_PATH = "arcade.store.resources.Pages";
	private static final long serialVersionUID = 1L;

	private JFrame gamePurchaseFrame = null;
	private JPanel gamePurchasePanel = null; // @jve:decl-index=0:visual-constraint="-15,3"
	private JButton addToCartButton = null;
	private JButton demoTheGameButton = null;

	private JTextArea descriptionTextArea = null;
	private JLabel gameImageLabel = null;
	private JTextField titleTextField = null;
	private JTextField priceTextField = null;

	private PurchaseItemController controller; // @jve:decl-index=0:
	private JButton returnToStoreButton = null;
	private JPanel commentPanel = null;

	public GamePurchaseView(IController control) {
		setResourceBundleFilePath(FILE_PATH);
		controller = (PurchaseItemController) control;
		gamePurchaseFrame = getJFrame();
		gamePurchaseFrame.setVisible(true);
	}

	public JFrame getJFrame() {
		JFrame jFrame = new JFrame();
		jFrame.setSize(new Dimension(Integer
				.parseInt(getString("jFrameWidthString")), Integer
				.parseInt(getString("jFrameHeightString"))));
		jFrame.setTitle(getString("jFrameString"));
		jFrame.setContentPane(getGamePurchasePanel());

		return jFrame;
	}

	@Override
	public void setController(IController control) {

		controller = (PurchaseItemController) controller;
	}

	/**
	 * This method retursn the Item title field
	 * 
	 * @return
	 */
	public String getItemName() {
		return getTitleTextField().getText();
	}

	/**
	 * This method initializes gamePurchasePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getGamePurchasePanel() {
		gamePurchasePanel = new JPanel();
		gamePurchasePanel.setLayout(null);
		gamePurchasePanel.setSize(new Dimension(Integer
				.parseInt(getString("gamePurchasePanelWidthString")), Integer
				.parseInt(getString("gamePurchasePanelHeightString"))));
		gamePurchasePanel.add(getAddToCartButton(), null);
		gamePurchasePanel.add(getDemoTheGameButton(), null);
		gamePurchasePanel.add(getDescriptionLabel(), null);
		gamePurchasePanel.add(getDescriptionTextArea(), null);
		gamePurchasePanel.add(getImageLabel(), null);
		gamePurchasePanel.add(getPriceLabel(), null);
		gamePurchasePanel.add(getTitleLabel(), null);
		gamePurchasePanel.add(getTitleTextField(), null);
		gamePurchasePanel.add(getPriceTextField(), null);
		gamePurchasePanel.add(getReturnToStoreButton(), null);
		// randomReviewLabel = new JLabel();
		// gamePurchasePanel.add(getCommentPanel(), null);
		return gamePurchasePanel;
	}

	private JLabel getPriceLabel() {
		JLabel priceLabel = new JLabel();
		priceLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("priceLabelXString")), Integer
				.parseInt(getString("priceLabelYString")), Integer
				.parseInt(getString("priceLabelWidthString")), Integer
				.parseInt(getString("priceLabelHeightString"))));
		priceLabel.setText(getString("priceLabelString"));
		return priceLabel;
	}

	private JLabel getTitleLabel() {
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("titleLabelXString")), Integer
				.parseInt(getString("titleLabelYString")), Integer
				.parseInt(getString("titleLabelWidthString")), Integer
				.parseInt(getString("titleLabelHeightString"))));
		titleLabel.setText(getString("titleLabelString"));
		return titleLabel;
	}

	private JLabel getDescriptionLabel() {
		JLabel descriptionLabel = new JLabel();
		descriptionLabel.setBounds(new Rectangle(Integer
				.parseInt(getString("descriptionLabelXString")), Integer
				.parseInt(getString("descriptionLabelYString")), Integer
				.parseInt(getString("descriptionLabelWidthString")), Integer
				.parseInt(getString("descriptionLabelHeightString"))));

		descriptionLabel.setText(getString("descriptionLabelString"));
		return descriptionLabel;
	}

	/**
	 * This method returns the gameImageLabel
	 */
	private JLabel getImageLabel() {
		if (gameImageLabel == null) {
			gameImageLabel = new JLabel();
			gameImageLabel.setSize(
					Integer.parseInt(getString("gameImageLabelWidthString")),
					Integer.parseInt(getString("gameImageLabelHeightString")));
		}
		return gameImageLabel;
	}

	public void setGameImageLabel(ImageIcon icon) {
		gameImageLabel.setIcon(icon);
	}

	/**
	 * This method initializes addToCartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddToCartButton() {
		if (addToCartButton == null) {
			addToCartButton = new JButton();
			addToCartButton.setBounds(new Rectangle(Integer
					.parseInt(getString("addToCartButtonXString")), Integer
					.parseInt(getString("addToCartButtonYString")), Integer
					.parseInt(getString("addToCartButtonWidthString")), Integer
					.parseInt(getString("addToCartButtonHeightString"))));

			addToCartButton.setText(getString("addToCartButtonString"));
			addToCartButton
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							controller.processConfirmAddToCart();
						}
					});
		}
		return addToCartButton;
	}

	public void setAddToCartButtonClickable(boolean clickable) {
		addToCartButton.setEnabled(clickable);
	}

	/**
	 * This method initializes DemoTheGameButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDemoTheGameButton() {
		if (demoTheGameButton == null) {
			demoTheGameButton = new JButton();
			demoTheGameButton
					.setBounds(new Rectangle(
							Integer.parseInt(getString("demoTheGameButtonXString")),
							Integer.parseInt(getString("demoTheGameButtonYString")),
							Integer.parseInt(getString("demoTheGameButtonWidthString")),
							Integer.parseInt(getString("demoTheGameButtonHeightString"))));
			demoTheGameButton.setText(getString("demoTheGameButtonString"));
			demoTheGameButton
					.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							controller.processConfirmDemoGame();
						}
					});

		}
		return demoTheGameButton;
	}

	/**
	 * This method initializes DescriptionTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextArea getDescriptionTextArea() {
		if (descriptionTextArea == null) {
			descriptionTextArea = new JTextArea();
			descriptionTextArea
					.setBounds(new Rectangle(
							Integer.parseInt(getString("descriptionTextAreaXString")),
							Integer.parseInt(getString("descriptionTextAreaYString")),
							Integer.parseInt(getString("descriptionTextAreaWidthString")),
							Integer.parseInt(getString("descriptionTextAreaHeightString"))));
			descriptionTextArea.setWrapStyleWord(true);
		}
		return descriptionTextArea;
	}

	/**
	 * Set the text inside of the descriptionTextArea
	 * 
	 * @param text
	 *            description of the item
	 */
	public void setDescriptionTextArea(String text) {
		descriptionTextArea.setText(text);
	}

	/**
	 * This method initializes TitleTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTitleTextField() {
		if (titleTextField == null) {
			titleTextField = new JTextField();
			titleTextField.setBounds(new Rectangle(Integer
					.parseInt(getString("titleTextFieldXString")), Integer
					.parseInt(getString("titleTextFieldYString")), Integer
					.parseInt(getString("titleTextFieldWidthString")), Integer
					.parseInt(getString("titleTextFieldHeightString"))));
		}
		return titleTextField;
	}

	/**
	 * Set the text inside of the titleTextArea
	 * 
	 * @param text
	 *            title of the item
	 */
	public void setTitleTextField(String text) {
		titleTextField.setText(text);
	}

	/**
	 * This method initializes PriceTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getPriceTextField() {
		if (priceTextField == null) {
			priceTextField = new JTextField();
			priceTextField.setBounds(new Rectangle(Integer
					.parseInt(getString("priceTextFieldXString")), Integer
					.parseInt(getString("priceTextFieldYString")), Integer
					.parseInt(getString("priceTextFieldWidthString")), Integer
					.parseInt(getString("priceTextFieldHeightString"))));
		}
		return priceTextField;
	}

	/**
	 * Set the text inside of the priceTextArea
	 * 
	 * @param text
	 *            price of the item
	 */
	public void setPriceTextField(String text) {
		priceTextField.setText(text);
	}

	/**
	 * This method initializes returnToStoreButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getReturnToStoreButton() {
		if (returnToStoreButton == null) {
			returnToStoreButton = new JButton();
			// TODO:
			returnToStoreButton
					.setBounds(new Rectangle(
							Integer.parseInt(getString("returnToStoreButtonXString")),
							Integer.parseInt(getString("returnToStoreButtonYString")),
							Integer.parseInt(getString("returnToStoreButtonWidthString")),
							Integer.parseInt(getString("returnToStoreButtonHeightString"))));
			returnToStoreButton.setText(getString("returnToStoreButtonString"));
			returnToStoreButton
					.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							gamePurchaseFrame.setVisible(false);
						}
					});
		}
		return returnToStoreButton;
	}

	// public void setReviewText(String content) {
	// randomReviewLabel.setText(content);
	// }

	// /**
	// * This method initializes commentPanel
	// *
	// * @return javax.swing.JPanel
	// */
	// private JPanel getCommentPanel() {
	// if (commentPanel == null) {
	// commentPanel = new JPanel();
	// // commentPanel.add(randomReviewLabel);
	// commentPanel.setLayout(new GridBagLayout());
	// commentPanel.setBounds(new Rectangle(34, 309, 590, 250));
	// }
	// return commentPanel;
	// }

}
