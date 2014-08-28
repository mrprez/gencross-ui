package com.mrprez.gencross.ui.addproperty;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;

public class AddPropertyEditor extends JPanel {
	private static final long serialVersionUID = 1L;
	public static String AUTRE = "Autre...";
	public static String PROPERTY_NAME = "Nom de la propriété: ";
	public static String NO_OPTIONS = "Aucune option disponible.";
	private JComboBox comboBox;
	private String title;
	private Component parent;
	private JTextField textField;
	private JPanel panel = new JPanel();
	private PropertiesList propertiesList;
	private Set<String> optionSet;
	
	
	public AddPropertyEditor(Component parent, String title, PropertiesList propertiesList){
		super();
		this.parent = parent;
		this.title = title;
		this.propertiesList = propertiesList;
		optionSet = new LinkedHashSet<String>(propertiesList.getOptions().keySet());
		optionSet.removeAll(propertiesList.keySet());
		
	}
	
	private int showDialogBox(){
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		if(!optionSet.isEmpty()){
			comboBox = new JComboBox(optionSet.toArray());
			panel.add(comboBox);
			if(propertiesList.isOpen()){
				textField = new JTextField(12);
				textField.setBorder(BorderFactory.createLoweredBevelBorder());
				comboBox.addItem(AUTRE);
				comboBox.addItemListener(new AddPropertyListener(this));
			}
			return JOptionPane.showConfirmDialog(parent, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		}else{
			if(propertiesList.isOpen()){
				panel.add(new JLabel(PROPERTY_NAME));
				textField = new JTextField(12);
				textField.setBorder(BorderFactory.createLoweredBevelBorder());
				panel.add(textField);
				return JOptionPane.showConfirmDialog(parent, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			} else {
				panel.add(new JLabel(NO_OPTIONS));
				JOptionPane.showMessageDialog(parent, panel, title, JOptionPane.PLAIN_MESSAGE);
				return -1;
			}
		}
	}
	
	private Property askProperty(){
		int result = showDialogBox();
		if(result!=JOptionPane.OK_OPTION){
			return null;
		}
		if(optionSet.isEmpty()){
			Property newProperty = propertiesList.getDefaultProperty().clone();
			newProperty.setName(textField.getText());
			return newProperty;
		}
		if(AUTRE.equals(comboBox.getSelectedItem())){
			Property newProperty = propertiesList.getDefaultProperty().clone();
			newProperty.setName(textField.getText());
			return newProperty;
		}
		return propertiesList.getOptions().get(comboBox.getSelectedItem()).clone();
	}
	
	public Property getProperty(){
		Property result = askProperty();
		if(result==null){
			return null;
		}
		if(result.getSpecification()!=null){
			String specification = JOptionPane.showInputDialog(parent, "Precisez: ", result.getName(), JOptionPane.PLAIN_MESSAGE);
			if(specification!=null){
				result.setSpecification(specification);
			}else{
				return null;
			}
		}
		return result;
	}
	
	public Object getSelectedOption(){
		return comboBox.getSelectedItem();
	}
	
	public void showTextField(){
		panel.add(textField);
		textField.grabFocus();
		getWindow().pack();
		getWindow().repaint();
	}
	
	public void hideTextField(){
		panel.remove(textField);
		getWindow().pack();
		getWindow().repaint();
	}
	
	private Window getWindow(){
		Container container = panel.getParent();
		while(!(container instanceof Window)){
			container = container.getParent();
		}
		return (Window) container;
	}

}
