package arcade.util.xmleditor.view;

import arcade.util.xmleditor.model.XMLNode;

public interface IBaseView {
	
	public void showView();
	
	public void showError(String message);
	
	public void reloadModel(XMLNode node);
	
	public void updateModel(XMLNode node);

}
