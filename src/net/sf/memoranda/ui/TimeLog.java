package net.sf.memoranda.ui;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.simple.parser.ParseException;

import net.sf.memoranda.TimeLogEntry;
import net.sf.memoranda.TimeLogJson;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		
		try {
			populatePanel();
		} catch (IOException | ParseException e2) {
			e2.printStackTrace();
		}
		
		this.setLayout(new BorderLayout());
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)timeLogTable.getModel();
				timeLogTable.clearSelection();					
				
			}
		});
		
		// Check for change values
		timeLogTable.getModel().addTableModelListener(new TableModelListener() {

	      public void tableChanged(TableModelEvent e) {
	        
	    	 int column = e.getColumn();
	    	 int row = e.getFirstRow();
	    	 
	         if(column >= 0){	  
	        	try {
					TimeLogJson json = new TimeLogJson("timeLog.json");
					String value = timeLogTable.getValueAt(row, column).toString();
					json.editCell(column, row, value);
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
	         }
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
				
				// Add to the json array
				TimeLogJson json;
				try {
					json = new TimeLogJson("timeLog.json");
					json.addLog();
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}		
				
			}
		});
		btnAdd.setText("Add");
		btnAdd.setIcon(new ImageIcon(TimeLog.class.getResource("/net/sf/memoranda/ui/resources/icons/event_new.png")));
		toolBar.add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Component par = timeLogTable.getComponentPopupMenu();
        		if(timeLogTable.getSelectedRow() == -1) {
        			String boxTitle = "Notification!";
        			String msg = "No log event selected, Please try again.";
        			new NotificationPopUp(
        					par, boxTitle, msg);
        		} else {
        		
        			String boxTitle = "WARNING";
        			String msg = "Are you sure you want to delete this?";
        			DecisionBox db = new DecisionBox(par, boxTitle, msg);
        			if(db.getDecision() == true) {
        				DefaultTableModel model = (DefaultTableModel)timeLogTable.getModel();
        		
        				// Remove log from the json file
        				try {
        					TimeLogJson json = new TimeLogJson("timeLog.json");
        					json.deleteCell(timeLogTable.getSelectedRow());
        				} catch (IOException | ParseException e1) {
        					e1.printStackTrace();
        				}
        		
        				model.removeRow(timeLogTable.getSelectedRow());
        			}
        		}
        	}
        });

        btnRemove.setIcon(new ImageIcon(TimeLog.class.getResource("/net/sf/memoranda/ui/resources/icons/event_remove.png")));
        toolBar.add(btnRemove);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(timeLogTable);
        this.add(toolBar, BorderLayout.NORTH);

	}


	private void populatePanel() throws FileNotFoundException, IOException, ParseException {
	
		TimeLogJson json = new TimeLogJson("TimeLog.json");
		
		for(int i = 0; i < json.size(); i++){
			
			Object[] row = new Object[5];
			
			DefaultTableModel model = (DefaultTableModel)timeLogTable.getModel();
			
			row[0] = json.getElement(i, "name");
			row[1] = json.getElement(i, "task");
			row[2] = json.getElement(i, "LOC");
			row[3] = json.getElement(i, "startTime");
			row[4] = json.getElement(i, "endTime");
			
			model.addRow(row);
		}
	}	
}