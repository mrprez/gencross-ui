package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.persoframe.PropertyTree;

public class EditCommentListener implements ActionListener {
	private PropertyTree propertyTree;

	
	public EditCommentListener(PropertyTree propertyTree) {
		super();
		this.propertyTree = propertyTree;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) propertyTree.getSelectionPath().getLastPathComponent();
		if(selectedNode.getUserObject() instanceof Property){
			GenCrossUI.getCurrentPersoFrame().getViewCommentFrame().dispose();
			GenCrossUI.getCurrentPersoFrame().getEditCommentFrame().editComment((Property)selectedNode.getUserObject());
		}
	}

}
