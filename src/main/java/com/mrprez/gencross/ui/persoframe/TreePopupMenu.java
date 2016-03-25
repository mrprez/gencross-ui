package com.mrprez.gencross.ui.persoframe;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.ui.persoframe.menu.CollapseListener;
import com.mrprez.gencross.ui.persoframe.menu.DeleteListener;
import com.mrprez.gencross.ui.persoframe.menu.ExpandListener;

public class TreePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private PropertyTree propertyTree;
	private JMenuItem deleteItem = new JMenuItem("Supprimer");
	private JMenuItem expandItem = new JMenuItem("Expand");
	private JMenuItem collapseItem = new JMenuItem("Collapse");
	private JMenuItem editItem = new JMenuItem("Editer");
	private JMenuItem spendPointsItem = new JMenuItem("Dépenses");
	
	
	public TreePopupMenu(PropertyTree propertyTree){
		super();
		this.propertyTree = propertyTree;
		deleteItem.addActionListener(new DeleteListener(propertyTree));
		this.add(deleteItem);
		expandItem.addActionListener(new ExpandListener(propertyTree));
		this.add(expandItem);
		collapseItem.addActionListener(new CollapseListener(propertyTree));
		this.add(collapseItem);
		editItem.addActionListener(new EditPropertyListener(propertyTree));
		this.add(editItem);
		spendPointsItem.addActionListener(new HistorySumListener(propertyTree));
		this.add(spendPointsItem);
	}

	@Override
	protected void firePopupMenuWillBecomeVisible() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) propertyTree.getSelectionPath().getLastPathComponent();
		if(selectedNode.getUserObject() instanceof String){
			this.setDeleteEnabled(false);
			editItem.setEnabled(false);
		}else{
			this.setDeleteEnabled(true);
			Property property = (Property)selectedNode.getUserObject();
			editItem.setEnabled(property.isEditable());
		}
		super.firePopupMenuWillBecomeVisible();
	}
	
	public void setDeleteEnabled(boolean b){
		deleteItem.setEnabled(b);
	}
	
	

}
