package net.sf.memoranda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TaskJson {
	
	String filePath;
	
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray data;
	
	private Object obj;
	
	public TaskJson(String filePath, String type) throws FileNotFoundException, IOException, ParseException{
		
		this.filePath = filePath;
		String file = new File(filePath).getCanonicalPath();
	      // creates the file
		parser = new JSONParser();
		obj = parser.parse(new FileReader(
				file));
		jsonObject = (JSONObject) obj;
		data = (JSONArray) jsonObject.get("tasks");

 	}
	
	public String getElement(String id, String type){
		
		Iterator i = data.iterator();
		String element = "";
		
		while(i.hasNext()) {
			
           JSONObject task = (JSONObject) i.next();
                     
	       if(task.get("id").toString().equals(id)){
	    	   element = task.get(type).toString();
	       }
        }
		return element;
	}
	
	public JSONArray getTemplates(){
		
		JSONArray templates = new JSONArray();
		
		Iterator i = data.iterator();
		int count = 1;
		while(i.hasNext()) {
			
			JSONObject task = (JSONObject) i.next();
			
			if(task.get("parent").equals("null")){
				templates.add(getTemplate(String.valueOf(count)));
			}
			
			count++;
		}
		
		System.out.println();
		return templates;
	}
	
	public JSONArray getTemplate(String id){
		
		JSONArray template = new JSONArray();
		
		if(getChildren(id).isEmpty()) {
			
			Iterator i = data.iterator();
			
			while(i.hasNext()) {
				
				JSONObject task = (JSONObject) i.next();
				
				if(task.get("id").equals(id)){
					JSONObject root = new JSONObject();
					root.put("id", task.get("id"));
					root.put("name", task.get("name"));
					root.put("startDate", task.get("startDate"));
					root.put("endDate", task.get("endDate"));
					root.put("description", task.get("description"));
					root.put("effort", task.get("effort"));
					root.put("progress", task.get("progress"));
					root.put("priority", task.get("priority"));
					
					JSONArray children = (JSONArray) task.get("children");
					root.put(children, "children");
					
					template.add(root);
				}
			}
			
		} else {
			
			Iterator i = data.iterator();
			
			while(i.hasNext()) {
				
				JSONObject task = (JSONObject) i.next();
				
				if(task.get("id").equals(id)){
					JSONObject root = new JSONObject();
					
					root.put("id", task.get("id"));
					root.put("name", task.get("name"));
					root.put("startDate", task.get("startDate"));
					root.put("endDate", task.get("endDate"));
					root.put("description", task.get("description"));
					root.put("effort", task.get("effort"));
					root.put("progress", task.get("progress"));
					root.put("priority", task.get("priority"));
					
					template.add(root);
				}
			}
			
			ArrayList<String> children = new ArrayList<String>();
			
			children = getChildren(id);
			
				
			
			for(int count = 0; count < children.size(); count++) {
			
				Iterator i1 = data.iterator();
				
				while(i1.hasNext())	{
				
					JSONObject task = (JSONObject) i1.next();
					
					if(task.get("id").equals(children.get(count))){
						JSONObject child = new JSONObject();
						child.put("id", task.get("id"));
						child.put("name", task.get("name"));
						child.put("startDate", task.get("startDate"));
						child.put("endDate", task.get("endDate"));
						child.put("description", task.get("description"));
						child.put("effort", task.get("effort"));
						child.put("progress", task.get("progress"));
						child.put("priority", task.get("priority"));
						
						template.add(child);
					}
				}
			}
		}
		
		return template;
	}
	
	public int numberOfChildren(String id){
		
		ArrayList<String> children = new ArrayList<String>();
		
		children = getChildren("id");
	
		return children.size();
	}
	
	public void editElement(String id, String type, String newValue) throws IOException{
		
		Iterator i = data.iterator();
		
		while(i.hasNext()) {
			
	           JSONObject task = (JSONObject) i.next();
	                     
		       if(task.get("id").toString().equals(id)){
		    	   System.out.println(task.get(type));
		    	   String elem = getElement(id,type);
		    	   //task.remove(type, elem);
		    	   System.out.println(task.get(type));
		    	   task.replace(type, elem, newValue);
		    	   System.out.println(task.get(type));
			 
		       }
	        }
		
		System.out.println(filePath);
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());		
			System.out.println("Successfully Copied JSON Object to File...");
		}
	}
	
	
	public void addNode(String name, String startDate, String endDate, String effort, String progress, String prior, String description, String parent, ArrayList<String> children) throws IOException{
		
		JSONObject newNode = new JSONObject();
		
		String id = String.valueOf(size() + 1);
		newNode.put("id", id);
		newNode.put("name", name);
		newNode.put("startDate", startDate);
		newNode.put("endDate", endDate);
		newNode.put("effort", effort);
		newNode.put("priority", prior);
		newNode.put("progress", progress);
		newNode.put("description", description);
		newNode.put("parent", parent);
		
		JSONArray childNodes = new JSONArray();
		
		for(int i = 0; i < children.size(); i++){
			childNodes.add(i, children.get(i));
		}
		
		newNode.put("children", childNodes);
		
		System.out.println(data.toJSONString());
		data.add(newNode);
		System.out.println(data.toJSONString());
		
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());		
			System.out.println("Successfully Copied JSON Object to File...");
		}	
	}
	
	public void deleteNode(String id) throws IOException{
		
		Integer index = null;
		index = Integer.valueOf(id);
		String taskId = getIdFromIndex(index);
		JSONObject task = (JSONObject) data.get(index);
		data.remove(index);
		
		//reomveHeadTask(taskId);
		//removeSubTasks(taskId);
		
	}
	
	public String getIdFromIndex(Integer index) {
		
		String id = "";
		JSONObject task = (JSONObject) data.get(index);
		id = task.get("id").toString();
		System.out.println("\nThe id returned from getIdFromIndex: "+id);
		return id;
	}

	public void reomveHeadTask(String id) throws IOException {
		
		String tmp = "";
		
		Iterator i = data.iterator();
		
		System.out.println(id);
		
		while(i.hasNext()) {
			
			JSONObject task = (JSONObject) i.next();
			tmp = task.get("id").toString();
			if (tmp.equals(id)){
				System.out.println(task.get("name"));
				data.remove(task);
				
			}	
		}
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());		
			System.out.println("Successfully Copied JSON Object to File...");
		}
	}

	// Get all the id's of the subTasks of a specific task
	public ArrayList<String> getSubTasks(String id){
		
		ArrayList<String> list = new ArrayList<>();
		
		String tmp = "";
		
		Iterator i = data.iterator();
		
		while(i.hasNext()) {
			
			JSONObject task = (JSONObject) i.next();
			tmp = task.get("parent").toString();
			
			if (tmp.equals(id)){
				tmp = task.get("id").toString();
				list.add(tmp);
			}
		}
		
		return list;
	}
	// Remove all of the subtasks that have the same id for parent
	public void removeSubTasks(String id) throws IOException{
		
		String tmp = "";
		
		Iterator i = data.iterator();
		System.out.println(id);
		
		while(i.hasNext()) {
			
			
			JSONObject task = (JSONObject) i.next();
			tmp = task.get("parent").toString();
			
			if (tmp.equals(id)){
				System.out.println(tmp + " parent");
				data.remove(task);
				
			}	
		}
		
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());		
			System.out.println("Successfully Copied JSON Object to File...");
		}
	}
	
	public int getHighestId(){
		
		int id = 0;
		
		String tmp = "";
		
		Iterator i = data.iterator();
		
		while(i.hasNext()) {
			
			JSONObject task = (JSONObject) i.next();
			tmp = task.get("id").toString();
			if (Integer.valueOf(tmp) > id){
				id = Integer.valueOf(tmp);
			}
		}
		
		return id;
	}
	
	public ArrayList<String> getElements(String type){
		
		ArrayList<String> list = new ArrayList<String>();
		
		Iterator i = data.iterator();
		
		while(i.hasNext()){
			
			JSONObject task = (JSONObject) i.next();
			
			list.add(task.get(type).toString());
		}	
		return list;
	}
	
	public void sortId() throws IOException{
		
		Iterator i = data.iterator();
		
		System.out.println(data.toJSONString());
		
		int tmp = 1;
		
		while(i.hasNext()) {
			
			JSONObject task = (JSONObject) i.next();
			
			String newID = String.valueOf(tmp);
			
			System.out.println(task.get("id").toString());
			
			task.replace("id", task.get("id").toString(), newID);
			
			tmp++;
		}
		
		System.out.println(data.toJSONString());
	}
	
	public ArrayList<String> getChildren(String id){
		ArrayList<String> ids = new ArrayList<String>();
		Iterator iter = data.iterator();
		
		while(iter.hasNext()){
			
			JSONObject task = (JSONObject) iter.next();
			
			if(task.get("id").equals(id)){
				JSONArray children = (JSONArray) task.get("children");
				
				for(int i = 0; i < children.size(); i++){
					ids.add(children.get(i).toString());
				}
			}
		}
		return ids;
	}
	
	public ArrayList<String> getRootIds(){
		ArrayList<String> rootNames = new ArrayList<String>();
		
		Iterator i = data.iterator();
		
		while(i.hasNext()){
			JSONObject task = (JSONObject) i.next();
			
			if(task.get("parent").equals("null")){
				rootNames.add(task.get("id").toString());
			}
		}
		
		return rootNames;
	}
	public int size(){
		return data.size();
	}
}
