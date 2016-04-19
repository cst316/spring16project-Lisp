package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.TimeLogJson;

public class TimeLogJunitTest {

	@Test
	public void test() {
		assertEquals(1,1);
	}
	
	TimeLogJson json;
	
	@Before
	public void setup() throws FileNotFoundException, IOException, ParseException{
		json = new TimeLogJson("TimeLogTest.json");
		json.clearData();
	}
	
	@Test
	public void addLogTest() throws IOException{
		
		json.addLog();
		json.addLog();
		
		assertEquals(2, json.size());	
		
		json.clearData();
	}
	
	@Test
	public void editCellTest() throws IOException{
		
		json.addLog();
		
		json.editCell(0, 0, "Isidro");
		json.editCell(1, 0, "Task");
		json.editCell(2, 0, "LOC");
		
		assertEquals("Isidro", json.getElement(0, "name"));
		assertEquals("Task", json.getElement(0, "task"));
		assertEquals("LOC", json.getElement(0, "LOC"));
		
		json.clearData();
	}
	
	@Test
	public void deleteCellTest(){
		
		json.addLog();
		json.addLog();
		
		assertEquals(2, json.size());
		
		json.deleteCell(0);
		
		assertEquals(1, json.size());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void getKeyTest(){
		
		String name = json.getKey(0);
		String task = json.getKey(1);
		String loc = json.getKey(2);
		
		assertEquals("name", name);
		assertEquals("task", task);
		assertEquals("LOC", loc);
		
	}
	
	@Test
	public void getElementTest() throws IOException{
		
		json.addLog();
		
		json.editCell(0, 0, "Isidro");
		json.editCell(1, 0, "TaskName");
		json.editCell(2, 0, "LinesOfCode");
		
		assertEquals("Isidro", json.getElement(0, "name"));
		assertEquals("TaskName", json.getElement(0, "task"));
		assertEquals("LinesOfCode", json.getElement(0, "LOC"));
		
		json.clearData();
	}
	
	@Test
	public void sizeTest() throws IOException {
		
		json.addLog();
		json.addLog();
		json.addLog();
		
		assertEquals(3, json.size());
		
		json.clearData();
	}
}
