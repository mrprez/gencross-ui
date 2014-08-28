package com.mrprez.gencross.ui.history;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class HistoryCostTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 1L;
	private JLabel label;

	public HistoryCostTableCellEditor(){
		super();
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		label = new JLabel(value.toString());
		label.addMouseListener(new HistoryCostMouseListener((HistoryCost) value));
		return label;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}
	
	

	

}
