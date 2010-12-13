package arcade.util.xmleditor.view;

public class ElementNameController {
	
	private String elementName;
	private ElementNamePanel view;
	
	public ElementNameController(){
		view = new ElementNamePanel();
	}
	
	public ElementNameController(String elementName){
		this();
		this.elementName = elementName;
	}
	
	public ElementNamePanel getView(){
		return view;
	}
	
	public void setElementName(String elementName){
		this.elementName = elementName;
		view.update(elementName);
	}

}
