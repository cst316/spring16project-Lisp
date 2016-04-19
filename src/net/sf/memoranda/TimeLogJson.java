package net.sf.memoranda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TimeLogJson {
	
	String filePath;
	
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONArray data;
	
	private Object obj;
	
	public TimeLogJson(String filePath) throws FileNotFoundException, IOException, ParseException{
		
		this.filePath = filePath;
		String file = new File(filePath).getCanonicalPath();
		parser = new JSONParser();
		obj = parser.parse(new FileReader(file));
		jsonObject = (JSONObject) obj;
		data = (JSONArray) jsonObject.get("timeLog");
 	}
	
	@SuppressWarnings("unchecked")
	public void addLog() {
			
		JSONObject newNode = new JSONObject();
		
		newNode.put("name", "");
		newNode.put("task", "");
		newNode.put("LOC", "");
		newNode.put("startTime", "00.00PM");
		newNode.put("endTime", "00.00PM");

		data.add(newNode);
		
		try {
			writeToFile("New log added, number of logs at " + data.size());
		} catch(IOException e){
			System.out.println("Could not find file");
		}
	}
	
	public void clearData() throws IOException{
		
		data.clear();
		writeToFile("Data cleared");
	}
	
	@SuppressWarnings("unchecked")
	public void editCell(int column, int row, String value) {
		
		String key = getKey(column);
		
		JSONObject obj = (JSONObject) data.get(row);
		
		obj.replace(key, value);
		
		try {
			writeToFile("Cell changed at column: " + column + " row: " + row);
		} catch(IOException e){
			System.out.println("Could not find file");
		}
	}
	
	public void deleteCell(int row){
		
		JSONObject obj = (JSONObject) data.get(row);
		data.remove(obj);
		
		try {
			writeToFile("Time Log deleted at row " + row);
		} catch(IOException e){
			System.out.println("Could not find file");
		}
	}
	
	public static String getKey(int column){
		
		String key = "";
		
		switch(column){
		case 0:
			key = "name";
			break;
		case 1:
			key = "task";
			break;
		case 2:
			key = "LOC";
			break;
		case 3:
			key = "startTime";
			break;
		case 4:
			key = "endTime";
			break;
		default:
			System.out.println("Row not recognized");
		}
		
		return key;
	}
	
	public String getElement(int row, String key){
		
		String element = "";
		
		JSONObject obj = (JSONObject) data.get(row);
		element = obj.get(key).toString();
		
		return element;
	}
	
	public int size(){
		return data.size();
	}
	
	public void writeToFile(String message) throws IOException{
		
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());		
			System.out.println(message);
		}
	}
}
