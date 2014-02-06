package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.ui.persoframe.PropertyTree;

public class CollapseListener implements ActionListener {
	private PropertyTree propertyTree;
	
	
	public CollapseListener(PropertyTree propertyTree) {
		super();
		this.propertyTree = propertyTree;
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		if(propertyTree.getSelectionPath()!=null){
			propertyTree.collapsePath(propertyTree.getSelectionPath());
		}
	}

}
