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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;

import java.awt.ComponentOrientation;
import java.awt.Component;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.border.MatteBorder;






import org.json.simple.parser.ParseException;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.TaskJson;
import net.sf.memoranda.Template;
import net.sf.memoranda.date.CalendarDate;

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
	
	private JLabel lblEndDate;
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
	private JButton btnStartDate;
	private JButton btnEndDate;
	private CalendarFrame startCalFrame;
	private CalendarFrame endCalFrame;
	private JCheckBox chkEndDate;
	
	// INPUT BOXES
	private JTextField task_name;
	private JTextField description;
	private JSpinner startDate;
	private JSpinner endDate;
	private JTextField est_effort;
	private JComboBox priority;
	private JTextField progress;
	
	public TaskTemplateWizard() {
		setResizable(false);
		setMinimumSize(new Dimension(450, 520));
		setSize(new Dimension(450, 520));
		
		setRootPaneCheckingEnabled(false);
		setLocation(new Point(300, 200));
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
		
		
		
		// CALENDAR
	    startCalFrame = new CalendarFrame();
	    endCalFrame = new CalendarFrame();
	    
        startCalFrame.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ignoreStartChanged)
                    return;
                startDate.getModel().setValue(startCalFrame.cal.get().getCalendar().getTime());
            }
        });
        
        
        endCalFrame.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ignoreEndChanged)
                    return;
                endDate.getModel().setValue(endCalFrame.cal.get().getCalendar().getTime());
            }
        });
    
		
	    
	
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
			        // startDate.setText(selected.getStartD().toString());
			        // endDate.setText(selected.getEndD().toString());
			        
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
		
		startDate = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
		startDate.setName("");
		//startDate.setColumns(20);
		startDate.setBounds(77, 138, 95, 28);
		panel.add(startDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setEnabled(false);
		lblEndDate.setBounds(241, 144, 70, 16);
		panel.add(lblEndDate);
		
		endDate = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
		endDate.setEnabled(false);
		endDate.setName("");
		//endDate.setColumns(20);
		endDate.setBounds(309, 138, 95, 28);
		panel.add(endDate);
		
		JLabel lblEstEff = new JLabel("Est Effort (hrs)");
		lblEstEff.setBounds(6, 178, 90, 16);
		panel.add(lblEstEff);
		
		est_effort = new JTextField();
		est_effort.setName("");
		est_effort.setColumns(20);
		est_effort.setBounds(102, 172, 70, 28);
		panel.add(est_effort);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(241, 172, 90, 16);
		panel.add(lblPriority);
		
		priority = new JComboBox();
		priority.setModel(new DefaultComboBoxModel(new String[] {"Low", "Lowest", "Normal", "High", "Highest"}));
		priority.setName("");
		priority.setBounds(309, 167, 95, 28);
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
				selected.setTaskName(task_name.getText().toString());//
				selected.setEffort(Integer.parseInt(est_effort.getText().toString()));
				selected.setHeadTaskTitle(task_name.getText().toString());
				selected.setUserObject(task_name.getText().toString());
				selected.setPriority(priority.getSelectedItem().toString());
				selected.setTaskDescription(description.getText().toString());
				selected.setProgress(Integer.parseInt(progress.getText().toString()));
				selected.setStartDate((CalendarDate)startDate.getModel().getValue());
				selected.setEndDate((CalendarDate)endDate.getModel().getValue());
				
				tree.revalidate();
							
				
			}
		});
		btnUpdate.setBounds(241, 207, 95, 29);
		panel.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(327, 207, 105, 29);
		panel.add(btnReset);
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
		
		CalendarDate startDateMin = CurrentProject.get().getStartDate();
		CalendarDate startDateMax = CurrentProject.get().getEndDate();
		CalendarDate endDateMin = startDateMin;
		CalendarDate endDateMax = startDateMax;
		
		btnStartDate = new JButton("");
		btnStartDate.setIcon(new ImageIcon(TaskTemplateWizard.class.getResource("/net/sf/memoranda/ui/resources/icons/calendar.png")));
		btnStartDate.setSelectedIcon(new ImageIcon(TaskTemplateWizard.class.getResource("/net/sf/memoranda/ui/resources/icons/calendar.png")));
		btnStartDate.setBounds(171, 138, 32, 28);
        btnStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStartDate_actionPerformed(e);
            }
        });
		panel.add(btnStartDate);
		
		btnEndDate = new JButton("");
		btnEndDate.setEnabled(false);

		btnEndDate.setIcon(new ImageIcon(TaskTemplateWizard.class.getResource("/net/sf/memoranda/ui/resources/icons/calendar.png")));
		btnEndDate.setBounds(400, 138, 32, 28);
        btnEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEndDate_actionPerformed(e);
            }
        });
        
        endDate.setEditor(new JSpinner.DateEditor(endDate, sdf.toPattern())); //Added by (jcscoobyrs) on
		//14-Nov-2003 at 10:45:16PM
        
        endDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	// it's an ugly hack so that the spinner can increase day by day
            	SpinnerDateModel sdm = new SpinnerDateModel((Date)endDate.getModel().getValue(),null,null,Calendar.DAY_OF_WEEK);
            	endDate.setModel(sdm);
            	
                if (ignoreEndChanged)
                    return;
                ignoreEndChanged = true;
                Date sd = (Date) startDate.getModel().getValue();
                Date ed = (Date) endDate.getModel().getValue();				
				if (ed.before(sd)) {
                    endDate.getModel().setValue(ed);
                    ed = sd;
                }
				if ((endDateMax != null) && ed.after(endDateMax.getDate())) {
					endDate.getModel().setValue(endDateMax.getDate());
                    ed = endDateMax.getDate();
				}
                if ((endDateMin != null) && ed.before(endDateMin.getDate())) {
                    endDate.getModel().setValue(endDateMin.getDate());
                    ed = endDateMin.getDate();
                }
				endCalFrame.cal.set(new CalendarDate(ed));
                ignoreEndChanged = false;
            }
        });
		panel.add(btnEndDate);
		
		chkEndDate = new JCheckBox("");
		chkEndDate.setBounds(215, 138, 28, 29);
		panel.add(chkEndDate);
		
		chkEndDate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkEndDate_actionPerformed(e);
			}
		});
		
		// RESET (WORKING)
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Reset selected.");
				reset();
				
			}
		});
		
		// OK - POPULATE TREE/CLOSE (KIND OF WORKING)
		// TO-DO: Instantiate Template class by iterating over tree
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Ok selected.");
				//ok();
				loadIn(); 
			}
		});
		
		// SAVE (NOT WORKING)
		// TO-DO: Need to somehow populate a Template class by iterating over tree
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Save selected.");
				
				String name = task_name.getText();
				Date startD = (Date)startDate.getModel().getValue();
				Date endD = (Date)endDate.getModel().getValue();
				String effort = est_effort.getText();
				String prog = progress.getText();
				String desc = description.getText();
				
				int index = priority.getSelectedIndex();
				String prior = "";
			
				System.out.println(index);
				
				switch (index) {
					case 0: prior = "Low";
					break;
					case 1: prior = "Lowest";
					break;
					case 2: prior = "Normal";
					break;
					case 3: prior = "High";
					break;
					case 4: prior = "Highest";
					break;
					default:
					prior = "Invalid";
					break;	
				}
				
				System.out.println(prior);
				//System.out.println(name);
				
				try {
					TaskJson json = new TaskJson("C:/workspace316/json/src/json/template1.json","tasks");
					//json.addNode(name, startDate, endDate, effort, prog, prior, desc, "parent", children);
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		 
			}
				 
			
		});
		
		//WORKING -- Load the tree from the template screen to the task list.
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				create instance of loadWizard
				then after selected open you will parse though to these
				
				these below will update the wizard boxes for root
				task_name.setText("text");
				description.setText("text");
				startDate.setText();//  The two dates are special cases, they 
				endDate.setText();//    will have to be changed to CalendarDate objects in a later date.
				est_effort.setText("text");
				priority.setSelectedIndex(int); //see lines 361-369 for values
				progress.setText("text"); 				
				btnUpdate.notifyAll(); //this is my attempt to auto update.
				
				need to add subtask stuff like is done in the update and add methods.
				*/
				dispose();
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
			subtask.setHeadTaskTitle(root.getTaskName());
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
		
		// Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
        task_name.setText("");
        description.setText("");
        est_effort.setText("");
        progress.setText("");
        priority.setSelectedItem("Normal");
        startDate.getModel().setValue(new Date());
        endDate.getModel().setValue(new Date());
		
		
	}
	
	public void loadIn() {
		DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		if(root == null) {	
		}
		//load the tree from the template wizard to the task panel
		Template troot = (Template) model.getRoot();
		troot.loadTemplate();
		this.dispose();
	}
	
	public void ok(){

		// dispose();	
	}

	
	public void save(){
		
		DefaultMutableTreeNode selected = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Template root = (Template) model.getRoot();
		
		// This might be how we save it
		// Export template.getName, getPriority, etc.
		Template template = root;
		
	}
	
	
    void btnStartDate_actionPerformed(ActionEvent e) {
        startCalFrame.setLocation(btnStartDate.getLocation());
        startCalFrame.setSize(200, 200);
        this.getLayeredPane().add(startCalFrame);
        startCalFrame.show();

    }

    void btnEndDate_actionPerformed(ActionEvent e) {
        endCalFrame.setLocation(btnEndDate.getLocation());
        endCalFrame.setSize(200, 200);
        this.getLayeredPane().add(endCalFrame);
        endCalFrame.show();
    }
    
	void chkEndDate_actionPerformed(ActionEvent e) {
		endDate.setEnabled(chkEndDate.isSelected());
		btnEndDate.setEnabled(chkEndDate.isSelected());
		lblEndDate.setEnabled(chkEndDate.isSelected());
		if(chkEndDate.isSelected()) {
			Date currentEndDate = (Date) endDate.getModel().getValue();
			Date currentStartDate = (Date) startDate.getModel().getValue();
			if(currentEndDate.getTime() < currentStartDate.getTime()) {
				endDate.getModel().setValue(currentStartDate);
			}
		}
	}
}
