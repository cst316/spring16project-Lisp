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
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Point;

public class TaskTemplateWizard extends JDialog {
	public TaskTemplateWizard() {
		setLocation(new Point(300, 300));
		setPreferredSize(new Dimension(500, 200));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaskTemplateWizard.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setForeground(Color.DARK_GRAY);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Create New Template");
		getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Save");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnAdd.setBounds(83, 227, 89, 29);
		getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Done");
		btnRemove.setBounds(168, 227, 100, 29);
		getContentPane().add(btnRemove);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 23, 376, 192);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.print("Tree clicked");
				
			}
		});
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Task") {
				{

				}
			}
		));
		tree.setBounds(6, 6, 364, 180);
		panel_1.add(tree);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(264, 227, 117, 29);
		getContentPane().add(btnReset);
		
		

	


	}
}
