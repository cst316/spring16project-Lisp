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
		assert name != null:"name is equal to null";
		this.task = task;
		assert task != null:"task is equal to null";
		this.loc = loc;
		assert loc != null:"loc is equal to null";
		this.startTime = startTime;
		assert startTime != null:"startTime is equal to null";
		this.endTime = endTime;
		assert endTime != null:"endTime is equal to null";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
		
	}
}
