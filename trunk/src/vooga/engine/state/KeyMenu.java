/**
 * @author: Jimmy Mu
 * @date: 10-17-10
 * @description: This the KeyMenu interface that provides additional complexity the menu design for users with fancy tastes. A menu class, a pause class, and a restart pause can
 * use this class to their advantages. Please add to the code. 
 */

package vooga.engine.state;

public interface KeyMenu {

	public void keyDown(int key);
	public void multipleKeyDown(int key, int times);
	
}
