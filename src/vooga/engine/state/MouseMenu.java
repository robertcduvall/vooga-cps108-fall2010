/**
 * 
 * @author: Yijia Mu
 * @date: 10-17-10 
 * @description:
 */

package vooga.engine.state;

import com.golden.gamedev.object.Sprite;


public interface MouseMenu {

	public void actOnMouseClicked(double x, double y);
	public void actOnMouseClicked(Sprite sprite);
	public void actOnMouseHover(Sprite sprite);
		
}
