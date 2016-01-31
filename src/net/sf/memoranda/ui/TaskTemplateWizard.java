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
import java.awt.FlowLayout;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class TaskTemplateWizard extends JDialog implements ActionListener{
	
	private JMenuItem add;
	private JMenuItem remove;
	private JMenuItem rename;
	private JTree tree;
	
	public TaskTemplateWizard() {
		
		setLocation(new Point(300, 300));
		setPreferredSize(new Dimension(500, 200));
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setForeground(Color.DARK_GRAY);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Create New Template");
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 0, 438, 215);
		getContentPane().add(panel_1);
		
		tree = new JTree();
		tree.setEditable(true);
	
		
	    //**********// MENU //************// 
		
		JPopupMenu menu = new JPopupMenu();
		
		add = new JMenuItem("Add Task");
		remove = new JMenuItem("Remove Task");
		rename = new JMenuItem("Rename");
		
		add.addActionListener(this);
		remove.addActionListener(this);
		rename.addActionListener(this);
		
		menu.add(add);
		menu.add(remove);
		menu.add(rename);
		
		//******************************//
		
	
		tree.setPreferredSize(new Dimension(430, 210));
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			    if (SwingUtilities.isRightMouseButton(e)) {
			    	
			    	System.out.println("Right click detected.");

			        int row = tree.getClosestRowForLocation(e.getX(), e.getY());
			        tree.setSelectionRow(row);
			        menu.show(e.getComponent(), e.getX(), e.getY());

			    }
				

				
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(tree);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 227, 438, 51);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JButton btnDone = new JButton("Done");
		panel.add(btnDone);
		
		JButton btnReset = new JButton("Reset");
		panel.add(btnReset);
		
		// DONE (KIND OF WORKING)
		// TO-DO: Instantiate Template class by iterating over tree
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				System.out.println((JButton)e.getSource() + "selected.");
				dispose();

				
			}
		});
		
		// RESET (WORKING)
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				System.out.println((JButton)e.getSource() + "selected.");
				root.removeAllChildren();
				model.reload(root);
				
			}
		});
		
		// SAVE (NOT WORKING)
		// TO-DO: Need to somehow populate a Template class by iterating over tree
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				System.out.println((JButton)e.getSource() + "selected.");

				
			}
		});
		

		

	


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		
		// ADD (WORKING)
		JMenuItem item = (JMenuItem) e.getSource();
		if (item.equals(add)){
			System.out.println("Add selected");

			model.insertNodeInto(new DefaultMutableTreeNode("Sub Task"), root, root.getChildCount());
			tree.revalidate();
			model.reload(root);
		}
		
		// REMOVE (WORKING)
		else if (item.equals(remove)){
			model.removeNodeFromParent(selected);
			System.out.println("Remove selected");
		}
		
		// RENAME (WORKING)
		else if (item.equals(rename)){
			System.out.println("Rename selected");
			tree.startEditingAtPath(tree.getSelectionPath());
		}

		
	}
}
