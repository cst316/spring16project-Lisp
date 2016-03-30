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

public class TimeLogEntry {
	
	private Element _element = null;
	private TimeLog _tl =null;
	
	public TimeLogEntry(Element timeLogElement) {
		_element = timeLogElement;
		TimeLog _tl = null;
	}
	
	public String getPerson() {
		return _element.getAttribute("person");
	}
	
	public String getStartTime() {
		return _element.getAttribute("startTime");
	}
	
	public String getEndTime() {
		return _element.getAttribute("endTime");
	}
	
	public String getComments() {
		return _element.getAttribute("comments");
	}
	
	public void setPerson(String person) {
		setAttr("person", person);
	}
	
	public void setStartTime(String startTime) {
		setAttr("startTime", startTime);
	}
	
	public void setEndTime(String endTime) {
		setAttr("endTime", endTime);
	}
	
	public void setComments(String comments) {
		setAttr("comments", comments);
	}
	
	private void setAttr(String a, String value) {
        Attribute attr = _element.getAttribute(a);
        if (attr == null)
           _element.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }
	
}
