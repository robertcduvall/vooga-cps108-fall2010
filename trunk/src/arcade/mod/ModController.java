package arcade.mod;

import java.util.Collection;

public class ModController {

	Model myModel;
	
	public ModController(){
		ModView modView = new ModView(this);
		myModel = new Model();
	}
	
	public Collection<String> getCatagories(){
		return myModel.getCategories();
	}
	
	//temporary main method until arcade adds a button to call us
	public static void main(String[] args){
		ModController mod = new ModController();
	}	
}
