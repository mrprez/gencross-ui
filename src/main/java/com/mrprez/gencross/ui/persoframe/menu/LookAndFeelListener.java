package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class LookAndFeelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			Object returnedObject = JOptionPane.showInputDialog(GenCrossUI.getCurrentPersoFrame(), "Choisir l'apparence", "Apparence", JOptionPane.PLAIN_MESSAGE, null, UIManager.getInstalledLookAndFeels(), UIManager.getInstalledLookAndFeels()[0]);
			if(returnedObject!=null){
				LookAndFeelInfo lookAndFeelInfo = (LookAndFeelInfo)returnedObject;
				UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
				SwingUtilities.updateComponentTreeUI(GenCrossUI.getCurrentPersoFrame());
				SwingUtilities.updateComponentTreeUI(GenCrossUI.getCurrentPersoFrame().getHistoryFrame());
			}
		}catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
