package net.sf.memoranda.ui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class TaskTemplateWizard extends JDialog {
	private JTextField textField;
	public TaskTemplateWizard() {
		setTitle("Create New Template");
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(104, 227, 134, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(42, 233, 61, 16);
		getContentPane().add(lblName);
		
		JButton btnAdd = new JButton("Add ");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAdd.setBounds(240, 226, 89, 29);
		getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(325, 226, 100, 29);
		getContentPane().add(btnRemove);
		
		TaskPanel panel_1 = new TaskPanel();
		panel_1.setBounds(205, 121, 10, 10);
		getContentPane().add(panel_1);
		
		

	JPanel panel = new JPanel();


	}
}
