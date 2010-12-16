package arcade.util.guiComponents;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


/**
 * 
 * @author: 		Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date:			12-15-10
 * @description: 	This is a modified panel that allows the user
 * 					to grid panels next to each other. This class
 * 					is especially useful if the user needs to 
 * 					connect together different boxes for display.
 */

public abstract class ItemThumbnailPanel extends JPanel{

	/**
	 * This is the default serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The basic constructor.
	 * @param title
	 */
	public ItemThumbnailPanel(String title)
	{
		initialize(title);
	}
	
	/**
	 * This method initialize the Grid layout and 
	 * @param title
	 */
	protected void initialize(String title) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setName(title);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				processMouseEvent();
			}
		});
	}
	
	/**
	 * This method specify how the ImageThumbnailPanel 
	 * is populated. 
	 * @param objects
	 */
	protected abstract void populate();
	
	/**
	 * This method specifies the specific action upon 
	 * clicking on the ItemThumbnailPanel object.
	 */
	protected abstract void processMouseEvent(); 

	
	
	
	
	
}
