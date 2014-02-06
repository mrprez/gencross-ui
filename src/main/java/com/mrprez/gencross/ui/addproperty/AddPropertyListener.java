package com.mrprez.gencross.ui.addproperty;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class AddPropertyListener implements ItemListener {
	private AddPropertyEditor addPropetyEditor;
	
	
	public AddPropertyListener(AddPropertyEditor addEditor) {
		super();
		this.addPropetyEditor = addEditor;
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		if(AddPropertyEditor.AUTRE.equals(addPropetyEditor.getSelectedOption())){
			addPropetyEditor.showTextField();
		}else{
			addPropetyEditor.hideTextField();
		}
	}

}
