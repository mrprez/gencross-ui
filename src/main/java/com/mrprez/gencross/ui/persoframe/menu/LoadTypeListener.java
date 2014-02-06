package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.disk.RepositoryManager;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class LoadTypeListener implements ActionListener {
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			if(checkPersonnageIsSaved()){
				File choosenFile = chooseFile();
				if(choosenFile != null) {
					PluginDescriptor pluginDescriptor = extractPluginDescriptor(choosenFile);
					if(pluginDescriptor != null) {
						RepositoryManager repositoryManager = GenCrossUI.getInstance().getRepositoryManager();
						repositoryManager.archivePlugin(pluginDescriptor.getName());
						repositoryManager.addPlugin(choosenFile);
						GenCrossUI.reinit();
					}else{
						JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), "Ce fichier n'est pas un plugin gencross.", "Fichier non reconnu", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}catch(Exception ioe){
			ErrorFrame.displayError(ioe);
		}
	}
	
	private File chooseFile(){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter zipFilter = new FileNameExtensionFilter("JAR files","jar");
		fileChooser.addChoosableFileFilter(zipFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(GenCrossUI.getCurrentPersoFrame());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	private boolean checkPersonnageIsSaved(){
		if(GenCrossUI.getCurrentPersonnage().size() > 0) {
			if(GenCrossUI.getInstance().isSaved()){
				int result = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Ceci va entrainer un redémarrage de l'application. Continuer?", "Confirmation", JOptionPane.YES_NO_OPTION);
				return result==JOptionPane.YES_OPTION;
			} else {
				int result = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Votre personnage n'est pas sauvegardé, il va être perdu.", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
				return result==JOptionPane.OK_OPTION;
			}
		}
		return true;
	}
	
	
	private PluginDescriptor extractPluginDescriptor(File jarFile) throws IOException {
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(jarFile)));
		try{
			for(ZipEntry entry = zis.getNextEntry(); entry!=null; entry = zis.getNextEntry()){
				if(entry.getName().equals(PluginDescriptor.PLUGIN_DESC_FILE_NAME)){
					return new PluginDescriptor(zis);
				}
			}
		} finally {
			zis.close();
		}
		return null;
	}


}
