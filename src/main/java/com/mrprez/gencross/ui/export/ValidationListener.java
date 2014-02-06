package com.mrprez.gencross.ui.export;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mrprez.gencross.export.FileGenerator;
import com.mrprez.gencross.export.TemplatedFileGenerator;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class ValidationListener implements ActionListener {
	private ExportDialog exportFrame;
	
	
	public ValidationListener(ExportDialog exportFrame) {
		super();
		this.exportFrame = exportFrame;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			FileGenerator generator = exportFrame.getGeneratorClass().newInstance();
			if(generator instanceof TemplatedFileGenerator){
				((TemplatedFileGenerator)generator).setTemplate(new File(exportFrame.getTemplatePath()));
				File propertiesFile = new File(exportFrame.getTemplatePath()+".properties");
				if(propertiesFile.exists()){
					((TemplatedFileGenerator)generator).loadProperties(new FileInputStream(propertiesFile));
				}
			}
			File outputFile = getOutputFile(generator, exportFrame.getOutputPath());
			FileOutputStream fis = new FileOutputStream(outputFile);
			try{
				generator.write(GenCrossUI.getCurrentPersonnage(), fis);
			}finally{
				fis.close();
			}
			Desktop.getDesktop().open(outputFile);
			exportFrame.dispose();
		} catch (FileNotFoundException e) {
			ErrorFrame.displayError(e);
		} catch (IOException e) {
			ErrorFrame.displayError(e);
		} catch (InstantiationException e) {
			ErrorFrame.displayError(e);
		} catch (IllegalAccessException e) {
			ErrorFrame.displayError(e);
		}
	}
	
	private File getOutputFile(FileGenerator generator, String outputPath){
		if(generator.getOutputExtension()==null){
			return new File(exportFrame.getOutputPath());
		}
		if(outputPath.endsWith("."+generator.getOutputExtension())){
			return new File(exportFrame.getOutputPath());
		}
		return  new File(exportFrame.getOutputPath()+"."+generator.getOutputExtension());
	}

}
