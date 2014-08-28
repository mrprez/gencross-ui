package com.mrprez.gencross.ui.history;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.persoframe.menu.CancelListener;

public class HistoryFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane historyView = new JScrollPane();
	private HistoryTableModel historyTableModel = new HistoryTableModel();
	private JTable historyTable = new JTable(historyTableModel);
	private static final String onCtrlZ = "onCtrlZ";
	
	
	public HistoryFrame(){
		super("Gen-Cross - Historique");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_DOWN_MASK), onCtrlZ);
		getRootPane().getActionMap().put(onCtrlZ,new CancelListener());
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(historyView)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(historyView)
			.addContainerGap()
		);
		pack();
		
		historyTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		historyTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		historyTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		historyTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		historyTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		historyView.setPreferredSize(historyTable.getPreferredSize());
		historyView.setViewportView(historyTable);
		historyTable.setDefaultEditor(HistoryCost.class,new HistoryCostTableCellEditor());
		
	}

	public void reinit(){
		historyTableModel.setHistory(GenCrossUI.getCurrentPersonnage().getHistory());
		if(historyTable.getCellEditor()!=null){
			historyTable.getCellEditor().cancelCellEditing();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	public JTable getHistoryTable() {
		return historyTable;
	}
	
	

}
