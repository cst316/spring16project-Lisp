package net.sf.memoranda;

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
