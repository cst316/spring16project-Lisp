package net.sf.memoranda;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Nodes;
/**
 * @author Eric
 * A class that manages timelog entries using the same
 * document as the tasklist
 */
public class TimeLog {
//	private Project _project = null;
//    private Document _doc = null;
//    private Element _root = null;
//    //Constructors
//    public TimeLog(Document doc, Project prj) {
//        _doc = doc;
//        _root = _doc.getRootElement();
//        _project = prj;
//    }
//    
//    public TimeLog(Project prj) {            
//        	_root = new Element("timelog");
//            _doc = new Document(_root);
//            _project = prj;
//    }
//    
//	public Project getProject() {
//		return _project;
//	}
//	/**
//	 * 
//	 * @param name The name of the person making the entry
//	 * @param startTime The time the person started working
//	 * @param endTime The time the person finished working
//	 * @param task The task that was worked on during that time
//	 * @param loc The Lines of Code completed
//	 * Creates a new entry and adds it to the timelog
//	 * @return Returns 
//	 */
//	public TimeLogEntry createTimeLogEntry(String name, String startTime, 
//			String endTime, String task, String loc) {
//		Element el = new Element("entry");
//		el.addAttribute(new Attribute("name", name));
//		el.addAttribute(new Attribute("startTime", startTime));
//		el.addAttribute(new Attribute("endTime", endTime));
//		el.addAttribute(new Attribute("task", task));
//		el.addAttribute(new Attribute("loc", loc));
//		String id = Util.generateId();
//		el.addAttribute(new Attribute("id", id));
//		_root.appendChild(el);
//		return new TimeLogEntry(el);
//	}
//	/**
//	 * 
//	 * @param entry The entry to be removed
//	 * @return If the entry was successfully removed return true
//	 */
//	public boolean removeTimeLogEntry(TimeLogEntry entry) {
//		_root.removeChild(entry.getContent());
//		return true;
//	}
//	
//	/*
//	 * Build the hashtable recursively
//	 * This is taken from the TaskList class
//	 */
//	private void buildElements(Element parent) {
//		Elements els = parent.getChildElements("entry");
//		for (int i = 0; i < els.size(); i++) {
//			Element el = els.get(i);
//			els.put(el.getAttribute("id").getValue(), el);
//			buildElements(el);
//		}
//	}
//	/**
//	 * @return Returns a collection of TimeLogEntry objects
//	 */
//	public Collection getAllEntries(){
//		Elements els = _root.getChildElements("entry");
//		return convertToTimeLogEntryObjects(els);
//	}
//	
//	public Document getXMLContent() {
//        return _doc;
//    }
//	/**
//	 * Converts entry elements to a vector of TimeLogEntry objects
//	 * @param entries Entry elements
//	 * @return Vector of TimeLogEntry objects
//	 */
//	private Collection convertToTimeLogEntryObjects(Elements entries) {
//        //check for null entries, if null return an empty vector
//		if (entries == null) {
//			return new Vector(0);
//		}
//		Vector v = new Vector();
//        for (int i = 0; i < entries.size(); i++) {
//        	TimeLogEntry t = new TimeLogEntry(entries.get(i), this);
//            v.add(t);
//        }
//        return v;
//    }
}
