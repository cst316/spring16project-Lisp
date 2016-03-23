package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class TimeLog extends JPanel {
	private JTable table;
	BorderLayout borderLayout1 = new BorderLayout();
	TimeLogTable timeLogTable = new TimeLogTable();
	public TimeLog() {
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 450, 274);
		add(scrollPane);
		
		
		Object[] columns = {"Name", "Task", "LOC", "Start Time", "End Time"};
		Object[] row = new Object[5];
		
		table = new JTable();
		table.setRowHeight(30);
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		setLayout(borderLayout1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 11, 450, 26);
		
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(timeLogTable, null);
        this.add(toolBar, BorderLayout.NORTH);
		
		JTextField name = new JTextField();
		JTextField task = new JTextField();
		JTextField LOC = new JTextField();
		JTextField startTime = new JTextField();
		JTextField endTime = new JTextField();
		
		/* row[0] = name;
		row[1] = task;
		row[2] = LOC;
		row[3] = startTime;
		row[4] = endTime;
		
		*/
		
		//model.addRow(row);

		
		

	}
}
