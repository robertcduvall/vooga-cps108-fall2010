package arcade.util.xmleditor;

import java.io.File;

import arcade.util.xmleditor.model.XMLNode;

public interface IBaseController{
	
	public void setModel(File file);
	
	public void save();
	
	public void save(File file);
	
	public void newNodeSelected(XMLNode node);

}
