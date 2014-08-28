package com.mrprez.gencross.ui.comment;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import com.mrprez.gencross.Property;

public class EditCommentFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();
	private JButton validationButton = new JButton("Valider");
	private ValidationCommentListener validListener = new ValidationCommentListener(this);
	
	
	public EditCommentFrame(){
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		scrollPane.setViewportView(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		validationButton.addActionListener(validListener);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(scrollPane)
				.addComponent(validationButton)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scrollPane)
			.addGap(5)
			.addComponent(validationButton)
			.addContainerGap()
		);
	}

	public void setText(String text){
		if(text==null){
			text = "";
		}
		textArea.setText(text);
		if(text.length()<20){
			textArea.setColumns(20);
		}else if(text.length()<100){
			textArea.setColumns(text.length());
		}else{
			textArea.setColumns(100);
		}
		if(textArea.getLineCount()<5 && textArea.isEditable()){
			textArea.setRows(5);
		}else{
			textArea.setRows(textArea.getLineCount());
		}
		scrollPane.setPreferredSize(textArea.getPreferredScrollableViewportSize());
	}
	
	
	private void edit(String text){
		setText(text);
		pack();
		setVisible(true);
	}
	public void editComment(Property property){
		validListener.setProperty(property);
		edit(property.getComment());
	}
	
	public String getText(){
		return textArea.getText();
	}
}
