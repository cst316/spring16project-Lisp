package net.sf.memoranda;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Nodes;

public class TimeLog {
	private Project _project = null;
    private Document _doc = null;
    private Element _root = null;
    
    public TimeLog(Document doc, Project prj) {
        _doc = doc;
        _root = _doc.getRootElement();
        _project = prj;
    }
    
    public TimeLog(Project prj) {            
        	_root = new Element("timelog");
            _doc = new Document(_root);
            _project = prj;
    }
    
	public Project getProject() {
		return _project;
	}
	
	public TimeLogEntry createTimeLogEntry(String person, String startTime, 
			String endTime, String comments) {
		Element el = new Element("entry");
		el.addAttribute(new Attribute("person", person));
		el.addAttribute(new Attribute("startTime", startTime));
		el.addAttribute(new Attribute("endTime", endTime));
		el.addAttribute(new Attribute("comments", comments));
		_root.appendChild(el);
	}
	
	/*
	 * Build the hashtable recursively
	 */
	private void buildElements(Element parent) {
		Elements els = parent.getChildElements("entry");
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			elements.put(el.getAttribute("id").getValue(), el);
			buildElements(el);
		}
	}
		
	public Collection getAllEntries(){
		Elements els = parent.getChildElements("entry");
		return convertToTimeLogEntryObjects(els);
	}
	
	public Document getXMLContent() {
        return _doc;
    }
	
	private Collection convertToTimeLogEntryObjects(Elements entries) {
        Vector v = new Vector();
        for (int i = 0; i < entries.size(); i++) {
        	TimeLogEntry t = new TimeLogEntry(entries.get(i), this);
            v.add(t);
        }
        return v;
    }
}
