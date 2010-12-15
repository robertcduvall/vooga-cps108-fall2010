package arcade.util.xmleditor.view.toolbar;

import java.awt.event.ActionEvent;

public class DeleteElementController extends ElementToolBarButton{
	
	public DeleteElementController(){
		super("Delete Element");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		getElement().getParentNode().removeChild(getElement());
	}
}
