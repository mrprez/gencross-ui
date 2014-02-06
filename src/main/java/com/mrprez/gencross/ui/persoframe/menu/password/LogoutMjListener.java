package com.mrprez.gencross.ui.persoframe.menu.password;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.mrprez.gencross.ui.GenCrossUI;

public class LogoutMjListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GenCrossUI.getInstance().setMj(false);
		GenCrossUI.getCurrentPersoFrame().reinitMenuBar();
		JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), "Vous êtes deconnecté du compte de MJ.");
	}

}
