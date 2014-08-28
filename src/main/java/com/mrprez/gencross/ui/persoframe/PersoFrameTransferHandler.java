package com.mrprez.gencross.ui.persoframe;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.exception.PersonnageVersionException;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class PersoFrameTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean canImport(TransferSupport transferSupport) {
		if(!transferSupport.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean importData(TransferSupport transferSupport) {
		try {
			if (!canImport(transferSupport)) {
		        return false;
		    }
			if(!GenCrossUI.getInstance().isSaved()){
				int confirm = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Vous n'avez pas sauvegardé, voulez-vous vraiment quitter ce personnage?", "Ouvrir", JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.NO_OPTION){
					return false;
				}
			}
			List<File> fileList = (List<File>) transferSupport.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			if(fileList.isEmpty()){
				throw new Exception("No File");
			}
			Personnage personnage = null;
			File file  = fileList.get(0);
			try{
				personnage = GenCrossUI.getInstance().getPersonnageFactory().loadPersonnageFromGcr(file);
			}catch (PersonnageVersionException e) {
				if(e.getFileVersion()!=null && e.getPluginVersion()!=null && e.getFileVersion().compareTo(e.getPluginVersion())<0){
					int result = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Votre fichier n'est pas dans le bonne version, voulez vous le convertir?", "Version", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(result==JOptionPane.OK_OPTION){
						GenCrossUI.getInstance().getPersonnageFactory().setAcceptMigration(true);
						personnage = GenCrossUI.getInstance().getPersonnageFactory().loadPersonnageFromGcr(file);
						GenCrossUI.getInstance().getPersonnageFactory().setAcceptMigration(false);
					}
				}else{
					JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), e.getMessage(), "Version", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(personnage!=null){
				GenCrossUI.getInstance().setPersonnage(personnage);
				GenCrossUI.getInstance().setLastSave(file);
				GenCrossUI.getInstance().clearLastModifications();
				GenCrossUI.getInstance().saveModification();
				GenCrossUI.getInstance().setMj(false);
		    	GenCrossUI.getCurrentPersoFrame().reinitTitle();
		    	GenCrossUI.getInstance().setSaved(true);
			}
		} catch (Exception e) {
			ErrorFrame.displayError(e);
		}
		return super.importData(transferSupport);
	}
	
	

}
