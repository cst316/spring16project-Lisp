package net.sf.memoranda;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.ui.DailyItemsPanel;
import net.sf.memoranda.ui.ProjectsPanel;
import net.sf.memoranda.ui.TaskPanel;
import net.sf.memoranda.ui.TaskTable;
import net.sf.memoranda.ui.WorkPanel;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Util;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

public class Template extends DefaultMutableTreeNode {
	
	CalendarDate startD,
				 endD;
	
	//taskpanel items
		TaskTable tt;
		DailyItemsPanel dip;
	
	private String taskDescription,
				   headTaskTitle;

	private Vector<Template> subtasks;
	public int priornum;
	private String taskName;
	private int taskId,
	            parentId,
	            progress;
	private long effort;
	
	public Template(String name){
		
		super(name);
		//setHeadTaskTitle(name);
		setSubtasks(new Vector<Template>());
		setTaskName(name);
		
		startD = endD = CurrentDate.get();
	}
	
	public Template(int taskId, String headTaskTitle, String taskDescription, CalendarDate startD, CalendarDate endD,
			String priority, long effort, int progress) {
		
		setSubtasks(new Vector<Template>());
		setTaskId(taskId);
		setHeadTaskTitle(headTaskTitle);
		setTaskDescription(taskDescription);
		this.startD = startD;
		this.endD = endD;
		setPriority(priority);
		setEffort(effort);
		setParentId(0);
		setTaskName(headTaskTitle);  //needs to be the name of this task not headTaskTittle.
	}
	
	//getters and setters
	public CalendarDate getStartD() {
		return startD;
	}

	public void setStartD(CalendarDate startD) {
		this.startD = startD;
	}

	public CalendarDate getEndD() {
		return endD;
	}

	public void setEndD(CalendarDate endD) {
		this.endD = endD;
	}

	public TaskTable getTt() {
		return tt;
	}

	public void setTt(TaskTable tt) {
		this.tt = tt;
	}

	public DailyItemsPanel getDip() {
		return dip;
	}

	public void setDip(DailyItemsPanel dip) {
		this.dip = dip;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}

	public void setStartDate(CalendarDate date) {
		this.startD = date;
	}
	
	public void setEndDate(CalendarDate date) {
		this.endD = date;
	}
	
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public String getHeadTaskTitle() {
		return headTaskTitle;
	}

	public void setHeadTaskTitle(String headTaskTitle) {
		this.headTaskTitle = headTaskTitle;
	}

	public Vector<Template> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(Vector<Template> subtasks) {
		this.subtasks = subtasks;
	}
    
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String name) {
		taskName = name;
	}
	
	public int getPriority() {
		return this.priornum;
	}

	// CHANGED TO STRING: May need a switch to convert to int
	public void setPriority(String priority) {
		int numberpriority;
		switch (priority){
		case "Low":
			numberpriority = 1;
			break;
		case "Lowest":
			numberpriority = 0;
			break;
		case "Normal":
			numberpriority = 2;
			break;
		case "High": 
			numberpriority = 3;
			break;
		case "Highest":
			numberpriority = 4;
			break;
		default:
			numberpriority = 0;
			break;
		}
		this.priornum = numberpriority;
	}

	public long getEffort() {
		return effort;
	}

	public void setEffort(long effort) {
		this.effort = effort;
	}
	
	//methods
	
	//addSubtask will set the parent id and then increment to 
	public void addSubtask(Template task) {
		subtasks.add(task);
		System.out.println("<<<DEBUG>>> addsubtask called.");
	}
	
	public boolean removeSubtask(Template task) {
		try {
			subtasks.remove(subtasks.indexOf(task));
		}
		catch(Exception ex) {
			System.out.println("The element does not exist.");
		}
		return true;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	//this is the method that loads the nodes onto the list <-----STILL WORKING ----->
	public void loadTemplate() {
		TaskList tl = CurrentProject.getTaskList();
		 tl.createTask(startD, endD, 
				getTaskName(), getPriority(), getEffort(), getTaskDescription(), null);
		System.out.println("<<<DEBUG>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("task name =" + getTaskName() + " task priority= " + getPriority() 
		+ " task effort= " +getEffort()
		+ "task description= " +getTaskDescription());
		System.out.println("<<<DEBUG>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Vector<Template> loadvec = getSubtasks();
		//System.out.println("<<<DEBUG>>> has sub task = " + loadvec.get(0).getTaskName() + " " + loadvec.get(0).getHeadTaskTitle());
		if(loadvec.size()!=0) {
			CurrentProject.updateProject();
			//subtasks not working cannot locate the parent in the defualt project.
			//Collection cl = tl.getTopLevelTasks();
			for(int i = 0; i<loadvec.size(); i++) {
				tl.createTask(
				loadvec.get(i).startD, 
				loadvec.get(i).endD, 
				loadvec.get(i).getTaskName(), 
				loadvec.get(i).getPriority(), 
				loadvec.get(i).getEffort(), 
				loadvec.get(i).getTaskDescription(), 
				loadvec.get(i).getHeadTaskTitle()); 
				
			}
		}
		//save the task list
		CurrentStorage.get().storeTaskList(tl, CurrentProject.get());
		//CurrentProject.save();
		CurrentProject.updateProject();
	}
}

