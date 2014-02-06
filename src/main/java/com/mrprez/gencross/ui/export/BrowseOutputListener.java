package com.mrprez.gencross.ui.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mrprez.gencross.export.FileGenerator;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class BrowseOutputListener implements ActionListener {
	private ExportDialog exportDialog;
	private JFileChooser chooser = new JFileChooser();
	
	
	public BrowseOutputListener(ExportDialog exportDialog) {
		super();
		this.exportDialog = exportDialog;
		chooser.setMultiSelectionEnabled(false);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			chooser.resetChoosableFileFilters();
			FileGenerator generator;
			generator = exportDialog.getGeneratorClass().newInstance();
		
			chooser.addChoosableFileFilter(new FileNameExtensionFilter(generator.getOutputExtension(), generator.getOutputExtension()));
			
			int returnVal = chooser.showSaveDialog(exportDialog);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				exportDialog.setOutputPath(file.getAbsolutePath());
			}
		} catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
