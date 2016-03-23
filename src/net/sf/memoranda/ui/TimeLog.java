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
	JButton btnAdd = new JButton();
	
	public TimeLog() {
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 450, 274);
		add(scrollPane);

		setLayout(borderLayout1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 11, 450, 26);
		btnAdd.setText("Add");
		btnAdd.setIcon(new ImageIcon(TimeLog.class.getResource("/net/sf/memoranda/ui/resources/icons/event_new.png")));
		toolBar.add(btnAdd);
		
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(timeLogTable);
        this.add(toolBar, BorderLayout.NORTH);
        
        JButton btnRemove = new JButton("Remove");
        btnRemove.setIcon(new ImageIcon(TimeLog.class.getResource("/net/sf/memoranda/ui/resources/icons/event_remove.png")));
        toolBar.add(btnRemove);
		
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
