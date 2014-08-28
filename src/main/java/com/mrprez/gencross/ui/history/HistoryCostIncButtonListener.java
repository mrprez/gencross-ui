package com.mrprez.gencross.ui.history;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryCostIncButtonListener implements ActionListener {
	private int inc;
	private HistoryCostEditor historyCostEditor;
	
	
	public HistoryCostIncButtonListener(int inc,
			HistoryCostEditor historyCostEditor) {
		super();
		this.inc = inc;
		this.historyCostEditor = historyCostEditor;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		historyCostEditor.setCost(historyCostEditor.getCost()+inc);
	}

}
