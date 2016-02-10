package net.sf.memoranda;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.ui.TaskPanel;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Util;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

public class Template extends DefaultMutableTreeNode {
	
	CalendarDate startD,
				 endD;
	
	private String taskDescription,
				   headTaskTitle;
	
	private Vector<Template> subtasks;
	private int priority; //priority had to be changed to an integer, because of how the system is set up.
	private String taskName;
	private int taskId,
	            parentId,
	            progress;
	private long effort;
	
	public Template(String name){
		
		super(name);
		setHeadTaskTitle(name);
		setSubtasks(new Vector<Template>());
		setTaskName(name);
	}
	
	public Template(int taskId, String headTaskTitle, String taskDescription, CalendarDate startD, CalendarDate endD,
			int priority, long effort, int progress) {
		
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
	public String getTaskDescription() {
		return taskDescription;
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
		return priority;
	}

	// CHANGED TO STRING: May need a switch to convert to int
	public void setPriority(int priority) {
		this.priority = priority;
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
		// I don't know what these lines do 
		// they give an id to the subtask and hold the id of the parent task.
		//task.setParentId(getTaskId());
		//task.setTaskId(getTaskId()+1);
		subtasks.addElement(task);
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
		Task addTask = CurrentProject.getTaskList().createTask(startD, endD, 
				getTaskName(), getPriority(), getEffort(), getTaskDescription(), null);
		Vector<Template> loadvec = getSubtasks();
		for(int i = 0; i<loadvec.size(); i++) {
			addTask = CurrentProject.getTaskList().createTask(
					loadvec.get(i).startD, 
					loadvec.get(i).endD, 
					loadvec.get(i).getTaskName(), 
					loadvec.get(i).getPriority(), 
					loadvec.get(i).getEffort(), 
					loadvec.get(i).getTaskDescription(), 
					loadvec.get(i).getHeadTaskTitle()); 
		}
		CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
		//need to update the task panel.
	}
}



/*
public class Template implements TaskList{

	private Project _project = null;
    private Document _doc = null;
    private Element _root = null;
	
	CalendarDate startdate;
	CalendarDate endDate;
	
	private String templateID;
	
	private int year;
	private int month;
	private int day;
	
	private String text;
	private String description;
	private String parentTaskId;
	
	private int priority;
	private long effort;
	
	private Hashtable elements = new Hashtable();
	
	public Template(Project prj){
		_root = new Element("templatelist");
        _doc = new Document(_root);
        _project = prj;
	}

	// Recursively adds elements to the hashtable
	private void buildElements(Element parent) {
		Elements els = parent.getChildElements("template");
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			elements.put(el.getAttribute("id").getValue(), el);
			buildElements(el);
		}
	}
	
	public Project getProject() {
		return _project;
	}

	@Override
	public Task getTask(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task createTask(CalendarDate startDate, CalendarDate endDate,
			String text, int priority, long effort, String description,
			String parentTaskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasSubTasks(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasParentTask(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection getTopLevelTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection getAllSubTasks(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection getActiveSubTasks(String taskId, CalendarDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long calculateTotalEffortFromSubTasks(Task t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CalendarDate getLatestEndDateFromSubTasks(Task t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalendarDate getEarliestStartDateFromSubTasks(Task t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] calculateCompletionFromSubTasks(Task t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getXMLContent() {
		// TODO Auto-generated method stub
		return null;
	}
}

	
	// To Do (Isidro Perez) :
	// 1) Understand how the system uses the Element, Project and Documents classes
	//    to keep track of date
	// 2) Connect the GUI to the back end
	// 3) Find out how id's are generated
	// 4) Create an XML file
  	
	
	// TO-DO: 
	// 1) Create linked list with tasks/subtasks
	// 2) Instantiate new Template whenever a user selects "Done" (or Save?) in Template Wizard
	// 3) Populate user tasks by iterating over template list and then adding to task list
	// 4) Implement Save feature

*/

