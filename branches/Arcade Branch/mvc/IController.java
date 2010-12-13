package arcade.core.mvc;


/**
 * 
 * @author Jimmy Mu
 * @date 12-09-10
 * @description 
 */

public interface IController {

	public void initialize();
	
	public void addModel(IModel model);
	
	public void addViewer(IViewer viewer);
}
