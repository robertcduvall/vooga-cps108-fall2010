package arcade.core.mvc;

import arcade.core.Tab;


/**
 * 
 * @author Jimmy Mu
 * @date 12-09-10
 * @description 
 */

public interface IController {

	public void initialize();
	
	public void addModel(IModel model);
	
	public void addViewer(Tab viewer);
}
