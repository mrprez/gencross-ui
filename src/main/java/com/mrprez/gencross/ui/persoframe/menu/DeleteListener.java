package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;
import com.mrprez.gencross.ui.persoframe.PropertyTree;

public class DeleteListener implements ActionListener {
	private PropertyTree propertyTree;
	
	
	public DeleteListener(PropertyTree propertyTree) {
		super();
		this.propertyTree = propertyTree;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			if(propertyTree.getSelectionPath()!=null){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)propertyTree.getSelectionPath().getLastPathComponent();
				Property property = (Property) node.getUserObject();
				int confirm = JOptionPane.showConfirmDialog(propertyTree, "Voulez-vous supprimer la propriété "+property.getFullName()+"?", "Suppression", JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.YES_OPTION){
					Personnage personnage = property.getPersonnage();
					boolean success = personnage.removePropertyFromMotherProperty(property);
					if(!success){
						if(personnage.getActionMessage()!=null){
							JOptionPane.showMessageDialog(propertyTree, personnage.getActionMessage(), "Suppression impossible", JOptionPane.WARNING_MESSAGE);
							personnage.clearActionMessage();
						}else{
							JOptionPane.showMessageDialog(propertyTree, "Impossible de supprimer cette propriété.", "Suppression impossible", JOptionPane.WARNING_MESSAGE);
						}
					}else{
						propertyTree.getPersoFrame().saveModification();
						propertyTree.getPersoFrame().reinitErrorPanel();
						propertyTree.getPersoFrame().reinitButtonPanel();
						propertyTree.getPersoFrame().reinitPointsPanel();
						SwingUtilities.updateComponentTreeUI(propertyTree.getPersoFrame().getHistoryFrame().getHistoryTable());
						propertyTree.update();
						SwingUtilities.updateComponentTreeUI(propertyTree);
					}
				}
			}
		}catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
