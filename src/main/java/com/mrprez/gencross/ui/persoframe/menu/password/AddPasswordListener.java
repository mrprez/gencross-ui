package com.mrprez.gencross.ui.persoframe.menu.password;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.mrprez.gencross.ui.GenCrossUI;

public class AddPasswordListener implements ActionListener {
	private JPanel panel = new JPanel();
	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JLabel confirmLabel = new JLabel("Confirmation:");
	private JPasswordField passwordField = new JPasswordField(10);
	private JPasswordField confirmField = new JPasswordField(10);
	
	
	public AddPasswordListener(){
		panel.setLayout(new GridLayout(2, 2, 5, 5));
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(confirmLabel);
		panel.add(confirmField);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int dialogResult = JOptionPane.OK_OPTION;
		String password = "";
		String confirm = "";
		while(dialogResult==JOptionPane.OK_OPTION && ("".equals(password) || !password.equals(confirm))){
			dialogResult = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), panel,"Mot de passe du MJ",
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(dialogResult==JOptionPane.OK_OPTION){
				password = new String(passwordField.getPassword());
				confirm = new String(confirmField.getPassword());
				if(!"".equals(password)){
					if(password.equals(confirm)){
						GenCrossUI.getCurrentPersonnage().setPassword(password);
						GenCrossUI.getCurrentPersoFrame().reinitMenuBar();
					}else{
						JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), "Erreur lors de la saisie du mot de passe ou de sa confirmation",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

}
