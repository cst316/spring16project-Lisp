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
		String HTML = "<!DOCTYPE html>";
		HTML += "<head><title>Project Report</title></head>";
		//Body
		HTML += "<body>";
		//Project info
		HTML += "<h2>"+projectTitle+"</h2>";
		HTML += "<p>Start Date: "+projectStartDate+"</p>";
		HTML += "<p>End Date: "+projectEndDate+"</p>";
		HTML += "<p>Status: "+projectStatus+"</p>";
		HTML += "<br>";
	}
}