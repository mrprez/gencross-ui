package com.mrprez.gencross.ui.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.mrprez.gencross.export.FileGenerator;
import com.mrprez.gencross.export.TemplatedFileGenerator;

public class SelectFileGeneratorListener implements ActionListener {
	private JButton selectTemplateButton;
	private JTextField templateTextField;
	
	
	public SelectFileGeneratorListener(JButton selectTemplateButton, JTextField templateTextField) {
		super();
		this.selectTemplateButton = selectTemplateButton;
		this.templateTextField = templateTextField;
	}




	@Override
	public void actionPerformed(ActionEvent event) {
		Class<? extends FileGenerator> selectedFileGeneratorClass = FileGenerator.getGeneratorList().get(((JComboBox)event.getSource()).getSelectedItem());
		boolean templatedGenerator = TemplatedFileGenerator.class.isAssignableFrom(selectedFileGeneratorClass);
		selectTemplateButton.setEnabled(templatedGenerator);
		templateTextField.setEnabled(templatedGenerator);
	}

}
