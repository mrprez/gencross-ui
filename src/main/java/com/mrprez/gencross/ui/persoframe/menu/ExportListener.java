package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.ui.export.ExportDialog;
import com.mrprez.gencross.ui.persoframe.PersoFrame;

public class ExportListener implements ActionListener {
	private ExportDialog exportFrame;
	
	
	public ExportListener(PersoFrame persoFrame) {
		super();
		exportFrame = new ExportDialog(persoFrame);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		exportFrame.setVisible(true);
	}

}
