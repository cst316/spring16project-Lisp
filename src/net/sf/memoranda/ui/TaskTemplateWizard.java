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
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.border.MatteBorder;
import net.sf.memoranda.Template;

public class TaskTemplateWizard extends JDialog implements ActionListener{
	
	//TaskPanel Operations
	TaskTable tasktab;
	DailyItemsPanel dipan;

	// RIGHT CLICK MENU 
	private JMenuItem add;
	private JMenuItem remove;
	private JMenuItem rename;
	
	// TREE
	private JTree tree;
	
	// INPUT BOXES
	private JTextField task_name;
	private JTextField description;
	private JTextField start_date;
	private JTextField end_date;
	private JTextField est_effort;
	private JComboBox priority;
	private JTextField progress;
	
	public TaskTemplateWizard() {
		
		setRootPaneCheckingEnabled(false);
		setLocation(new Point(300, 300));
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setForeground(Color.DARK_GRAY);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(false);
		setTitle("Create New Template");
		getContentPane().setLayout(null);
		
		JPanel task_panel = new JPanel();
		task_panel.setBounds(6, 0, 438, 215);
		getContentPane().add(task_panel);
		
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new Template("JTree") {
				{

				}
			}
		));
		tree.setToolTipText("Right click to view options");
		
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
			    
			    if (SwingUtilities.isLeftMouseButton(e)) {
			    	


			        int row = tree.getClosestRowForLocation(e.getX(), e.getY());
			        tree.setSelectionRow(row);

			        
			        // Gets the text from the selected node
			        Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
			        String selectedText = selected.getUserObject().toString();
			        
			        // Populates text boxes
			        task_name.setText(selectedText);
			        description.setText(selected.getTaskDescription());
			        est_effort.setText(new Integer((int)selected.getEffort()).toString());
			        progress.setText(new Integer(selected.getProgress()).toString());
			        priority.setSelectedItem(selected.getPriority());
			        
			    }	
			}
		});
		
		
		task_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		task_panel.add(tree);
		
		JPanel button_panel = new JPanel();
		button_panel.setBounds(6, 465, 438, 29);
		getContentPane().add(button_panel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 6, 106, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		button_panel.setLayout(null);
		button_panel.add(btnSave);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(225, 6, 106, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Test");
				ok();
			}
		});
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(107, 6, 106, 23);
		button_panel.add(btnLoad);
		button_panel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(332, 6, 106, 23);
		button_panel.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(6, 219, 438, 243);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(26, 6, 70, 16);
		panel.add(lblTaskName);
		
		task_name = new JTextField();
		task_name.setBounds(36, 21, 375, 28);
		task_name.setName("");
		panel.add(task_name);
		task_name.setColumns(20);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(26, 50, 80, 16);
		panel.add(lblDescription);
		
		description = new JTextField();
		description.setName("");
		description.setColumns(20);
		description.setBounds(36, 65, 375, 67);
		panel.add(description);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(6, 144, 70, 16);
		panel.add(lblStartDate);
		
		start_date = new JTextField();
		start_date.setName("");
		start_date.setColumns(20);
		start_date.setBounds(77, 138, 95, 28);
		panel.add(start_date);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(266, 144, 70, 16);
		panel.add(lblEndDate);
		
		end_date = new JTextField();
		end_date.setName("");
		end_date.setColumns(20);
		end_date.setBounds(337, 138, 95, 28);
		panel.add(end_date);
		
		JLabel lblEstEff = new JLabel("Est Effort (hrs)");
		lblEstEff.setBounds(6, 178, 90, 16);
		panel.add(lblEstEff);
		
		est_effort = new JTextField();
		est_effort.setName("");
		est_effort.setColumns(20);
		est_effort.setBounds(102, 172, 70, 28);
		panel.add(est_effort);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(266, 172, 90, 16);
		panel.add(lblPriority);
		
		priority = new JComboBox();
		priority.setModel(new DefaultComboBoxModel(new String[] {"Low", "Lowest", "Normal", "High", "Highest"}));
		priority.setName("");
		priority.setBounds(337, 166, 95, 28);
		panel.add(priority);
		
		progress = new JTextField();
		progress.setName("");
		progress.setColumns(20);
		progress.setBounds(77, 206, 95, 28);
		panel.add(progress);
		
		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(6, 212, 70, 16);
		panel.add(lblProgress);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				Template root = (Template) model.getRoot();
				
				// Initializes Template object
				selected.setEffort(Integer.parseInt(est_effort.getText().toString()));
				selected.setHeadTaskTitle(task_name.getText().toString());
				selected.setUserObject(task_name.getText().toString());
				selected.setPriority(priority.getSelectedItem().toString());
				selected.setTaskDescription(description.getText().toString());
				selected.setProgress(Integer.parseInt(progress.getText().toString()));
				
				tree.revalidate();
							
				
			}
		});
		btnUpdate.setBounds(241, 207, 95, 29);
		panel.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(327, 207, 105, 29);
		panel.add(btnReset);
		
		// RESET (WORKING)
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println((JButton)e.getSource() + "selected.");
				reset();
				
			}
		});
		
		// OK - POPULATE TREE/CLOSE (KIND OF WORKING)
		// TO-DO: Instantiate Template class by iterating over tree
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println((JButton)e.getSource() + "selected.");
				ok();

				
			}
		});
		
		// SAVE (NOT WORKING)
		// TO-DO: Need to somehow populate a Template class by iterating over tree
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println((JButton)e.getSource() + "selected.");

				
			}
		});
		
		//WORKING -- Load the tree from the template screen to the task list.
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadIn(); //test
			}
		});
		

	}
	
	//TaskTable Getters and Setters.
	public TaskTable getTaskTable() {
		return tasktab;
	}

	public void setTaskTable(TaskTable tasktable) {
		this.tasktab = tasktable;
	}
	
	//Daily Items Panel setters and getters
	public DailyItemsPanel getDipan() {
		return dipan;
	}

	public void setDipan(DailyItemsPanel dipan) {
		this.dipan = dipan;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Template root = (Template) model.getRoot();
		
		// ADD (WORKING)
		JMenuItem item = (JMenuItem) e.getSource();
		if (item.equals(add)){
			System.out.println("Add selected");

			Template subtask = new Template("Sub Task");
			model.insertNodeInto(subtask, root, root.getChildCount());
			root.addSubtask(subtask);
			tree.revalidate();
			model.reload(root);
		}
		
		// REMOVE (WORKING)
		else if (item.equals(remove)){
			model.removeNodeFromParent(selected);
			root.removeSubtask(selected);
			tree.revalidate();
			
			System.out.println("Remove selected");
			
		}
		
		// RENAME (WORKING)
		else if (item.equals(rename)){
			System.out.println("Rename selected");
			tree.startEditingAtPath(tree.getSelectionPath());
			String selectedText = selected.getUserObject().toString();
			selected.setHeadTaskTitle(selectedText); // Changed this to selected, should be root maybe? idk
			tree.revalidate();
		}

		
	}
	
	public void reset(){
		
		Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
        task_name.setText("");
        description.setText("");
        est_effort.setText("");
        progress.setText("");
        priority.setSelectedItem("Normal");
		
		
	}
	
	public void loadIn() {
		System.out.println("load in called!!!!!!!");
		DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		if(root == null) {
			
		}
		//load the tree from the template wizard to the task panel
		Template troot = (Template) model.getRoot();
		System.out.println(troot.getTaskName());
		troot.setDip(dipan);
		troot.setTt(tasktab);
		troot.loadTemplate();
		this.dispose();
	}
	
	
	public void ok(){
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		if(root == null) {
			
		}
		dispose();	
	}

	
	public void save(){
		
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Template root = (Template) model.getRoot();
		
		// This might be how we save it
		// Export template.getName, getPriority, etc.
		Template template = root;
		
	}
}
