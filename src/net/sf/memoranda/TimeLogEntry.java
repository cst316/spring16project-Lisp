package net.sf.memoranda;

import java.util.Date;

public class TimeLogEntry {
	
	private String name;
	private String task;
	private String loc;
	private String startTime;
	private String endTime;
	
	public TimeLogEntry(String name, String task, String loc, String startTime, String endTime){
		this.name = name;
		this.task = task;
		this.loc = loc;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.isEmpty()) {
			this.name = "Name";
		}
		else {
			this.name = name;
		}
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		if(task.isEmpty()){
			this.task = "Task";
		}
		else {
			this.task = task;
		}
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		if(loc.isEmpty()) {
			this.loc = "0";
		}
		else {
			this.loc = loc;
		}
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime.isEmpty()) {
			this.startTime = "0/0/00";
		}
		else {
			this.startTime = startTime;
		}
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if(endTime.isEmpty()) {
			this.endTime = "0/0/00";
		}
		else {
			this.endTime = endTime;
		}
	}
}
