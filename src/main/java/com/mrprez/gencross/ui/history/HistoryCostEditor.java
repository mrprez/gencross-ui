package com.mrprez.gencross.ui.history;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mrprez.gencross.ui.GenCrossUI;

public class HistoryCostEditor {
	private JButton minusButton = new JButton("-");
	private JButton addButton = new JButton("+");
	private JLabel costLabel = new JLabel("0");
	private Integer cost = 0;
	private JComboBox<String> comboBox = new JComboBox<>();
	private int selectedOption;
	private JPanel panel = new JPanel();
	
	
	public HistoryCostEditor(int cost, String pointPoolName){
		super();
		this.cost = cost;
		costLabel.setText(""+cost);
		comboBox = new JComboBox<String>(GenCrossUI.getCurrentPersonnage().getPointPools().keySet().toArray(new String[0]));
		comboBox.setSelectedItem(pointPoolName);
		minusButton.addActionListener(new HistoryCostIncButtonListener(-1, this));
		addButton.addActionListener(new HistoryCostIncButtonListener(1, this));
		
		panel.setLayout(new FlowLayout());
		panel.add(minusButton);
		panel.add(costLabel);
		panel.add(addButton);
		panel.add(comboBox);
		
	}

	public Integer getCost() {
		return cost;
	}
	public void setCost(int cost){
		this.cost = cost;
		costLabel.setText(""+cost);
	}
	
	public String getPointPool(){
		if(comboBox.getSelectedItem()==null){
			return null;
		}
		return comboBox.getSelectedItem().toString();
	}
	
	public void ask(){
		HistoryFrame historyFrame = GenCrossUI.getInstance().getPersoFrame().getHistoryFrame();
		selectedOption = JOptionPane.showConfirmDialog(historyFrame, panel, "Modification d'historique", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
	
	public boolean isValid(){
		return selectedOption==JOptionPane.OK_OPTION;
	}
	

}
