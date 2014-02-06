package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mrprez.gencross.ui.persoframe.PropertyTree;

public class ExpandListener implements ActionListener {
	private PropertyTree propertyTree;
	
	
	public ExpandListener(PropertyTree propertyTree) {
		super();
		this.propertyTree = propertyTree;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(propertyTree.getSelectionPath()!=null){
			propertyTree.expandAll((DefaultMutableTreeNode) propertyTree.getSelectionPath().getLastPathComponent());
		}
	}

}
