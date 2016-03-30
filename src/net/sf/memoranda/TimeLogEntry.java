package net.sf.memoranda;

import java.util.Collection;
import java.util.Vector;
import java.util.Calendar;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Nodes;
/**
 * @author Eric
 * This class uses the document to store data about a
 * time log entry. It is implemented similarly to tasks. 
 */
public class TimeLogEntry {
	
	private Element _element = null;
	private TimeLog _tl =null;
	//Constructor
	public TimeLogEntry(Element timeLogElement) {
		_element = timeLogElement;
		TimeLog _tl = null;
	}
	//Getters
	public String getPerson() {
		return _element.getAttribute("name");
	}
	
	public String getStartTime() {
		return _element.getAttribute("startTime");
	}
	
	public String getEndTime() {
		return _element.getAttribute("endTime");
	}
	
	public String getTask() {
		return _element.getAttribute("task");
	}
	
	public String getLOC() {
		return _element.getAttribute("loc");
	}
	
	public String getId() {
		return _element.getAttribute("id");
	}
	//Setters
	public void setPerson(String name) {
		setAttr("name", name);
	}
	
	public void setStartTime(String startTime) {
		setAttr("startTime", startTime);
	}
	
	public void setEndTime(String endTime) {
		setAttr("endTime", endTime);
	}
	
	public void setTask(String task) {
		setAttr("task", task);
	}
	
	public void setLOC(String loc) {
		setAttr("loc", loc);
	}
	//Add an attribute to this entry
	private void setAttr(String a, String value) {
        Attribute attr = _element.getAttribute(a);
        if (attr == null)
           _element.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }
	/**
	 * 
	 * @return Returns the entire entry as an element
	 */
	public Element getContent() {
        return _element;
    }
}
