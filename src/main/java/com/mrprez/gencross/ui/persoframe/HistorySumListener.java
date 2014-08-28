package com.mrprez.gencross.ui.persoframe;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.history.HistoryUtil;
import com.mrprez.gencross.ui.GenCrossUI;

public class HistorySumListener extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PropertyTree propertyTree;

	
	public HistorySumListener(PropertyTree propertyTree)
			throws HeadlessException {
		super();
		this.propertyTree = propertyTree;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getContentPane().removeAll();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)propertyTree.getSelectionPath().getLastPathComponent();
		Object selectedComponent = node.getUserObject();
		if(selectedComponent instanceof Property){
			Property selectedProperty = (Property) selectedComponent;
			Map<String, Integer> historySums = HistoryUtil.sumHistoryOfSubTree(GenCrossUI.getCurrentPersonnage().getHistory(), selectedProperty);
			setLayout(new GridLayout(historySums.size(), 2));
			for(String pointPool : historySums.keySet()){
				add(new JLabel(pointPool));
				add(new JLabel(""+historySums.get(pointPool)));
			}
			pack();
			super.setVisible(true);
		}
	}

}
