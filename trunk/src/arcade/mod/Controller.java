package arcade.mod;

import java.util.Collection;

public class Controller {

	Model myModel;
	
	public Controller(){
		View modView = new View(this);
		myModel = new Model();
	}
	
	public Collection<String> getCategories(){
		return myModel.getCategories();
	}
	
	//temporary main method until arcade adds a button to call us
	public static void main(String[] args){
		Controller mod = new Controller();
	}	
}
