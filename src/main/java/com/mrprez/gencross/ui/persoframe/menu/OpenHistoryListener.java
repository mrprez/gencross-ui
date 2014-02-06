package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.ui.GenCrossUI;

public class OpenHistoryListener implements ActionListener {
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		int x = GenCrossUI.getCurrentPersoFrame().getX();
		int y = GenCrossUI.getCurrentPersoFrame().getY();
		int height = GenCrossUI.getCurrentPersoFrame().getHeight();
		int width = GenCrossUI.getCurrentPersoFrame().getWidth();
		x = x + width;
		GenCrossUI.getCurrentPersoFrame().getHistoryFrame().setBounds(x, y, width, height);
		
		GenCrossUI.getCurrentPersoFrame().getHistoryFrame().setVisible(true);
	}

}
