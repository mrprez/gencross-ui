package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;
import com.mrprez.gencross.ui.filechooser.FileChooserFactory;

public class SaveAsListener implements ActionListener {
	


	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			JFileChooser fileChooser = FileChooserFactory.getSaveFileChooser();
			int returnVal = fileChooser.showSaveDialog(GenCrossUI.getCurrentPersoFrame());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	File file = fileChooser.getSelectedFile();
		    	if(!file.getName().endsWith(".gcr")){
		    		file = new File(file.getParentFile(), file.getName()+".gcr");
		    	}
		    	PersonnageSaver.savePersonnageGcr(GenCrossUI.getCurrentPersonnage(), file);
		    	GenCrossUI.getInstance().setLastSave(file);
		    	GenCrossUI.getInstance().setSaved(true);
		    	GenCrossUI.getCurrentPersoFrame().reinitMenuBar();
		    	GenCrossUI.getCurrentPersoFrame().reinitTitle();
		    }
		} catch (Exception e) {
			(new ErrorFrame(e)).setVisible(true);
		}


	}

}
