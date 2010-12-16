package arcade.util.xmleditor.model;

public interface ModelListener {

	public void modelChanged(XMLNode root);

	public void nodeSelected(XMLNode node);

	public void nodeUpdated(XMLNode node);

}
