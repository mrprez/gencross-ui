package com.mrprez.gencross.ui.history;

import com.mrprez.gencross.history.HistoryItem;

public class HistoryCost {
	private HistoryItem historyItem;
	
	
	public HistoryCost(HistoryItem historyItem) {
		super();
		this.historyItem = historyItem;
	}

	@Override
	public String toString() {
		if(historyItem.getPointPool()==null){
			return "0";
		}
		return ""+historyItem.getCost()+" "+historyItem.getPointPool();
	}
	
	public int getCost(){
		return historyItem.getCost();
	}
	public String getPointPool(){
		return historyItem.getPointPool();
	}
	public HistoryItem getHistoryItem(){
		return historyItem;
	}
	

}
