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
	
	public void notifyModelChanged(XMLNode root){
		for(ModelListener listener: listeners){
			listener.modelChanged(root);
		}
	}
	
	public void notifyNodeSelected(XMLNode node){
		for(ModelListener listener: listeners){
			listener.nodeSelected(node);
		}
	}
	
	public void notifyNodeUpdated(XMLNode node){
		for(ModelListener listener: listeners){
			listener.nodeUpdated(node);
		}
	}
}
