package com.mrprez.gencross.ui.comment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseWithMouseListener implements MouseListener {
	private ViewCommentFrame viewCommentFrame;
	
	
	public CloseWithMouseListener(ViewCommentFrame viewCommentFrame) {
		super();
		this.viewCommentFrame = viewCommentFrame;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		viewCommentFrame.setVisible(false);
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
