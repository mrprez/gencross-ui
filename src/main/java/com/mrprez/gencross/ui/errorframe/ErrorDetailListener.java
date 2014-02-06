package com.mrprez.gencross.ui.errorframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorDetailListener implements ActionListener {
	private ErrorFrame errorFrame;
	
	
	public ErrorDetailListener(ErrorFrame errorFrame) {
		super();
		this.errorFrame = errorFrame;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		errorFrame.hideShowDetails();
	}

}
