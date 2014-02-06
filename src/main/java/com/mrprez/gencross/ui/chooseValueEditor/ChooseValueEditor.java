package com.mrprez.gencross.ui.chooseValueEditor;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.value.Value;

public class ChooseValueEditor {
	private static ChooseValueEditor instance = new ChooseValueEditor();
	private Property property;
	
	
	public static Value askValue(Property property){
		instance.setProperty(property);
		return instance.askValue();
	}
	
	public Value askValue(){
		JComboBox comboBox = new JComboBox(property.getOptions().toArray());
		comboBox.setRenderer(new ValueCellRenderer(property.getRenderer()));
		comboBox.setSelectedItem(property.getValue());
		int result = JOptionPane.showConfirmDialog(GenCrossUI.getInstance().getPersoFrame(), comboBox, property.getFullName(),JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(result == JOptionPane.CANCEL_OPTION || result==-1){
			return null;
		}
		return (Value) comboBox.getSelectedItem();
	}
	
	public void setProperty(Property property){
		this.property = property;
	}

}
