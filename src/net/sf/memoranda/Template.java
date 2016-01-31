package net.sf.memoranda;

import java.util.Collection;
import java.util.Hashtable;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Util;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

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


