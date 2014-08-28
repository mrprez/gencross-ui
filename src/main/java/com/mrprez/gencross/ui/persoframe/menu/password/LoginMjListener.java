package com.mrprez.gencross.ui.persoframe.menu.password;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.mrprez.gencross.ui.GenCrossUI;

public class LoginMjListener implements ActionListener {
	private JPanel panel = new JPanel();
	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JPasswordField passwordField = new JPasswordField(10);
	
	
	public LoginMjListener(){
		panel.setLayout(new GridLayout(1, 2, 5, 5));
		panel.add(passwordLabel);
		panel.add(passwordField);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int dialogResult = JOptionPane.OK_OPTION;
		while(dialogResult==JOptionPane.OK_OPTION && !GenCrossUI.getInstance().isMj()){
			dialogResult = JOptionPane.showConfirmDialog(GenCrossUI.getCurrentPersoFrame(), panel,"Mot de passe du MJ",
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(dialogResult==JOptionPane.OK_OPTION){
				String password = new String(passwordField.getPassword());
				if(password.equals(GenCrossUI.getCurrentPersonnage().getPassword())){
					GenCrossUI.getInstance().setMj(true);
					GenCrossUI.getCurrentPersoFrame().reinitMenuBar();
				}else{
					JOptionPane.showMessageDialog(GenCrossUI.getCurrentPersoFrame(), "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
