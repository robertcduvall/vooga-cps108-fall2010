package arcade.mod;

public class ModController {

	//temporary main method until arcade adds a button to call us
	public static void main(String[] args){
		ModController mod = new ModController();
	}
	
	
	public ModController(){
		ModView modView = new ModView(this);
	}
	
	
}
