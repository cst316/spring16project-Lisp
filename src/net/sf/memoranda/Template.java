package net.sf.memoranda;

import java.util.Collection;
import java.util.Date;
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
	
	// For loading tasks
	CalendarDate CalendarStartDate,
				 CalendarEndDate;
	
	// For TaskTemplateWizard UI
	Date startDate, endDate;
	
	//taskpanel items
		TaskTable tt;
		DailyItemsPanel dip;
	
	private String taskDescription,
				   headTaskTitle;

	private Vector<Template> subtasks;
	public int priornum;
	private String taskName,
				   parentId;
	private int taskId,
	            progress;
	private long effort;
	
	public Template(String name){
		
		super(name);
		setSubtasks(new Vector<Template>());
		setTaskName(name);
		
		//this is the temporary date set
		CalendarStartDate = CalendarEndDate = CurrentDate.get();
		startDate = endDate = new Date();
	}
	
	public Template(int taskId, String headTaskTitle, String taskDescription, 
			CalendarDate CalendarStartDate, CalendarDate CalendarEndDate,
			String priority, long effort, int progress) {
		
		setSubtasks(new Vector<Template>());
		setTaskId(taskId);
		setHeadTaskTitle(headTaskTitle);
		setTaskDescription(taskDescription);
		setCalendarStartDate(CalendarStartDate);
		setCalendarEndDate(CalendarEndDate);
		setPriority(priority);
		setEffort(effort);
		setParentId("");
		setTaskName(headTaskTitle);  //needs to be the name of this task not headTaskTittle.
	}
	
	//getters and setters
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	
	public CalendarDate getCalendarStartDate() {
		return CalendarStartDate;
	}

	public CalendarDate getCalendarEndDate() {
		return CalendarEndDate;
	}

	public String getTaskDescription() {
		return taskDescription;
	}
	
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void setCalendarStartDate(CalendarDate date) {
		this.CalendarStartDate = date;
	}
	
	public void setCalendarEndDate(CalendarDate date) {
		this.CalendarEndDate = date;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public void loadTemplate() {
		TaskList tl = CurrentProject.getTaskList();
		 tl.createTask(getCalendarStartDate(), getCalendarEndDate(), 
				getTaskName(), getPriority(), getEffort(), getTaskDescription(), null);
		Vector<Template> loadvec = getSubtasks();
		if(loadvec.size()!=0) {
			CurrentProject.updateProject();
			
			//This is a hack on taskListImpl in order to get the parents id.
			String parent = CurrentProject.getTaskList().getParentaskID();
			
			for(int i = 0; i<loadvec.size(); i++) {
				CurrentProject.getTaskList().createTask(
				loadvec.get(i).getCalendarStartDate(), 
				loadvec.get(i).getCalendarEndDate(),
				loadvec.get(i).getTaskName(), 
				loadvec.get(i).getPriority(), 
				loadvec.get(i).getEffort(), 
				loadvec.get(i).getTaskDescription(),
				parent);	
			}
		}
		//save the task list
		CurrentStorage.get().storeTaskList(tl, CurrentProject.get());
		//CurrentProject.save();
		CurrentProject.updateProject();
	}
}

