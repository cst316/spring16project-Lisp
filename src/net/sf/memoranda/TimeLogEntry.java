package net.sf.memoranda;

/*
 * Author:
 * Description: handles the back end of Time Log Entries
 */
import nu.xom.Attribute;
import nu.xom.Element;

//Description: class to handle each time log entry.
public class TimeLogEntry {
	
	private Element _element = null;
	
    //Class constructor
	public TimeLogEntry(Element timeLogElement) {
		_element = timeLogElement;
	}
	
	/*
	 * MethodTitle: getPerson
	 * Returns: (String) name of the person
	 */
	public String getPerson() {
		return _element.getAttribute("person").toString();
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
	 * MethodTitle: getComments
	 * Returns: (String) any comments made in this entry
	 */
	public String getComments() {
		return _element.getAttribute("comments").toString();
	}
	
	/*
	 * MethodTitle: getId
	 * Returns: (String) the id of this log entry
	 */
	public String getId() {
		return _element.getAttribute("id").toString();
	}
	
	/*
	 * MethodTitle: setPerson
	 * Description: sets the name of the person
	 */
	public void setPerson(String person) {
		setAttr("person", person);
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
	 * MethodTitle: setComments
	 * Description: sets any comments made to this log entry
	 */
	public void setComments(String comments) {
		setAttr("comments", comments);
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
	 * Description: returns the entire element
	 */
	public Element getContent() {
        return _element;
    }
}
