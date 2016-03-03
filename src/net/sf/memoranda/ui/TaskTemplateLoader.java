package net.sf.memoranda.ui;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;




public class TaskTemplateLoader extends JDialog implements ActionListener {
	public TaskTemplateLoader() {
		setTitle("Load Template");
		getContentPane().setLayout(null);
		
		this.setSize(new Dimension(300,200));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(34, 69, 226, 27);
		getContentPane().add(comboBox);
		
		JLabel lblTemplates = new JLabel("My Templates");
		lblTemplates.setBounds(46, 50, 174, 16);
		getContentPane().add(lblTemplates);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(33, 93, 117, 29);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(143, 93, 117, 29);
		getContentPane().add(btnCancel);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		

	}
}
