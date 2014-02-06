package com.mrprez.gencross.ui.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mrprez.gencross.history.HistoryItem;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.value.Value;

public class HistoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<HistoryItem> history = new ArrayList<HistoryItem>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	
	
	public HistoryTableModel(){
		super();
	}
	
	public HistoryTableModel(List<HistoryItem> history) {
		super();
		this.history = history;
	}
	
	public void setHistory(List<HistoryItem> history) {
		this.history = history;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return history.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		int index = history.size()-rowIndex-1;
		if(columnIndex==0){
			return history.get(index).getAbsoluteName().replace('#', '/');
		}else if(columnIndex==1){
			return history.get(index).getOldValue();
		}else if(columnIndex==2){
			return history.get(index).getNewValue();
		}else if(columnIndex==3){
			return new HistoryCost(history.get(index));
		}else if(columnIndex==4){
			return dateFormat.format(history.get(index).getDate());
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		if(column==0){
			return "Nom";
		}else if(column==1){
			return "Ancien";
		}else if(column==2){
			return "Nouveau";
		}else if(column==3){
			return "Coût";
		}else if(column==4){
			return "Date";
		}
		return super.getColumnName(column);
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if(column==0){
			return String.class;
		}else if(column==1){
			return Value.class;
		}else if(column==2){
			return Value.class;
		}else if(column==3){
			return HistoryCost.class;
		}else if(column==4){
			return String.class;
		}
		return super.getColumnClass(column);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return GenCrossUI.getInstance().isMj() && columnIndex==3;
	}
	
	
	
	

}
