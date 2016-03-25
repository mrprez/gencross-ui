package com.mrprez.gencross.ui.persoframe;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import com.mrprez.gencross.ui.GenCrossUI;

public class CloseListener implements WindowListener {

	

	@Override
	public void windowActivated(WindowEvent arg0) {
		;
	}

	@Override
	public void windowClosed(WindowEvent event) {
		;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		if(!GenCrossUI.getInstance().isSaved()){
			int confirm = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Vous n'avez pas sauvegardé, voulez-vous vraiment quitter?", "Fermer", JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.NO_OPTION){
				return;
			}
		}
		GenCrossUI.getCurrentPersoFrame().getHistoryFrame().dispose();
		GenCrossUI.getCurrentPersoFrame().dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		;
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		;
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		;
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		;
	}

}
