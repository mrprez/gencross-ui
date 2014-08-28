package com.mrprez.gencross.ui.persoframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class NextPhaseListener implements ActionListener {
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			if(GenCrossUI.getCurrentPersonnage().phaseFinished()){
				GenCrossUI.getCurrentPersonnage().passToNextPhase();
				GenCrossUI.getCurrentPersoFrame().reinitPointsPanel();
				GenCrossUI.getCurrentPersoFrame().reinitPhasePanel();
				GenCrossUI.getCurrentPersoFrame().reinitButtonPanel();
				GenCrossUI.getCurrentPersoFrame().reinitErrorPanel();
				GenCrossUI.getCurrentPersoFrame().reinitPropertyTree();
			}
		}catch(Exception e){
			ErrorFrame.displayError(e);
		}
	}

}
