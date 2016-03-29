package net.sf.memoranda;
/*
 * Author: Jason Rice
 * Description: the template class is a template version of a task object
 * that will store information pertaining to task templates.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Template extends DefaultMutableTreeNode {
	
	// For loading tasks
	CalendarDate CalendarStartDate;
	CalendarDate CalendarEndDate;
	
	// For TaskTemplateWizard UI
	Date startDate;
	Date endDate;
	
	TaskTable tt;
	DailyItemsPanel dip;
	
	private String taskDescription;
	private String headTaskTitle;
	private Vector<Template> subtasks;
	private int priornum;
	private String taskName;
	private String parentId;
	private int taskId;
	private int progress;
	private long effort;
	
	/*
	 * Name: Template constructor
	 * Author:
	 * Description: creates a template object only consisting of a name
	 * needs to be manually updated.
	 */
	public Template(String name){
		
		super(name);
		setSubtasks(new Vector<Template>());
		setTaskName(name);
		
		//this is the temporary date set
		CalendarStartDate = CalendarEndDate = CurrentDate.get();
		startDate = endDate = new Date();
	}
	
	/*
	 * Name: Template constructor
	 * Author: Jason Rice
	 * Description: creates a full template object.
	 */
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
	
	//empty constructor
	public Template() {
		super("");
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

	/*
	 * Name: setPriority
	 * Author: Jason Rice
	 * Description: converts the priority from a string into an integer
	 * and sets it to the corresponding priority.
	 */
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
	/*
	 * Name: removeSubtask
	 * Author: Jason Rice
	 * Description: removes a subtask from the task object.
	 */
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
	
	/*
 	* Name: save
 	* Author: 
 	* Description: Save functionality which saves the template object and
 	* sub template objects to a .Json file.
 	*/
public void save() throws FileNotFoundException, IOException, ParseException{
		
		TaskList tl = CurrentProject.getTaskList();
		 //tl.createTask(getCalendarStartDate(), getCalendarEndDate(), 
			//	getTaskName(), getPriority(), getEffort(), getTaskDescription(), null);
		Vector<Template> loadvec = getSubtasks();
		
		TaskJson json = new TaskJson("template.json", "tasks");		
		JSONArray template = new JSONArray();			
		JSONObject root = new JSONObject();
		
		//had to shift further
		ArrayList<String> children = new ArrayList<String>();
		for(int i = json.size()+3; i <= loadvec.size() + json.size()+2; i++){
			children.add(String.valueOf(i));
		}
		
		json.addNode(getTaskName(), "2/3/4", "2/2/3", String.valueOf(getEffort()), 
				String.valueOf(getProgress()), String.valueOf(getPriority()), 
				getTaskDescription(), "null", children);
		
		if(loadvec.size()!=0) {
			String currentSize = String.valueOf(json.size()+1);
			
			//This is a hack on taskListImpl in order to get the parents id.
			String parent = CurrentProject.getTaskList().getParentaskID();
			
			ArrayList<String> childNode = new ArrayList<String>();
			for(int i = 0; i<loadvec.size(); i++) {
				json.addNode(loadvec.get(i).getTaskName(), "2/2/3", "3/4/3", 
						String.valueOf(loadvec.get(i).getEffort()), 
						"null", String.valueOf(loadvec.get(i).getPriority()), 
						loadvec.get(i).getTaskDescription(), currentSize, childNode);
				System.out.println(loadvec.get(i).getTaskName());
			}
		}		
	}
	
	/*
 	* Name: loadTemplate
 	* Author: Jason Rice
 	* Description: loads in the template and the sub templates and converts them 
 	* to task objects and updates the task panel to display the tasks.
 	*/
	public void loadTemplate() {
		TaskList tl = CurrentProject.getTaskList();
		 tl.createTask(getCalendarStartDate(), getCalendarEndDate(), 
				getTaskName(), getPriority(), getEffort(), getTaskDescription(), null);
		Vector<Template> loadvec = getSubtasks();
		
		int vecsize = loadvec.size();
		if(vecsize != 0) {
			CurrentProject.updateProject();
			
			//This is a hack on taskListImpl in order to get the parents id.
			String par = CurrentProject.getTaskList().getParentaskID();
			
			loadTemplateChildren(loadvec, par);
		}
		//save the task list
		CurrentStorage.get().storeTaskList(tl, CurrentProject.get());
		//CurrentProject.save();
		CurrentProject.updateProject();
	}
	
	/*
	 * Author: Jason Rice
	 * Description: creates new subtasks of the children.
	 */
	public void loadTemplateChildren(Vector<Template> children, String parent) {
		for(int i = 0; i<children.size(); i++) {
			CurrentProject.getTaskList().createTask(
			children.get(i).getCalendarStartDate(), 
			children.get(i).getCalendarEndDate(),
			children.get(i).getTaskName(), 
			children.get(i).getPriority(), 
			children.get(i).getEffort(), 
			children.get(i).getTaskDescription(),
			parent);	
		}
	}
	
	/*
 	* Name: getJsonTemplate
 	* Author: Jason Rice
 	* Description: creates a template from a json object.
 	*/ 
	public Template getJsonTemplate(JSONArray ja) {
		JSONObject job = (JSONObject) ja.get(0);
		Template root = new Template(job.get("name").toString());
		root.setPriority(job.get("priority").toString());
		root.setProgress(Integer.parseInt(job.get("progress").toString()));
		root.setEffort((long)job.get("effort"));
		root.setTaskDescription(job.get("description").toString());
		root.setHeadTaskTitle(job.get("parent").toString());
		Template subtask = new Template();
		
		int jaSize = ja.size();
		for(int i = 1; i < jaSize; i++) {
			
			String jobName = job.get("name").toString();
			String jobPriority = job.get("priority").toString();
			int jobProgress = Integer.parseInt(job.get("progress").toString());
			long jobEffort = Long.parseLong(job.get("effort").toString());
			String jobDescription = job.get("description").toString();
			String jobHeadTask = job.get("parent").toString();
			
			job = (JSONObject) ja.get(i);
			subtask.setTaskName(jobName);
			subtask.setPriority(jobPriority);
			subtask.setProgress(jobProgress);
			subtask.setEffort(jobEffort);
			subtask.setTaskDescription(jobDescription);
			subtask.setHeadTaskTitle(jobHeadTask);
			root.addSubtask(subtask);
		}
		return root;
	}
}

