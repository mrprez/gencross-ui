package com.mrprez.gencross.ui.persoframe;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.mrprez.gencross.PoolPoint;
import com.mrprez.gencross.ui.GenCrossUI;

public class EditPointPoolListener implements MouseListener {
	private Component parentComponent;
	private PoolPoint poolPoint;
	
	
	public EditPointPoolListener(Component parentComponent, PoolPoint poolPoint) {
		super();
		this.parentComponent = parentComponent;
		this.poolPoint = poolPoint;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		try{
			String addedPointsString = JOptionPane.showInputDialog(parentComponent, "Ajouter des points", poolPoint.getName(), JOptionPane.PLAIN_MESSAGE);
			if(addedPointsString!=null){
				int addedPoints = Integer.parseInt(addedPointsString);
				poolPoint.add(addedPoints);
				GenCrossUI.getCurrentPersoFrame().reinitPointsPanel();
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(parentComponent, "Vous n'avez pas entré un chiffre valide.","Erreur", JOptionPane.ERROR_MESSAGE);
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
