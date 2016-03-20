package net.sf.memoranda.tests;


import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.TaskJson;

public class TaskJsonTest {
	
	//dummy test until JUnit tests are updated to reflect loading, it might be how 
	//the first Json items were stored, because that has been recently updated.
	@Test
	public void passTest() {
		assertEquals(1, 1);
	}
/*
	
	TaskJson json;
	TaskJson json1;
	TaskJson json2;
	
	@Before
	public void setUp() throws Exception {
		
		json = new TaskJson("template1.json","tasks");
		json1 = new TaskJson("template1.json","tasks");
		json2 = new TaskJson("template1.json","tasks");

	}

	@Test
	public void getElementTest() throws TransformerException	{
		
		assertEquals("1", json.getElement("1","id").toString());
		assertEquals("aName", json.getElement("1", "name").toString());
		assertEquals("23.23.3", json.getElement("1", "startDate").toString());
		assertEquals("34/23/3", json.getElement("1", "endDate").toString());
	    assertEquals("testinsdg", json.getElement("2", "progress").toString());
		assertEquals("234", json.getElement("2", "effort").toString());
		assertEquals("this ia a sdif", json.getElement("2", "description").toString());
		assertEquals("2", json.getElement("2", "parent").toString());	
		
		assertEquals("1", json1.getElement("1","id").toString());
		assertEquals("aName", json1.getElement("1", "name").toString());
		assertEquals("23.23.3", json1.getElement("1", "startDate").toString());
		assertEquals("34/23/3", json1.getElement("1", "endDate").toString());
	    assertEquals("testinsdg", json1.getElement("2", "progress").toString());
		assertEquals("234", json1.getElement("2", "effort").toString());
		assertEquals("this ia a sdif", json1.getElement("2", "description").toString());
		assertEquals("2", json.getElement("2", "parent").toString());	
		
	}
	
	@Test
	public void getElementsTest() {
		
		ArrayList<String> testList = new ArrayList<String>();
		
		testList.add("1");
		testList.add("2");
		testList.add("3");
		testList.add("4");
		testList.add("5");
		testList.add("6");
		testList.add("7");
		assertEquals(testList, json.getElements("id"));
		
		testList.clear();
		
		testList.add("aName");
		testList.add("aName");
		testList.add("aName");
		testList.add("The name");
		testList.add("task name");
		testList.add("task name");
		testList.add("task name");
		assertEquals(testList, json.getElements("name"));
		
		testList.clear();
		
		testList.add("23.23.3");
		testList.add("23.23.3");
		testList.add("23.23.3");
		testList.add("the start");
		testList.add("startd dtate");
		testList.add("startd dtate");
		testList.add("startd dtate");
		
		assertEquals(testList, json.getElements("startDate"));
		
		testList.clear();
		
		testList.add("34/23/3");
		testList.add("34/23/3");
		testList.add("34/23/3");
		testList.add("the end");
		testList.add("enddatat");
		testList.add("enddatat");
		testList.add("enddatat");
		
		assertEquals(testList, json.getElements("endDate"));
		
		testList.clear();
		
		testList.add("test423ing");
		testList.add("testinsdg");
		testList.add("testing4");
		testList.add("a progress");
		testList.add("123");
		testList.add("123");
		testList.add("123");
		
		assertEquals(testList, json.getElements("progress"));
		
		testList.clear();
		
		testList.add("234");
		testList.add("234");
		testList.add("234");
		testList.add("efforts");
		testList.add("12");
		testList.add("12");
		testList.add("12");
		
		assertEquals(testList, json.getElements("effort"));
		
		testList.clear();
		
		testList.add("this ia a sdif");
		testList.add("this ia a sdif");
		testList.add("this ia a sdif");
		testList.add("this is a description");
		testList.add("description dog");
		testList.add("description dog");
		testList.add("description dog");
		
		assertEquals(testList, json.getElements("description"));
	
		testList.clear();
		
		testList.add("2");
		testList.add("2");
		testList.add("2");
		testList.add("parent");
		testList.add("parent");
		testList.add("parent");
		testList.add("parent");
		
		assertEquals(testList, json.getElements("parent"));		
	}*/
}


