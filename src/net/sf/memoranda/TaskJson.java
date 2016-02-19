package net.sf.memoranda;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TaskJson {
	
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray data;
	
	private Object obj;
	
	public TaskJson(String filename, String type) throws FileNotFoundException, IOException, ParseException{
		
		parser = new JSONParser();
		obj = parser.parse(new FileReader(filename));
		jsonObject = (JSONObject) obj;
		data  = (JSONArray) jsonObject.get(type);
		
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
		    	   task.remove(type, "12");
		    	   System.out.println(task.get(type));
		    	   task.put(type, newValue);
		    	   System.out.println(task.get(type));
		       }
	        }
		
		try (FileWriter file = new FileWriter("C:/workspace316/json/src/json/template.json")) {
			file.write(obj.toString());
			System.out.println("Successfully Copied JSON Object to File...");
		}
		
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

	public int size(){
		return data.size();
	}
}
