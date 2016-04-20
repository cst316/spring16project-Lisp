/**
 * Report.java
 * Created on 2.11.2016, 12:36 Eric
 * Package: net.sf.memoranda
 * 
 * @author Eric Dressler
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

package net.sf.memoranda;

import net.sf.memoranda.TaskList;
import net.sf.memoranda.Task;
import net.sf.memoranda.date.CurrentDate;

import java.util.Collection;

//Wrapper class for the TaskListImpl to generate and export an HTML report of a Project and it's Tasks
public class Report {
	
	private String projectTitle;
	private String projectStartDate;
	private String projectEndDate; //May be null if there is no end date
	private String projectStatus;
	private TaskList taskList;
	
	public Report(TaskList taskList) {
		this.projectTitle = taskList.getProject().getTitle();
		this.projectStartDate = taskList.getProject().getStartDate().toString();
		if(taskList.getProject().getEndDate() == null){
			this.projectEndDate = "None";
		} else {
			this.projectEndDate = taskList.getProject().getEndDate().toString();
		}
		this.projectStatus = getProjectStatusString(taskList.getProject().getStatus());
		this.taskList = taskList; 
	}
	
	//Converts the report to a string so it can be displayed in a dialog box
	public String toString(String id, String str) {
		Task task = null;
		//Project info
		if(id == null) {
			str += projectTitle+"\n";
			str += "Start Date: "+projectStartDate+"\n";
			str += "End Date: "+projectEndDate+"\n";
			str += "Status: "+projectStatus+"\n";
			str += "\nTasks\n";
			
			Collection<Task> mainTasks = taskList.getAllSubTasks(id);
			for (Task taskElement : mainTasks) {
				toString(taskElement.getID(), str); 
		    }
		}
		
		//Iterate through tasklist and add data about each task to the String
		if(taskList.getTask(id) != null) {
			task = taskList.getTask(id);
			str = taskToString(str, task);
		} if(taskList.hasSubTasks(id)){
			Collection<Task> subTasks = taskList.getAllSubTasks(id);
			for (Task taskElement : subTasks) {
				toString(taskElement.getID(), str); 
			}
		} 
		return str;
	}
	
	public String taskToString(String str, Task task) {
		str += "Task: "+task.getText()+"\n";
		str += "Start Date: "+task.getStartDate().toString()+"\n";
		if(task.getEndDate() == null) {
			str += "End Date: None\n";
		} else {
			str += "End Date: "+task.getEndDate().toString()+"\n";
		}
		int status = task.getStatus(CurrentDate.get());
		str += getTaskStatusString(status)+"\n";
		int priority = task.getPriority();
		str += getTaskPriorityString(priority)+"\n";
		str += "Effort: "+task.getEffort()+"\n";
		str += "Progress: "+task.getProgress()+"\n";
		return str;
	}
	
	//Returns a String that contains the report in HTML
	public String toHTML(String id, String html) {
		//Doctype and Head
		Task task;
		if(id == null) {
			html = "<!DOCTYPE html><html>";
			html += "<head><title>Project Report</title></head>";
		
			//Body
			html += "<body>";
			//Project info
			html += "<h2>"+projectTitle+"</h2>";
			html += "<p>Start Date: "+projectStartDate+"</p>";
			html += "<p>End Date: "+projectEndDate+"</p>";
			html += "<p>Status: "+projectStatus+"</p>";
			html += "<br>";
		
			Collection<Task> mainTasks = taskList.getAllSubTasks(id);
			for (Task taskElement : mainTasks) {
				toString(taskElement.getID(), html); 
		    }
		}
		//Iterate through tasklist and add data about each task to the String
		if(taskList.getTask(id) != null) {
			task = taskList.getTask(id);
			html = taskToHTML(html, task);
		} if(taskList.hasSubTasks(id)){
			Collection<Task> subTasks = taskList.getAllSubTasks(id);
			for (Task taskElement : subTasks) {
				toString(taskElement.getID(), html); 
		    }
		}
		//End of body and HTML
		if (id == null){
			html +="</body></html>";
		}
		return html;
	}
	private String taskToHTML (String html, Task task) {
		html += "<p>Task: "+task.getText()+"</p>";
		html += "<p>Start Date: "+task.getStartDate().toString()+"</p>";
		if(task.getEndDate() == null) {
			html += "<p>End Date: None</p>";
		} else {
			html += "<p>End Date: "+task.getEndDate().toString()+"</p>";
		}
		int status = task.getStatus(CurrentDate.get());
		html += "<p>"+getTaskStatusString(status)+"</p>";
		int priority = task.getPriority();
		html += "<p>"+getTaskPriorityString(priority)+"</p>";
		html += "<p>Effort: "+task.getEffort()+"</p>";
		html += "<p>Progress: "+task.getProgress()+"</p>";
		return html;
	}
	
	private String getProjectStatusString(int status) {
		String statusStr;
		if(status == 0){
			statusStr = "Scheduled";
		} else if (status == 1) {
			statusStr = "Active";
		} else if (status == 2) {
			statusStr = "Completed";
		} else if (status == 4) {
			statusStr = "Frozen";
		} else {
			statusStr = "Failed";
		}
		return statusStr;
	}
	
	private String getTaskStatusString(int status) {
		String statusStr;
		if(status == 0){
			statusStr = "Status: Scheduled";
		} else if (status == 1) {
			statusStr = "Status: Active";
		} else if (status == 2) {
			statusStr = "Status: Completed";
		} else if (status == 4) {
			statusStr = "Status: Frozen";
		} else if (status == 5) {
			statusStr = "Status: Failed";
		}else if (status == 6) {
			statusStr = "Status: Locked";
		}else {
			statusStr = "Status: Deadline";
		}
		return statusStr;
	}
	
	private String getTaskPriorityString(int priority) {
		String priorityStr; 
		if(priority == 0){
			priorityStr = "Priority: Lowest";
		} else if (priority == 1) {
			priorityStr = "Priority: Low";
		} else if (priority == 2) {
			priorityStr = "Priority: Normal";
		} else if (priority == 3) {
			priorityStr = "Priority: High";
		}else {
			priorityStr = "Priority: Highest";
		}
		return priorityStr;
	}
}