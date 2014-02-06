package com.mrprez.gencross.ui.persoframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.addproperty.AddPropertyEditor;
import com.mrprez.gencross.ui.chooseValueEditor.ChooseValueEditor;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;
import com.mrprez.gencross.ui.inteditor.IntEditor;
import com.mrprez.gencross.value.StringValue;
import com.mrprez.gencross.value.Value;

public class EditPropertyListener implements MouseListener, ActionListener {
	private PropertyTree tree;
	
	
	public EditPropertyListener(PropertyTree tree){
		super();
		this.tree = tree;
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		try{
			if(mouseEvent.getClickCount()==2){
				Object userObject = ((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getUserObject();
				if(userObject instanceof Property){
					modifyProperty();
				}else if(userObject instanceof String){
					addProperty();
				}
				Personnage personnage = GenCrossUI.getCurrentPersonnage();
				if(personnage.getActionMessage()!=null){
					JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), personnage.getActionMessage(), "Action impossible", JOptionPane.WARNING_MESSAGE);
					personnage.clearActionMessage();
				}
			}
		}catch(Exception e){
			(new ErrorFrame(e)).setVisible(true);
		}
	}
	
	private Value getNewValue(Property property){
		if(property.getValue()==null){
			return null;
		}
		if(property.getOptions()!=null){
			return ChooseValueEditor.askValue(property);
		}else if(property.getValue().getOffset()!=null){
			IntEditor intEditor = new IntEditor(GenCrossUI.getCurrentPersoFrame() ,property);
			return intEditor.askValue();
		}else if(property.getValue() instanceof StringValue){
			String text = (String) JOptionPane.showInputDialog(GenCrossUI.getCurrentPersoFrame(), property.getFullName(),property.getFullName(),JOptionPane.PLAIN_MESSAGE,null,null,property.getValue().toString());
			return text==null?null:new StringValue(text);
		}
		return null;
	}
	
	
	
	private void modifyProperty() throws Exception{
		Property property = (Property) ((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getUserObject();
		if(property.isEditable()){
			Value newValue = getNewValue(property);
			if(newValue!=null){
				boolean success = property.getPersonnage().setNewValue(property, newValue);
				if(success){
					tree.getPersoFrame().saveModification();
					tree.getPersoFrame().reinitErrorPanel();
					tree.getPersoFrame().reinitButtonPanel();
					tree.getPersoFrame().reinitPointsPanel();
					SwingUtilities.updateComponentTreeUI(tree.getPersoFrame().getHistoryFrame().getHistoryTable());
					tree.update();
					SwingUtilities.updateComponentTreeUI(tree);
				}
			}
		}
	}
	
	private void addProperty() throws Exception{
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
		Property motherProperty = (Property) parentNode.getUserObject();
		AddPropertyEditor editor = new AddPropertyEditor(tree, motherProperty.getFullName(), motherProperty.getSubProperties());
		Property property = editor.getProperty();
		if(property!=null){
			boolean success = motherProperty.getPersonnage().addPropertyToMotherProperty(property);
			if(success){
				tree.getPersoFrame().saveModification();
				tree.getPersoFrame().reinitErrorPanel();
				tree.getPersoFrame().reinitButtonPanel();
				tree.getPersoFrame().reinitPointsPanel();
				SwingUtilities.updateComponentTreeUI(tree.getPersoFrame().getHistoryFrame().getHistoryTable());
				tree.update();
				SwingUtilities.updateComponentTreeUI(tree);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Object userObject = ((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getUserObject();
			if(userObject instanceof Property){
				modifyProperty();
			}
		} catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
