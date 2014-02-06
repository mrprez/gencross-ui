package com.mrprez.gencross.ui.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class BrowseListener implements ActionListener {
	private JTextField textField;
	private JFileChooser chooser = new JFileChooser();
	
	
	public BrowseListener(JTextField textField) {
		super();
		this.textField = textField;
		chooser.setMultiSelectionEnabled(false);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		int returnVal = chooser.showOpenDialog(textField);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			textField.setText(file.getAbsolutePath());
		}
	}

}
