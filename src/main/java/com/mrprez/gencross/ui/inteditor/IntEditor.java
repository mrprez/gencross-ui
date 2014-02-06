package com.mrprez.gencross.ui.inteditor;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.renderer.Renderer;
import com.mrprez.gencross.value.Value;

public class IntEditor {
	private JButton minusButton = new JButton("-");
	private JButton addButton = new JButton("+");
	private JLabel label = new JLabel();
	private Component parent;
	private Value value;
	private String title;
	private Value min;
	private Value max;
	private Renderer renderer;

	
	public IntEditor(Component parent, Property property) {
		super();
		this.parent = parent;
		this.title = property.getFullName();
		this.renderer = property.getRenderer();
		min = property.getMin();
		max = property.getMax();
		setValue(property.getValue().clone());
	}
	
	public Value getValue() {
		return value;
	}
	public void setValue(Value value){
		this.value = value;
		calculateButtonsAvailability();
		label.setText(renderer.displayValue(value));
	}

	public Value askValue() {
		minusButton.addActionListener(new IntEditorListener(this,-1));
		addButton.addActionListener(new IntEditorListener(this,1));
		calculateButtonsAvailability();
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(minusButton);
		panel.add(label);
		panel.add(addButton);
		
		int result = JOptionPane.showConfirmDialog(parent, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(result!=JOptionPane.OK_OPTION){
			return null;
		}
		return value;
	}
	
	public void calculateButtonsAvailability(){
		if(min!=null && value.equals(min)){
			minusButton.setEnabled(false);
		}else{
			minusButton.setEnabled(true);
		}
		if(max!=null && value.equals(max)){
			addButton.setEnabled(false);
		}else{
			addButton.setEnabled(true);
		}
	}

}
