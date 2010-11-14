/**
 * 
 * @author: Yijia Mu
 * @date: 10-17-10 
 * @description: This the MouseMenu interface that provides additional complexity the menu design for users with fancy tastes. A menu class, a pause class, and a restart pause can
 * use this class to their advantages. Please add to the code. 
 */

package vooga.engine.state.other;

import com.golden.gamedev.object.Sprite;


public interface MouseMenu {

	public void actOnMouseClicked(double x, double y);
	public void actOnRightMouseClicked(double x, double y);
	public void actOnMouseClicked(Sprite sprite);
	public void actOnRightMouseClicked(Sprite sprite);
	public void actOnMouseHover(Sprite sprite);
		
}
