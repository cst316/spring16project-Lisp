package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;

public class TimeLog extends JPanel {

	TimeLogTable timeLogTable = new TimeLogTable();
	JButton btnAdd = new JButton();
	JTextField name = new JTextField();
	JTextField task = new JTextField();
	JTextField LOC = new JTextField();
	JTextField startTime = new JTextField(8);
	JTextField endTime = new JTextField(8);
	JScrollPane scrollPane = new JScrollPane();
	
	public TimeLog() {
	
		this.setLayout(new BorderLayout());
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)timeLogTable.getModel();
				timeLogTable.clearSelection();					
				
			}
		});
		scrollPane.setBounds(0, 33, 450, 274);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 11, 450, 26);

		
        startTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Key typed for Start Time");
				if (startTime.getText().length() > 8){
					e.consume();
				}
			}
		});
		
		Object[] row = new Object[5];
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)timeLogTable.getModel();
				
				startTime.setText("00:00 PM");
				endTime.setText("00:00 PM");
				
				row[0] = name.getText();
				row[1] = task.getText();
				row[2] = LOC.getText();
				row[3] = startTime.getText();
				row[4] = endTime.getText();
				
				model.addRow(row);
				
			}
		});
		btnAdd.setText("Add");
		btnAdd.setIcon(new ImageIcon(TimeLog.class.getResource("/net/sf/memoranda/ui/resources/icons/event_new.png")));
		toolBar.add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		DefaultTableModel model = (DefaultTableModel)timeLogTable.getModel();
        		model.removeRow(timeLogTable.getSelectedRow());
        		
        		
        	}
        });

        btnRemove.setIcon(new ImageIcon(TimeLog.class.getResource("/net/sf/memoranda/ui/resources/icons/event_remove.png")));
        toolBar.add(btnRemove);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(timeLogTable);
        this.add(toolBar, BorderLayout.NORTH);

	}	
}
