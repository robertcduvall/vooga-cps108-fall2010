package arcade.mod.view;

import arcade.mod.model.IResourceNode;

/**
 * An object list frame is a list frame which represents any game object.
 * By giving the user the ability to switch out one Object java file for another,
 * we are giving the user the opportunity to modify any object behavior through
 * simple extension.
 * 
 * @author Brian, Brent, Daniel, and Vitor
 *
 */

public class ObjectListFrame extends FilepathListFrame{
	
	public ObjectListFrame() {
	}

	public ObjectListFrame(IResourceNode node) {
		super(node);
	}

/*	@Override
	public AbstractListFrame newInstance(IResourceNode node) {

		return new IntegerListFrame(node);
	}*/

	
}
