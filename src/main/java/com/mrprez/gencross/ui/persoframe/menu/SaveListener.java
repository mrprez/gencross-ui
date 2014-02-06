package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class SaveListener implements ActionListener {
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			PersonnageSaver.savePersonnageGcr(GenCrossUI.getCurrentPersonnage(), GenCrossUI.getInstance().getLastSave());
			GenCrossUI.getInstance().setSaved(true);
		} catch (Exception e) {
			(new ErrorFrame(e)).setVisible(true);
		}
	}

}
