package arcade.store.gui.pages;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.control.MainController;
import arcade.store.control.MainPageController;
import arcade.store.control.PurchaseItemController;

public class GamePurchaseView implements Tab {

	private JFrame gamePurchaseFrame = null;
	private JPanel gamePurchasePanel = null; // @jve:decl-index=0:visual-constraint="-15,3"
	private JButton addToCartButton = null;
	private JButton DemoTheGameButton = null;
	private JLabel DescriptionLabel = null;
	private JTextArea DescriptionTextArea = null;
	private JLabel gameImageLabel = null;
	private JLabel PriceLabel = null;
	private JLabel TitleLabel = null;
	private JTextField TitleTextField = null;
	private JTextField PriceTextField = null;
	private JLabel randomReviewLabel = null;

	private PurchaseItemController controller; // @jve:decl-index=0:
	private JButton returnToStoreButton = null;
	private JPanel commentPanel = null;

	public GamePurchaseView(IController control) {
		controller = (PurchaseItemController) control;
		gamePurchaseFrame = getJFrame();
		gamePurchaseFrame.setVisible(true);
	}

	public JFrame getJFrame() {
		JFrame jFrame = new JFrame();
		jFrame.setSize(new Dimension(650, 600));
		jFrame.setTitle("Game Purchase View");
		jFrame.setContentPane(getGamePurchasePanel());

		return jFrame;
	}

	@Override
	public void setController(IController control) {

		controller = (PurchaseItemController) controller;
	}

	/**
	 * This method initializes gamePurchasePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getGamePurchasePanel() {
		if (gamePurchasePanel == null) {
			TitleLabel = new JLabel();
			TitleLabel.setBounds(new Rectangle(184, 20, 46, 21));
			TitleLabel.setText("Title:");
			PriceLabel = new JLabel();
			PriceLabel.setBounds(new Rectangle(184, 50, 43, 24));
			PriceLabel.setText("Price:");
			DescriptionLabel = new JLabel();
			DescriptionLabel.setBounds(new Rectangle(184, 85, 87, 24));
			DescriptionLabel.setText("Description:");
			gamePurchasePanel = new JPanel();
			gamePurchasePanel.setLayout(null);
			gamePurchasePanel.setSize(new Dimension(632, 277));
			gamePurchasePanel.add(getAddToCartButton(), null);
			gamePurchasePanel.add(getDemoTheGameButton(), null);
			gamePurchasePanel.add(DescriptionLabel, null);
			gamePurchasePanel.add(getDescriptionTextArea(), null);
			gamePurchasePanel.add(getGameIcon(), null);
			gamePurchasePanel.add(PriceLabel, null);
			gamePurchasePanel.add(TitleLabel, null);
			gamePurchasePanel.add(getTitleTextField(), null);
			gamePurchasePanel.add(getPriceTextField(), null);
			gamePurchasePanel.add(getReturnToStoreButton(), null);
			randomReviewLabel = new JLabel();
			gamePurchasePanel.add(getCommentPanel(), null);
		}
		return gamePurchasePanel;
	}

	private JLabel getRandomReviewLabel() {
		if (randomReviewLabel == null) {
			randomReviewLabel = new JLabel("LABEL!!");
			randomReviewLabel.setText("Label");
		}
		return randomReviewLabel;
	}

	/**
	 * This method returns the gameImageLabel
	 */
	public JLabel getGameIcon() {
		if (gameImageLabel == null) {
			gameImageLabel = new JLabel();

		}
		return gameImageLabel;
	}

	/**
	 * This method initializes addToCartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddToCartButton() {
		if (addToCartButton == null) {
			addToCartButton = new JButton();
			addToCartButton.setBounds(new Rectangle(480, 58, 136, 30));
			addToCartButton.setText("Add To Cart");
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
		if (DemoTheGameButton == null) {
			DemoTheGameButton = new JButton();
			DemoTheGameButton.setBounds(new Rectangle(481, 19, 134, 30));
			DemoTheGameButton.setText("Demo The Game");
			DemoTheGameButton
					.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							controller.processConfirmDemoGame();
						}
					});

		}
		return DemoTheGameButton;
	}

	/**
	 * This method initializes DescriptionTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextArea getDescriptionTextArea() {
		if (DescriptionTextArea == null) {
			DescriptionTextArea = new JTextArea();
			DescriptionTextArea.setBounds(new Rectangle(184, 121, 278, 157));
			DescriptionTextArea.setWrapStyleWord(true);
		}
		return DescriptionTextArea;
	}

	/**
	 * This method initializes TitleTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getTitleTextField() {
		if (TitleTextField == null) {
			TitleTextField = new JTextField();
			TitleTextField.setBounds(new Rectangle(242, 22, 219, 20));
		}
		return TitleTextField;
	}

	/**
	 * This method initializes PriceTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getPriceTextField() {
		if (PriceTextField == null) {
			PriceTextField = new JTextField();
			PriceTextField.setBounds(new Rectangle(241, 52, 220, 20));
		}
		return PriceTextField;
	}

	/**
	 * This method initializes returnToStoreButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getReturnToStoreButton() {
		if (returnToStoreButton == null) {
			returnToStoreButton = new JButton();
			returnToStoreButton.setBounds(new Rectangle(480, 97, 136, 30));
			returnToStoreButton.setText("Return To Store");
			returnToStoreButton
					.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent e) {
							gamePurchaseFrame.setVisible(false);
						}
					});
		}
		return returnToStoreButton;
	}

	@Override
	public IController getController() {
		return controller;
	}

	public void setReviewText(String content) {
		randomReviewLabel.setText(content);
	}

	/**
	 * This method initializes commentPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCommentPanel() {
		if (commentPanel == null) {
			commentPanel = new JPanel();
			commentPanel.add(randomReviewLabel);
			commentPanel.setLayout(new GridBagLayout());
			commentPanel.setBounds(new Rectangle(34, 309, 590, 250));
		}
		return commentPanel;
	}

	@Override
	public JComponent getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
