package arcade.security.view;

import javax.swing.*;

import arcade.security.control.Control;

import java.awt.*;
import java.awt.event.*;

public class RetrievePasswordPanel extends ViewState{
	
	private static final long serialVersionUID = 1L;
	private Control controller;
	
	public RetrievePasswordPanel(Control controller){
		this.controller = controller;
		setName("Retrieve password");
	}
	protected void addListeners(){
		
	}
	
	
}
