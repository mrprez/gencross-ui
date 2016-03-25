package com.mrprez.gencross.ui.persoframe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.tree.TreePath;


public class PopupListener implements MouseListener {
	private JPopupMenu popupMenu;

	public PopupListener(JPopupMenu popupMenu) {
		super();
		this.popupMenu = popupMenu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		;
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
	public void mousePressed(MouseEvent e) {
		PropertyTree propertyTree = (PropertyTree) e.getComponent();
		TreePath treePath = propertyTree.getClosestPathForLocation(e.getX(), e.getY());
		propertyTree.setSelectionPath(treePath);
		maybeShowPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		maybeShowPopup(e);
	}

	private void maybeShowPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

}
