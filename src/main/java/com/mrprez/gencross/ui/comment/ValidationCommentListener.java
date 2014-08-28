package com.mrprez.gencross.ui.comment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.Property;

public class ValidationCommentListener implements ActionListener {
	private Property property;
	private EditCommentFrame commentFrame;
	
	
	public ValidationCommentListener(EditCommentFrame commentFrame) {
		super();
		this.commentFrame = commentFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		property.setComment(commentFrame.getText());
		commentFrame.dispose();
	}

	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	

}
