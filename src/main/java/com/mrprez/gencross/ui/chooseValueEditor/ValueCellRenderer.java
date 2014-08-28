package com.mrprez.gencross.ui.chooseValueEditor;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import com.mrprez.gencross.renderer.Renderer;
import com.mrprez.gencross.value.Value;

public class ValueCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;
	private Renderer renderer;
	
	public ValueCellRenderer(Renderer renderer) {
		super();
		this.renderer = renderer;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		if(value instanceof Value){
			JLabel result = (JLabel) super.getListCellRendererComponent(list,value, index, isSelected, cellHasFocus);
			result.setText(renderer.displayValue((Value)value));
			return result;
		}
		return super.getListCellRendererComponent(list,value, index, isSelected, cellHasFocus);
	}
	
	

}
