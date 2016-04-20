package net.sf.memoranda.ui;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.FlowLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.border.MatteBorder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	// CALENDAR
	private JLabel lblEndDate;
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
	private JButton btnStartDate;
	private JButton btnEndDate;
	private CalendarFrame startCalFrame;
	private CalendarFrame endCalFrame;
	private JCheckBox chkEndDate;
	
	// INPUT BOXES
	private JTextField taskName;
	private JTextField description;
	private JSpinner startDate;
	private JSpinner endDate;
	private JComboBox estEffort;
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
		
		JPanel taskPanel = new JPanel();
		taskPanel.setBounds(6, 0, 438, 215);
		getContentPane().add(taskPanel);
		
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new Template("Task") {{}}
		));
		tree.setToolTipText("Right click to view options");
		
		tree.setEditable(true);

		//MENU
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
		
		// CALENDAR
	    startCalFrame = new CalendarFrame();
	    endCalFrame = new CalendarFrame();
	    
        startCalFrame.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (ignoreStartChanged)
                    return;
                startDate.getModel().setValue(startCalFrame.cal.get().getCalendar().getTime());
            }
        });
        
        
        endCalFrame.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (ignoreEndChanged)
                    return;
                endDate.getModel().setValue(endCalFrame.cal.get().getCalendar().getTime());
            }
        });
    
		tree.setPreferredSize(new Dimension(430, 210));
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {

			    if (SwingUtilities.isRightMouseButton(event)) {
			        int row = tree.getClosestRowForLocation(event.getX(), event.getY());
			        tree.setSelectionRow(row);
			        menu.show(event.getComponent(), event.getX(), event.getY());
			    }
			    
			    if (SwingUtilities.isLeftMouseButton(event)) {

			        int row = tree.getClosestRowForLocation(event.getX(), event.getY());
			        tree.setSelectionRow(row);
			        
			        // Gets the text from the selected node
			        Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
			        String selectedText = selected.getUserObject().toString();
			        
			        // Populates text boxes
			        taskName.setText(selectedText);
			        description.setText(selected.getTaskDescription());
			        estEffort.setSelectedItem(Integer.valueOf((int)selected.getEffort()).toString());
			        progress.setText(Integer.valueOf(selected.getProgress()).toString());
			        priority.setSelectedItem(selected.getPriority());
			        startDate.getModel().setValue(selected.getStartDate());
			        endDate.getModel().setValue(selected.getEndDate());
			        
			    }	
			}
		});
			
		taskPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		taskPanel.add(tree);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(6, 465, 438, 29);
		getContentPane().add(buttonPanel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 6, 106, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		buttonPanel.setLayout(null);
		buttonPanel.add(btnSave);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(225, 6, 106, 23);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(107, 6, 106, 23);
		buttonPanel.add(btnLoad);
		buttonPanel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(332, 6, 106, 23);
		buttonPanel.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(6, 219, 438, 243);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(26, 6, 70, 16);
		panel.add(lblTaskName);
		
		taskName = new JTextField();
		taskName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				taskName.setBackground(Color.white);
			}
		});
		taskName.setBounds(36, 21, 375, 28);
		taskName.setName("");
		panel.add(taskName);
		taskName.setColumns(20);
		
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
		
		JLabel lblEstEff = new JLabel("Story Points");
		lblEstEff.setBounds(6, 178, 90, 16);
		panel.add(lblEstEff);
		
		estEffort = new JComboBox();
		estEffort.setModel(new DefaultComboBoxModel(new String[] {
				"1", "2", "3", "5", "8", "10", "15", "20", "40"}));
		estEffort.setName("");
		estEffort.setBounds(102, 172, 70, 28);
		panel.add(estEffort);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(241, 172, 90, 16);
		panel.add(lblPriority);
		
		priority = new JComboBox();
		priority.setModel(new DefaultComboBoxModel(new String[] {
				"Low", "Lowest", "Normal", "High", "Highest"}));
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
				
				// Initializes Template object
				selected.setTaskName(taskName.getText().toString());//
				selected.setEffort(Integer.parseInt(estEffort.getSelectedItem().toString()));
				selected.setHeadTaskTitle(taskName.getText().toString());
				selected.setUserObject(taskName.getText().toString());
				selected.setPriority(priority.getSelectedItem().toString());
				selected.setTaskDescription(description.getText().toString());
				selected.setProgress(Integer.parseInt(progress.getText().toString()));
				selected.setStartDate((Date)startDate.getModel().getValue());
				selected.setEndDate((Date)endDate.getModel().getValue());
				selected.setCalendarStartDate(new CalendarDate((Date)startDate.getModel().getValue()));
				selected.setCalendarEndDate(new CalendarDate((Date)endDate.getModel().getValue()));
				
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
		startDate.setEditor(new JSpinner.DateEditor(startDate, sdf.toPattern()));
		
		CalendarDate startDateMin = CurrentProject.get().getStartDate();
		CalendarDate startDateMax = CurrentProject.get().getEndDate();
		CalendarDate endDateMin = startDateMin;
		CalendarDate endDateMax = startDateMax;
		
		btnStartDate = new JButton("");
		btnStartDate.setIcon(new ImageIcon(TaskTemplateWizard.class.getResource(
				"/net/sf/memoranda/ui/resources/icons/calendar.png")));
		btnSelectedIcon(new ImageIcon(TaskTemplateWizard.class.getResource(
				"/net/sf/memoranda/ui/resources/icons/calendar.png")));
		btnStartDate.setBounds(171, 138, 32, 28);
        btnStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStartDateActionPerformed(e);
            }
        });
		panel.add(btnStartDate);
		
		btnEndDate = new JButton("");
		btnEndDate.setEnabled(false);

		btnEndDate.setIcon(new ImageIcon(TaskTemplateWizard.class.getResource(
				"/net/sf/memoranda/ui/resources/icons/calendar.png")));
		btnEndDate.setBounds(400, 138, 32, 28);
        btnEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                btnEndDateActionPerformed(event);
            }
        });
        
        endDate.setEditor(new JSpinner.DateEditor(endDate, sdf.toPattern()));
        
        endDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
            	//it's an ugly hack so that the spinner can increase day by day
				Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				Template root = (Template) model.getRoot();
            	SpinnerDateModel sdm = new SpinnerDateModel((Date)endDate.getModel().getValue(),
            			null,null,Calendar.DAY_OF_WEEK);
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
				if (endDateMax != null && ed.after(endDateMax.getDate())) {
					endDate.getModel().setValue(endDateMax.getDate());
                    ed = endDateMax.getDate();
				}
                if (endDateMin != null && ed.before(endDateMin.getDate())) {
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
			public void actionPerformed(ActionEvent event) {
				chkEndDateActionPerformed(event);
			}
		});
		
		// RESET (WORKING)
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				reset();
			}
		});
		
		// OK - POPULATE TREE/CLOSE (KIND OF WORKING)
		// TO-DO: Instantiate Template class by iterating over tree
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				loadIn(); 
			}
		});
		
		// TO-DO: Need to somehow populate a Template class by iterating over tree
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				String name = taskName.getText();
				if(!name.isEmpty()) {
					try {
						saving();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException | ParseException e1) {
						e1.printStackTrace();
					}
				}
				else {
					taskName.setBackground(Color.red);
				}
				
			}
				 
			
		});
			
		//WORKING -- Load the tree from the template screen to the task list.
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {	
				JDialog loader = getLoader();
				loader.setVisible(true);
			}
		});
	}
	
	private void btnSelectedIcon(ImageIcon imageIcon) {
		
	}

	//this selects the root node in the tree
	public void selectRootNode() {
		this.tree.setSelectionRow(0);
	}
	
	//TaskTable Getters and Setters.
	public TaskTable getTaskTable() {
		return tasktab;
	}

	public void setTaskTable(TaskTable tasktable) {
		this.tasktab = tasktable;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Template selected = (Template)tree.getSelectionPath().getLastPathComponent();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Template root = (Template) model.getRoot();
		
		// ADD (WORKING)
		JMenuItem item = (JMenuItem) event.getSource();
		if (item.equals(add)){

			Template subtask = new Template("Sub Task");
			model.insertNodeInto(subtask, root, root.getChildCount());
			subtask.setHeadTaskTitle(root.getTaskName());
	        startDate.getModel().setValue(new Date());
	        endDate.getModel().setValue(new Date());
			root.addSubtask(subtask);
			tree.revalidate();
			model.reload(root);
		}
		
		// REMOVE (WORKING)
		else if (item.equals(remove)){
			model.removeNodeFromParent(selected);
			root.removeSubtask(selected);
			tree.revalidate();	
		}
		
		// RENAME (WORKING)
		else if (item.equals(rename)){
			tree.startEditingAtPath(tree.getSelectionPath());
			String selectedText = selected.getUserObject().toString();
			selected.setHeadTaskTitle(selectedText);
			tree.revalidate();
		}	
	}
	
	public void reset(){
        taskName.setText("");
        description.setText("");
        estEffort.setSelectedItem("");
        progress.setText("");
        priority.setSelectedItem("Normal");
        startDate.getModel().setValue(new Date());
        endDate.getModel().setValue(new Date());	
	}
	
	public void loadIn() {
		DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		if(root != null) {	
			Template troot = (Template) model.getRoot();
			this.tree.setSelectionRow(0);
			troot.setEffort(Long.parseLong(estEffort.getSelectedItem().toString()));
			troot.loadTemplate();
		}
		this.dispose();
	}
	
	public void saving() throws FileNotFoundException, IOException, ParseException{
		DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		Template troot = (Template) model.getRoot();
		troot.save();
		this.dispose();
	}
	
    void btnStartDateActionPerformed(ActionEvent event) {
        startCalFrame.setLocation(btnStartDate.getLocation());
        startCalFrame.setSize(200, 200);
        this.getLayeredPane().add(startCalFrame);
        startCalFrame.show();
    }

    void btnEndDateActionPerformed(ActionEvent event) {
    	// Defaulted to btnStartDate's location for now
        endCalFrame.setLocation(btnStartDate.getLocation());
        endCalFrame.setSize(200, 200);
        this.getLayeredPane().add(endCalFrame);
        endCalFrame.show();
    }
    
	void chkEndDateActionPerformed(ActionEvent event) {
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
	
	public String getStringPriority(int p) {
		switch(p) {
		case 0: return "Lowest";
		case 1: return "Low";
		case 2: return "Normal";
		case 3: return "High";
		case 4: return "Highest";
		default:return "Lowest";
		}	
	}
	
	public JDialog getLoader() {
		selectRootNode();
		ArrayList<String> ids = getLoaderNames();
		JDialog loader = new JDialog(this, true);
		loader.setTitle("Load Template");
		loader.getContentPane().setLayout(null);
		loader.setSize(new Dimension(340,200));
		
		ArrayList<String> names = new ArrayList<String>();
		
		try{
			TaskJson taskjson = new TaskJson("template.json","tasks");
			for(int i = 0; i < ids.size(); i++){
				names.add(taskjson.getElement(ids.get(i), "name"));
			}
		}
		catch(IOException | ParseException e){
			e.printStackTrace();
		}
		
		JComboBox comboBox = new JComboBox();
		
		for(int i = 0; i < names.size(); i++){
			if(!names.get(i).isEmpty()) {
				comboBox.addItem(names.get(i));
			}	
		}

		comboBox.setBounds(34, 69, 226, 27);
		loader.getContentPane().add(comboBox);
		
		JLabel lblTemplates = new JLabel("My Templates");
		lblTemplates.setBounds(46, 50, 174, 16);
		loader.getContentPane().add(lblTemplates);
		
		//ok button
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String current = comboBox.getSelectedItem().toString();
				String id = String.valueOf(ids.get(comboBox.getSelectedIndex()));
				try {
					populateTreeFromLoad(id);
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
				loader.dispose();	
			}
		});
		btnOk.setBounds(33, 93, 117, 29);
		loader.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				loader.dispose();
			}
		});
		btnCancel.setBounds(143, 93, 117, 29);
		
		//delete button
		JButton btnDeleteTemplate = new JButton("");
		btnDeleteTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String tmp = "Are you sure you want to delete ";
				String cur = comboBox.getSelectedItem().toString();
				tmp = tmp + cur + "?";
				getDecision(loader, "Warning", tmp, 
						String.valueOf(ids.get(comboBox.getSelectedIndex())));
			}
		});
		btnDeleteTemplate.setToolTipText("Delete Selected Template");
		btnDeleteTemplate.setIcon(new ImageIcon(TaskTemplateLoader.class.getResource(
				"/com/sun/java/swing/plaf/motif/icons/Error.gif")));
		btnDeleteTemplate.setBounds(263, 69, 31, 27);
		loader.getContentPane().add(btnDeleteTemplate);
		loader.getContentPane().add(btnCancel);
		loader.setAlwaysOnTop(true);
		loader.setLocationRelativeTo(this);
		
		return loader;
	}
	
	private void getDecision(JDialog parent, String title, String question, String jsonid) {
		new DecisionBox(parent, "Warning", question, jsonid);
	}
	
	public ArrayList<String> getLoaderNames(){
		
		TaskJson tj;
		ArrayList<String> names = new ArrayList<String>();
		try{
		tj = new TaskJson("template.json","tasks");
		names = tj.getRootIds();
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return names;
	}
	
	// This is going to take in a JSONArray or an array of something, not a name
	public void populateTreeFromLoad(String id) throws FileNotFoundException, IOException, ParseException{
	
		TaskJson json = new TaskJson("template.json", "tasks");
		String name = json.getElement(id, "name");
		// Sets root template with the name
		//Template root = new Template(name);
		tree.setModel(new DefaultTreeModel(
				new Template(name)) {
					{
						taskName.setText(name);
				        description.setText(json.getElement(id, "description"));
				        estEffort.setSelectedItem(json.getElement(id, "effort"));
				        progress.setText(json.getElement(id, "progress"));
				        priority.setSelectedItem(getStringPriority(
				        		Integer.parseInt(json.getElement(id, "priority"))
				        ));
				        startDate.getModel().setValue(new Date());
				        endDate.getModel().setValue(new Date());
					}
				}
			);
			
			JSONArray subtasks = json.getTemplate(id);

			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			Template root = (Template) model.getRoot();
			root.setTaskDescription(json.getElement(id, "description"));
			root.setEffort(Integer.parseInt(json.getElement(id, "effort")));
			root.setProgress(Integer.parseInt(json.getElement(id, "progress")));
			root.setPriority(getStringPriority(
					Integer.parseInt(json.getElement(id, "priority"))
			));
			root.setStartDate(new Date());
			root.setEndDate(new Date());
			
			
			// Starts at one because the 0 index is the root
				Template subtask;
				for(int i = 1; i < subtasks.size(); i++){
				JSONObject currentSubtask = (JSONObject) subtasks.get(i);
				subtask = new Template(currentSubtask.get("name").toString());
				subtask.setHeadTaskTitle(root.getTaskName());
				subtask.setTaskDescription(currentSubtask.get("description").toString());
				subtask.setEffort(Long.parseLong(currentSubtask.get("effort").toString()));
				subtask.setPriority(getStringPriority(
						Integer.parseInt(currentSubtask.get("priority").toString()))
				);
				
				model.insertNodeInto(subtask, root, root.getChildCount());
				root.addSubtask(subtask);
				tree.revalidate();
				model.reload(root);
				}
		}	
	}

	
	

