package net.sf.memoranda;

/*
import static org.junit.Assert.*;


import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Template;
import net.sf.memoranda.date.CalendarDate;

public class TemplateTest {

	//create two Template objects for testing
	Template tmp;
	Template tmp2;

	@Before
	public void setUp() throws Exception {
		//initialize the two template objects
		CalendarDate cd = CalendarDate.today();
		CalendarDate td = CalendarDate.tomorrow();
		tmp = new Template("tmp");
		tmp2 = new Template( 123, "tmp", 
				"This is a Description", cd, td, "Low", (long) 1.0, 0);
	}

	@After
	public void tearDown() throws Exception {
		//empty out the two template objects
		tmp = null;
		tmp2 = null;
	}

	
	@Test   //Calendar getters and setters
	public void calendarStartandEndDatesTest() {
		CalendarDate cdt = CalendarDate.today();
		CalendarDate cdn = CalendarDate.tomorrow();
		tmp.setStartD(cdt);
		tmp.setEndD(cdn);
		assertTrue(tmp.getStartD() == cdt);
		assertTrue(tmp.getEndD() == cdn);
	}
	
	@Test   //task Description getters and setters
	public void taskDescriptionSetterandGetterTest() {
		tmp.setTaskDescription("This is a test Descript");
		assertTrue(tmp.getTaskDescription().equals("This is a test Descript"));
	}
	
	@Test   //task progress getters and setters
	public void taskProgressSetterandGetterTest() {
		tmp.setProgress(2);
		assertFalse(tmp.getProgress() == 0);
		assertTrue(tmp.getProgress() == 2);
	}
	
	@Test   //head task title getters and setters
	public void headTTSetterandGetterTest() {
		tmp.setHeadTaskTitle("None");
		assertFalse(tmp.getHeadTaskTitle().equals(null));
		assertTrue(tmp.getHeadTaskTitle().equals("None"));
	}

	@Test   //task name getters and setters
	public void taskNameSetterandGetterTest() {
		tmp.setTaskName("newName");
		assertFalse(tmp.getTaskName().equals("tmp"));
		assertTrue(tmp.getTaskName().equals("newName"));
	}
	
	@Test   //priority getters and setters
	public void prioritySetterandGetterTest() {
		/*priority values should reflect 
		  Lowest = 0
		  Low = 1
		  Normal = 2
		  High = 3
		  Highest = 4
		  Default = 0
		*/

/*
		tmp.setPriority("Lowest");
		assertTrue(tmp.getPriority() == 0);
		tmp.setPriority("Low");
		assertTrue(tmp.getPriority() == 1);
		tmp.setPriority("Normal");
		assertTrue(tmp.getPriority() == 2);
		tmp.setPriority("High");
		assertTrue(tmp.getPriority() == 3);
		tmp.setPriority("Highest");
		assertTrue(tmp.getPriority() == 4);
		tmp.setPriority("RandomNumber");
		assertTrue(tmp.getPriority() == 0);
	}
	
	@Test   //effort getters and setters
	public void effortSetterandGetterTest() {
		tmp.setEffort((long)2);
		assertFalse(tmp.getEffort() == (long)0);
		assertTrue(tmp.getEffort() == (long)2);
	}
	
	@Test   //parentID getters and setters
	public void parentIDSetterandGetterTest() {
		tmp.setParentId("Parent");
		assertFalse(tmp.getParentId().equals(null));
		assertTrue(tmp.getParentId().equals("Parent"));
	}
	
	@Test   //taskID getters and setters
	public void taskIDSetterandGetterTest() {
		tmp.setTaskId(121);
		assertFalse(tmp.getTaskId() == 0);
		assertTrue(tmp.getTaskId() == 121);
	}
	
	@Test   //addandRemoveSubtask
	public void addandRemoveSubTaskTest() {
		tmp.addSubtask(tmp2);
		String name = tmp2.getTaskName();
		Vector<Template> tl = tmp.getSubtasks();
		//test if added
		assertTrue(tl.get(0).getTaskName().equals(name));
		//test if removed
		if(tl.size() == 1) {
			assertTrue(tmp.removeSubtask(tmp2));
		}
		else {
			fail("the vector size was not equal to 1");
		}
	}
}
*/

