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

//Wrapper class for the TaskListImpl to generate and export an HTML report.
public class Report {
	
	private String projectTitle;
	private String projectStartDate;
	private String projectEndDate; //May be null if there is no end date
	private String projectStatus;
	private TaskList taskList;
	
	public Report(TaskListImpl taskList) {
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
		} else is (status == 2) {
			this.projectStatus = "Completed";
		} else if (status = 4) {
			this.projectStatus = "Frozen";
		} else {
			this.projectSataus = "Failed";
		}
		
		this.taskList = taskList; 
	}
		//Returns a String that contains the report in HTML
	public String toHTML {
		//Doctype and Head
		String HTML = "<!DOCTYPE html><html>";
		HTML += "<head><title>Project Report</title></head>";
		//Body
		HTML += "<body>";
		//Project info
		HTML += "<h2>"+projectTitle+"</h2>";
		HTML += "<p>Start Date: "+projectStartDate+"</p>";
		HTML += "<p>End Date: "+projectEndDate+"</p>";
		HTML += "<p>Status: "+projectStatus+"</p>";
		HTML += "<br>";
		//Iterate through tasklist and add data about each task to the HTML String
		Task[] taskArr = taskList.getAllSubTasks(null).toArray(taskArr);
		for (int i=0; i<taskArr.length; ++i) {
			HTML = getAllSubTasks(HTML, taskArr[i]);
		}
		//End of body and HTML
		HTML +="</body></html>";
	}
	
	private String taskToHTML (String HTML, Task task) {
		HTML += "<p>Task: "+task.getText()+"</p>";
		HTML += "<p>Start Date: "+task.getStartDate().toString+"</p>";
		if(task.getEndDate() == null) {
			HTML += "<p>End Date: None</p>";
		} else {
			HTML += "<p>End Date: "+task.getEndDate().toString+"</p>";
		}
		
		int status = task.getStatus();
		if(status == 0){
			HTML += "<p>Status: Scheduled</p>";
		} else if (status == 1) {
			HTML += "<p>Status: Active</p>";
		} else if (status == 2) {
			HTML += "<p>Status: Completed</p>";
		} else if (status == 4) {
			HTML += "<p>Status: Frozen</p>";
		} else if (status == 5) {
			HTML += "<p>Status: Failed</p>";
		}else if (status == 6) {
			HTML += "<p>Status: Locked</p>";
		}else {
			HTML += "<p>Status: Deadline</p>";
		}
		
		int priority = task.getPriority();
		if(status == 0){
			HTML += "<p>Priority: Lowest</p>";
		} else if (status == 1) {
			HTML += "<p>Priority: Low</p>";
		} else if (status == 2) {
			HTML += "<p>Priority: Normal</p>";
		} else if (status == 3) {
			HTML += "<p>Priority: High</p>";
		}else {
			HTML += "<p>Priority: Highest</p>";
		}
		HTML += "<p>Progress: "+task.getProgress()+"</p>";
		return HTML;
	}
}