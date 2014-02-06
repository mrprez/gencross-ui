package com.mrprez.gencross.ui.history;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.PoolPoint;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class HistoryCostMouseListener implements MouseListener {
	private HistoryCostEditor historyCostEditor;
	private HistoryCost historyCost;
	
	
	public HistoryCostMouseListener(HistoryCost historyCost){
		super();
		this.historyCost = historyCost;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		try{
			if(event.getClickCount()==2){
				historyCostEditor = new HistoryCostEditor(historyCost.getCost(), historyCost.getPointPool());
				historyCostEditor.ask();
				if(historyCostEditor.isValid()){
					Personnage personnage = GenCrossUI.getCurrentPersonnage();
					PoolPoint poolPoint = personnage.getPointPools().get(historyCost.getPointPool());
					if(poolPoint!=null){
						poolPoint.spend(-historyCost.getCost());
					}
					PoolPoint newPoolPoint = personnage.getPointPools().get(historyCostEditor.getPointPool());
					if(newPoolPoint!=null){
						newPoolPoint.spend(historyCostEditor.getCost());
					}
					historyCost.getHistoryItem().setCost(historyCostEditor.getCost());
					historyCost.getHistoryItem().setPointPool(newPoolPoint.getName());
					
					GenCrossUI.getCurrentPersoFrame().saveModification();
					
					GenCrossUI.getCurrentPersoFrame().reinitPointsPanel();
					GenCrossUI.getCurrentPersoFrame().getHistoryFrame().reinit();
				}
			}
		} catch (CloneNotSupportedException e) {
			ErrorFrame.displayError(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		;
	}

}
