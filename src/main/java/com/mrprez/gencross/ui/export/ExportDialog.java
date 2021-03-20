package com.mrprez.gencross.ui.export;

import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mrprez.gencross.export.FileGenerator;

public class ExportDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel generatorLabel = new JLabel("Type d'exportation:");
	private JComboBox<String> generatorList = new JComboBox<String>(FileGenerator.getGeneratorList().keySet().toArray(new String[0]));
	private JLabel templateLabel = new JLabel("Séléctionnez un template:");
	private JTextField templateTextField = new JTextField(30);
	private JButton selectTemplateButton = new JButton("Parcourir");
	private JLabel outputLabel = new JLabel("Fichier de Sortie");
	private JTextField outputTextField = new JTextField(30);
	private JButton selectOutputButton = new JButton("Parcourir");
	private JButton validationButton = new JButton("Valider");
	
	
	public ExportDialog(Frame parentFrame){
		super(parentFrame,"Export", true);
		
		templateTextField.setBorder(BorderFactory.createLoweredBevelBorder());
		outputTextField.setBorder(BorderFactory.createLoweredBevelBorder());
		selectTemplateButton.addActionListener(new BrowseTemplateListener(this));
		selectOutputButton.addActionListener(new BrowseOutputListener(this));
		validationButton.addActionListener(new ValidationListener(this));
		generatorList.addActionListener(new SelectFileGeneratorListener(selectTemplateButton, templateTextField));
		generatorList.setSelectedIndex(0);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(generatorLabel)
				.addComponent(generatorList)
				.addComponent(templateLabel)
				.addGroup(layout.createSequentialGroup()
					.addComponent(templateTextField)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(selectTemplateButton)
				)
				.addComponent(outputLabel)
				.addGroup(layout.createSequentialGroup()
					.addComponent(outputTextField)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(selectOutputButton)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(validationButton)
				)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(generatorLabel)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(generatorList)
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addComponent(templateLabel)
			.addGroup(layout.createParallelGroup()
				.addComponent(templateTextField)
				.addComponent(selectTemplateButton)
			)
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addComponent(outputLabel)
			.addGroup(layout.createParallelGroup()
				.addComponent(outputTextField)
				.addComponent(selectOutputButton)
			)
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(validationButton)
			)
			.addContainerGap()
		);
		pack();
	}
	
	public String getTemplatePath(){
		return templateTextField.getText();
	}
	
	public String getOutputPath(){
		return outputTextField.getText();
	}
	
	public Class<? extends FileGenerator> getGeneratorClass(){
		return FileGenerator.getGeneratorList().get(generatorList.getSelectedItem());
	}
	
	public void setTemplatePath(String templatePath){
		templateTextField.setText(templatePath);
	}
	
	public void setOutputPath(String outputPath){
		outputTextField.setText(outputPath);
	}
	

}
