package net.sf.memoranda.ui;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimeLog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public TimeLog() {
		setTitle("Time Log");
		getContentPane().setLayout(null);
		
		JTree tree = new JTree();
		tree.setBounds(10, 11, 206, 240);
		getContentPane().add(tree);
		
		textField = new JTextField();
		textField.setBounds(286, 11, 138, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(226, 14, 70, 14);
		getContentPane().add(lblTaskName);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(226, 44, 70, 14);
		getContentPane().add(lblStartTime);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(286, 41, 138, 19);
		getContentPane().add(textField_1);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(226, 74, 70, 14);
		getContentPane().add(lblEndTime);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(286, 71, 138, 19);
		getContentPane().add(textField_2);
		
		JLabel lblTotalTime = new JLabel("LOC");
		lblTotalTime.setBounds(226, 102, 70, 14);
		getContentPane().add(lblTotalTime);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(286, 99, 138, 19);
		getContentPane().add(textField_3);
		
		JLabel lblTotalTime_1 = new JLabel("Total Time");
		lblTotalTime_1.setBounds(226, 130, 70, 14);
		getContentPane().add(lblTotalTime_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(286, 127, 138, 19);
		getContentPane().add(textField_4);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(226, 157, 89, 23);
		getContentPane().add(btnAdd);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setBounds(335, 157, 89, 23);
		getContentPane().add(btnReset);
	}
}
