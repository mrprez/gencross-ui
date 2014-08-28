package com.mrprez.gencross.ui.comment;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.mrprez.gencross.Property;

public class ViewCommentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();
	
	public ViewCommentFrame(){
		super();
		setAlwaysOnTop(true);
		scrollPane.setViewportView(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		setUndecorated(true);
		setLayout(new BorderLayout());
		add(scrollPane,BorderLayout.CENTER);
		textArea.addMouseListener(new CloseWithMouseListener(this));
	}
	
	
	public void setText(String text){
		textArea.setText(text);
		if(text.length()<20){
			textArea.setColumns(20);
		}else if(text.length()<100){
			textArea.setColumns(text.length());
		}else{
			textArea.setColumns(100);
		}
		if(textArea.getLineCount()<5){
			textArea.setRows(5);
		}else{
			textArea.setRows(textArea.getLineCount());
		}
		scrollPane.setPreferredSize(textArea.getPreferredScrollableViewportSize());
	}
	
	private void display(String text){
		setText(text);
		pack();
		setVisible(true);
	}
	public void displayComment(Property property){
		display(property.getComment());
	}

}
