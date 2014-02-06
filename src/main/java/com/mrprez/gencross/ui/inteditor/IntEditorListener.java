package com.mrprez.gencross.ui.inteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mrprez.gencross.value.Value;

public class IntEditorListener implements ActionListener {
	private IntEditor intEditor;
	private int inc;

	
	public IntEditorListener(IntEditor intEditor, int inc) {
		super();
		this.intEditor = intEditor;
		this.inc = inc;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Value value = intEditor.getValue();
		if(inc>0){
			value.increase();
		}else{
			value.decrease();
		}
		intEditor.setValue(value);
	}

}
