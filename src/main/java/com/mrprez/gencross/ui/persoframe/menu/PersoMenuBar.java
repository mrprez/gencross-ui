package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.persoframe.PersoFrame;
import com.mrprez.gencross.ui.persoframe.menu.password.AddPasswordListener;
import com.mrprez.gencross.ui.persoframe.menu.password.LoginMjListener;
import com.mrprez.gencross.ui.persoframe.menu.password.LogoutMjListener;

public class PersoMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenuItem newMenuItem = new JMenuItem("Nouveau");
	private JMenuItem openMenuItem = new JMenuItem("Ouvrir");
	private JMenuItem saveMenuItem = new JMenuItem("Sauvegarder");
	private JMenuItem saveAsMenuItem = new JMenuItem("Sauvegarder sous...");
	private JMenuItem exportMenuItem = new JMenuItem("Exporter");
	private JMenuItem cancelMenuItem = new JMenuItem("Annuler");
	private JMenuItem historyMenuItem = new JMenuItem("Historique");
	private JMenuItem lookAndFeelMenuItem = new JMenuItem("Apparence");
	private JMenuItem loadTypeMenuItem = new JMenuItem("Charger un nouveau type");
	private JMenuItem addPasswordMenuItem = new JMenuItem("Ajouter un mot de passe");
	private JMenuItem loginMjMenuItem = new JMenuItem("Connection MJ");
	private JMenuItem logoutMjMenuItem = new JMenuItem("Deconnection MJ");
	private JMenuItem helpFileMenuItem = new JMenuItem("Fichier d'aide");
	private JMenu fileMenu = new JMenu("Fichier");
	private JMenu editMenu = new JMenu("Edition");
	private JMenu affichageMenu = new JMenu("Affichage");
	private JMenu outilMenu = new JMenu("Outil");
	private JMenu aideMenu = new JMenu("Aide");
	
	
	public PersoMenuBar(PersoFrame persoFrame){
		// Menu Fichier
		add(fileMenu);
		addMenuItem(fileMenu,newMenuItem,new NewListener(),true);
		addMenuItem(fileMenu,openMenuItem,new OpenListener(),true);
		addMenuItem(fileMenu,saveMenuItem,new SaveListener(),false);
		addMenuItem(fileMenu,saveAsMenuItem,new SaveAsListener(),false);
		addMenuItem(fileMenu,exportMenuItem,new ExportListener(persoFrame),false);
		
		// Menu Edition
		add(editMenu);
		addMenuItem(editMenu,cancelMenuItem,new CancelListener(),true);
		
		// Menu Affichage
		add(affichageMenu);
		addMenuItem(affichageMenu,historyMenuItem,new OpenHistoryListener(),true);
		addMenuItem(affichageMenu,lookAndFeelMenuItem, new LookAndFeelListener(), true);
		
		// Menu Outils
		add(outilMenu);
		addMenuItem(outilMenu,loadTypeMenuItem,new LoadTypeListener(),true);
		outilMenu.addSeparator();
		addMenuItem(outilMenu, addPasswordMenuItem, new AddPasswordListener(), false);
		addMenuItem(outilMenu, loginMjMenuItem, new LoginMjListener(), false);
		addMenuItem(outilMenu, logoutMjMenuItem, new LogoutMjListener(), false);
		
		// Menau Aide
		add(aideMenu);
		addMenuItem(aideMenu, helpFileMenuItem, new HelpFileListener(), false);
		
	}
	
	private void addMenuItem(JMenu menu, JMenuItem item, ActionListener listener, boolean enabled){
		item.addActionListener(listener);
		item.setEnabled(enabled);
		menu.add(item);
	}
	
	public void reinit(){
		Personnage personnage = GenCrossUI.getCurrentPersonnage();
		saveAsMenuItem.setEnabled(personnage!=null);
		exportMenuItem.setEnabled(personnage!=null);
		cancelMenuItem.setEnabled(GenCrossUI.getInstance().isCancelAvailable());
		saveMenuItem.setEnabled(GenCrossUI.getInstance().getLastSave()!=null);
		addPasswordMenuItem.setEnabled(personnage!=null && personnage.getPassword()==null);
		loginMjMenuItem.setEnabled(personnage!=null && personnage.getPassword()!=null && !GenCrossUI.getInstance().isMj());
		logoutMjMenuItem.setEnabled(GenCrossUI.getInstance().isMj());
		helpFileMenuItem.setEnabled(personnage!=null && personnage.hasHelpFile());
	}
	
	public JMenu getAffichageMenu(){
		return affichageMenu;
	}

}
