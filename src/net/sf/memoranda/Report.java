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
import net.sf.memoranda.Project;
import net.sf.memoranda.date.CurrentDate;

import java.util.Collection;
import java.util.Iterator;

//Wrapper class for the TaskListImpl to generate 
//and export an HTML report of a Project and it's Tasks
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
		int status = taskList.getProject().getStatus();
		if(status == 0){
			this.projectStatus = "Scheduled";
		} else if (status == 1) {
			this.projectStatus = "Active";
		} else if (status == 2) {
			this.projectStatus = "Completed";
		} else if (status == 4) {
			this.projectStatus = "Frozen";
		} else {
			this.projectStatus = "Failed";
		}
		
		this.taskList = taskList; 
	}
	
	//Converts the report to a string so it can be displayed in a dialog box
	public String toString(String id) {
		String s = "";
		Task current = null;
		//Project info
		s += projectTitle+"\n";
		s += "Start Date: "+projectStartDate+"\n";
		s += "End Date: "+projectEndDate+"\n";
		s += "Status: "+projectStatus+"\n";
		s += "\nTasks\n";
		//Iterate through tasklist and add data about each task to the String
		for (Iterator iterator = taskList.getAllSubTasks(id).iterator(); iterator.hasNext();) {
			//System.out.println("DEBUG: This Task has Subtasks");
			current = (Task)iterator.next();
			s = taskToString(s, current); 
			s += "\n";
			if(current.hasSubTasks(current.getID())) {
				s += toString(current.getID());
			}
		}
		return s;
	}
	
	public String taskToString(String s, Task task) {
		s += "Task: "+task.getText()+"\n";
		s += "Start Date: "+task.getStartDate().toString()+"\n";
		if(task.getEndDate() == null) {
			s += "End Date: None\n";
		} else {
			s += "End Date: "+task.getEndDate().toString()+"\n";
		}
		
		int status = task.getStatus(CurrentDate.get());
		if(status == 0){
			s += "Status: Scheduled\n";
		} else if (status == 1) {
			s += "Status: Active\n";
		} else if (status == 2) {
			s += "Status: Completed\n";
		} else if (status == 4) {
			s += "Status: Frozen\n";
		} else if (status == 5) {
			s += "Status: Failed\n";
		}else if (status == 6) {
			s += "Status: Locked\n";
		}else {
			s += "Status: Deadline\n";
		}
		
		int priority = task.getPriority();
		if(priority == 0){
			s += "Priority: Lowest\n";
		} else if (priority == 1) {
			s += "Priority: Low\n";
		} else if (priority == 2) {
			s += "Priority: Normal\n";
		} else if (priority == 3) {
			s += "Priority: High\n";
		}else {
			s += "Priority: Highest\n";
		}
		s += "Progress: "+task.getProgress()+"\n";
		return s;
	}
	
	//Returns a String that contains the report in HTML
	public String toHTML(String id) {
		Task current = null;
		//Doctype and Head
		String html = "<!DOCTYPE html><html>";
		html += "<head><title>Project Report</title></head>";
		//Body
		html += "<body>";
		//Project info
		html += "<h2>"+projectTitle+"</h2>";
		html += "<p>Start Date: "+projectStartDate+"</p>";
		html += "<p>End Date: "+projectEndDate+"</p>";
		html += "<p>Status: "+projectStatus+"</p>";
		html += "<br>";
		//Iterate through tasklist and add data about each task to the HTML String
		for (Iterator iterator = taskList.getAllSubTasks(id).iterator(); iterator.hasNext();) {
			current = (Task)iterator.next();
			html = taskToHTML(html, current);    
			html += "<br>";
			if(current.hasSubTasks(current.getID())) {
				html += toString(current.getID());
			}
		}
		//End of body and HTML
		html +="</body></html>";
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
		if(status == 0){
			html += "<p>Status: Scheduled</p>";
		} else if (status == 1) {
			html += "<p>Status: Active</p>";
		} else if (status == 2) {
			html += "<p>Status: Completed</p>";
		} else if (status == 4) {
			html += "<p>Status: Frozen</p>";
		} else if (status == 5) {
			html += "<p>Status: Failed</p>";
		}else if (status == 6) {
			html += "<p>Status: Locked</p>";
		}else {
			html += "<p>Status: Deadline</p>";
		}
		
		int priority = task.getPriority();
		if(priority == 0){
			html += "<p>Priority: Lowest</p>";
		} else if (priority == 1) {
			html += "<p>Priority: Low</p>";
		} else if (priority == 2) {
			html += "<p>Priority: Normal</p>";
		} else if (priority == 3) {
			html += "<p>Priority: High</p>";
		}else {
			html += "<p>Priority: Highest</p>";
		}
		html += "<p>Progress: "+task.getProgress()+"</p>";
		return html;
	}
}