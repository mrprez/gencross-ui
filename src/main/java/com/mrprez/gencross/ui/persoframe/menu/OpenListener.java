package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.exception.PersonnageVersionException;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;
import com.mrprez.gencross.ui.filechooser.FileChooserFactory;

public class OpenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			if(!GenCrossUI.getInstance().isSaved()){
				int confirm = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Vous n'avez pas sauvegardé, voulez-vous vraiment quitter ce personnage?", "Ouvrir", JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.NO_OPTION){
					return;
				}
			}
			JFileChooser fileChooser = FileChooserFactory.getOpenFileChooser();
			int returnVal = fileChooser.showOpenDialog(GenCrossUI.getCurrentPersoFrame());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	File file = fileChooser.getSelectedFile();
		    	Personnage personnage = null;
		    	try{
					personnage = GenCrossUI.getInstance().getPersonnageFactory().loadPersonnageFromGcr(file);
				}catch (PersonnageVersionException e) {
					if(e.getFileVersion()!=null && e.getPluginVersion()!=null && e.getFileVersion().compareTo(e.getPluginVersion())<0){
						int result = JOptionPane.showConfirmDialog( GenCrossUI.getInstance().getPersoFrame(), "Votre fichier n'est pas dans le bonne version, voulez vous le convertir?", "Version", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(result==JOptionPane.OK_OPTION){
							GenCrossUI.getInstance().getPersonnageFactory().setAcceptMigration(true);
							personnage = GenCrossUI.getInstance().getPersonnageFactory().loadPersonnageFromGcr(file);
							GenCrossUI.getInstance().getPersonnageFactory().setAcceptMigration(false);
						}
					}else{
						JOptionPane.showMessageDialog(GenCrossUI.getInstance().getPersoFrame(), e.getMessage(), "Version", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(personnage!=null){
					GenCrossUI.getInstance().setPersonnage(personnage);
					GenCrossUI.getInstance().setLastSave(file);
					GenCrossUI.getInstance().clearLastModifications();
					GenCrossUI.getInstance().saveModification();
					GenCrossUI.getInstance().setMj(false);
					GenCrossUI.getInstance().setSaved(true);
					GenCrossUI.getCurrentPersoFrame().reinit();
				}
		    }
		} catch (Exception e) {
			(new ErrorFrame(e)).setVisible(true);
		}
	}

}
