package com.mrprez.gencross.ui.filechooser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserFactory {
	public static File defaultDirectory;
	
	
	public static JFileChooser getOpenFileChooser(){
		JFileChooser fileChooser = new JFileChooser(defaultDirectory);
		FileFilter gcrFileFilter = new FileNameExtensionFilter("Fichiers Gen-Cross","gcr");
		fileChooser.addChoosableFileFilter(gcrFileFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		return fileChooser;
	}
	
	public static JFileChooser getSaveFileChooser(){
		JFileChooser fileChooser = new JFileChooser(defaultDirectory);
		FileFilter gcrFileFilter = new FileNameExtensionFilter("Gen-Cross file","gcr");
		fileChooser.addChoosableFileFilter(gcrFileFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		return fileChooser;
	}

}
