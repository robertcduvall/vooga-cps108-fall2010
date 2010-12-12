package arcade.security.mvc;


/**
 * 
 * @author Jimmy Mu
 * @date 12-09-10
 * @description 
 */

public interface IController {

	public void operate();
	
	public void addModel(IModel model);
	
	public void addViewer(IViewer viewer);
}