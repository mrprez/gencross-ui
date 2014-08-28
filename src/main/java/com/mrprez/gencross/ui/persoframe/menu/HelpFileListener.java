package com.mrprez.gencross.ui.persoframe.menu;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;

public class HelpFileListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		PluginDescriptor pluginDescriptor = GenCrossUI.getInstance().getPersonnage().getPluginDescriptor();
		File helpFile = new File(GenCrossUI.getInstance().getTempDirectory(), pluginDescriptor.getHelpFileName());
		if(helpFile.exists()) {
			helpFile.delete();
		}
		
		BufferedOutputStream os = null;
		BufferedInputStream is = null;
		try{
			os = new BufferedOutputStream(new FileOutputStream(helpFile));
			is = new BufferedInputStream(GenCrossUI.getInstance().getPersonnage().getHelpFileInputStream());

			int i;
			while((i=is.read())>=0) {
				os.write(i);
			}
			Desktop.getDesktop().open(helpFile);
		} catch (IOException e) {
			ErrorFrame.displayError(e);
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					ErrorFrame.displayError(e);
				}
			}
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					ErrorFrame.displayError(e);
				}
			}
		}
		
	}

}
