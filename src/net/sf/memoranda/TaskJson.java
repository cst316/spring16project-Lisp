package net.sf.memoranda;

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
		parser = new JSONParser();
		obj = parser.parse(new FileReader(
				"C:/workspace316/json/src/json/template1.json"));
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
	
	
	public void addNode(String name, String startDate, String endDate, String effort, String progress, String description, String parent, ArrayList<String> children) throws IOException{
		
		JSONObject newNode = new JSONObject();
		
		String id = String.valueOf(size() + 1);
		newNode.put("id", id);
		newNode.put("name", name);
		newNode.put("startDate", startDate);
		newNode.put("endDate", endDate);
		newNode.put("effort", effort);
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
		
		System.out.println(data.toJSONString());
		Integer index = null;
		index = index.valueOf(id);
		JSONObject r = (JSONObject) data.get(index);
		data.remove(r);
		sortId();
		System.out.println(data.toJSONString());
		
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

	public int size(){
		return data.size();
	}
}
