package com.mrprez.gencross.ui.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mrprez.gencross.export.TemplatedFileGenerator;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class BrowseTemplateListener implements ActionListener {
	private ExportDialog exportDialog;
	private JFileChooser chooser = new JFileChooser();
	
	
	public BrowseTemplateListener(ExportDialog exportDialog) {
		super();
		this.exportDialog = exportDialog;
		chooser.setMultiSelectionEnabled(false);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			chooser.resetChoosableFileFilters();
			TemplatedFileGenerator generator = (TemplatedFileGenerator) exportDialog.getGeneratorClass().newInstance();
			if(generator.getTemplateFileExtension()!=null){
				if(generator.getTemlpateFileExtensionDescription()==null){
					chooser.addChoosableFileFilter(new FileNameExtensionFilter(generator.getTemplateFileExtension(), generator.getTemplateFileExtension()));
				}else{
					chooser.addChoosableFileFilter(new FileNameExtensionFilter(generator.getTemlpateFileExtensionDescription(), generator.getTemplateFileExtension()));
				}
				chooser.setAcceptAllFileFilterUsed(false);
			}
			
			int returnVal = chooser.showOpenDialog(exportDialog);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				exportDialog.setTemplatePath(file.getAbsolutePath());
			}
		} catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
