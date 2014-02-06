package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class NewListener implements ActionListener {
	private static String message = "Quel type de personnage?";
	private static String title = "Nouveau";
	

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			PersonnageFactory personnageFactory = GenCrossUI.getInstance().getPersonnageFactory();
			Collection<PluginDescriptor> availablePersonnages = personnageFactory.getPluginList();
			if(availablePersonnages.size()>0){
				if(!GenCrossUI.getInstance().isSaved()){
					int confirm = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), "Vous n'avez pas sauvegardé, voulez-vous vraiment quitter ce personnage?", "Ouvrir", JOptionPane.YES_NO_OPTION);
					if(confirm==JOptionPane.NO_OPTION){
						return;
					}
				}
				PluginDescriptor choice = (PluginDescriptor) JOptionPane.showInputDialog(GenCrossUI.getCurrentPersoFrame(), message, title, JOptionPane.QUESTION_MESSAGE, null, availablePersonnages.toArray(), null);
				if(choice!=null){
					Personnage personnage;
					personnage = GenCrossUI.getInstance().getPersonnageFactory().buildNewPersonnage(choice);
					GenCrossUI.getInstance().setPersonnage(personnage);
					GenCrossUI.getInstance().clearLastModifications();
					GenCrossUI.getInstance().setLastSave(null);
					GenCrossUI.getInstance().saveModification();
					GenCrossUI.getInstance().setMj(false);
					GenCrossUI.getCurrentPersoFrame().reinit();
				}
			}else{
				JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), "Aucun type de personnage disponible", "Aucun type de personnage disponible", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			(new ErrorFrame(e)).setVisible(true);
		}
	}


}
