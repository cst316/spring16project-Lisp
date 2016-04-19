package net.sf.memoranda.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TimeLogTable extends JTable {
	
	public TimeLogTable(){
	
		Object[] columns = {"Name", "Task", "LOC", "Start Time", "End Time"};
		
		this.setRowHeight(30);
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		this.setModel(model);
		
	}


}
