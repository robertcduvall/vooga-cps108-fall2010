package arcade.util.xmleditor.model;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ModelObserver {
	
	private Collection<ModelListener> listeners;
	
	public ModelObserver(){
		listeners = new ArrayList<ModelListener>();
	}

	
	public void addNewListener(ModelListener listener){
		listeners.add(listener);
	}
	
	public void notifyModelChanged(Document modelDocument){
		for(ModelListener listener: listeners){
			listener.modelChanged(new XMLNode((Element) modelDocument
					.getFirstChild(), null));
		}
	}
}
