package net.sf.memoranda;

/*
 * Author: Eric
 * Description: handles the back end of Time Log Entries
 */
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Nodes;

public class TimeLogEntry {
	
	private Element _element = null;
	private TimeLog _tl =null;
	
//Description: class to handle each time log entry.
	
    //Class constructor
	public TimeLogEntry(Element timeLogElement) {
		_element = timeLogElement;
	}
	//Getters
	/*
	 * MethodTitle: getName
	 * Returns: (String) name of the person
	 */
	public String getName() {
		return _element.getAttribute("name").toString();
	}
	/*
	 * MethodTitle: getStartTime
	 * Returns: (String) starting time of this log entry
	 */
	public String getStartTime() {
		return _element.getAttribute("startTime").toString();
	}
	/*
	 * MethodTitle: getEndTime
	 * Returns: (String) ending time of this log entry
	 */
	public String getEndTime() {
		return _element.getAttribute("endTime").toString();
	}
	/*
	 * MethodTitle: getTask
	 * Returns: (String) task of this log entry
	 */
	public String getTask() {
		return _element.getAttribute("task").toString();
	}
	/*
	 * MethodTitle: getLOC
	 * Returns: (String) lines of code of this log entry
	 */
	public String getLOC() {
		return _element.getAttribute("loc").toString();
	}
	/*
	 * MethodTitle: getId
	 * Returns: (String) the id of this log entry
	 */
	public String getId() {
		return _element.getAttribute("id").toString();
	}
	
	//Setters
	/*
	 * MethodTitle: setName
	 * Description: sets the name of this log entry
	 */
	public void setName(String name) {
		setAttr("name", name);
	}
	
	/*
	 * MethodTitle: setStartTime
	 * Description: sets the start time of this log entry
	 */
	public void setStartTime(String startTime) {
		setAttr("startTime", startTime);
	}
	
	/*
	 * MethodTitle: setEndTime
	 * Description: sets the end time of this log entry
	 */
	public void setEndTime(String endTime) {
		setAttr("endTime", endTime);
	}
	/*
	 * MethodTitle: setTasl
	 * Description: sets the task of this log entry
	 */
	public void setTask(String task) {
		setAttr("task", task);
	}
	/*
	 * MethodTitle: setLOC
	 * Description: sets the linesof code of this log entry
	 */
	public void setLOC(String loc) {
		setAttr("loc", loc);
	}
	/*
	 * MethodTitle: setAttr
	 * Description: gets an attribute and sets the new value to it
	 */
	public void setAttr(String a, String value) {
        Attribute attr = _element.getAttribute(a);
        if (attr == null) {
           _element.addAttribute(new Attribute(a, value));
        }
        else {
            attr.setValue(value);
        }
    }
	/*
	 * MethodTitle: getContent
	 * Returs: The entry as an element
	 */
	public Element getContent() {
        return _element;
    }
}
