package arcade.util.xmleditor.controllers.toolbar;

import java.awt.event.ActionEvent;

/**
 * Deletes the currently selected node from the XML tree
 * @author djk14
 *
 */
public class DeleteElementController extends ElementToolBarButton{
	
	private static final String LABEL_TEXT = "Delete Element";
	
	public DeleteElementController(){
		super(LABEL_TEXT);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		getNode().orphanSelf();
	}
}
