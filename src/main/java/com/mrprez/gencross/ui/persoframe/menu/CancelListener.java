package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class CancelListener extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			GenCrossUI.getInstance().cancel();
		} catch (CloneNotSupportedException e) {
			(new ErrorFrame(e)).setVisible(true);
		}
	}

}
