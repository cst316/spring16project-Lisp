package net.sf.memoranda.tests;

import static org.junit.Assert.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.sf.memoranda.TimeLogEntry;
import net.sf.memoranda.TimeLogJson;

public class TimeLogSaveAndLoadWhiteboxTest {
	
	TimeLogEntry t1;
	TimeLogEntry t2;
	
	@Before
	public void setUp() throws Exception {
		//create a couple of new log events.
		String name = "John";
		String task = "build stuff";
		String loc = "125";
		String startd = "01/02/15";
		String endd = "02/03/15";
		t1 = new TimeLogEntry(name, task, loc, startd, endd);
		
		name = "bill";
		task = "follow john";
		loc = "10";
		startd = "05/20/13";
		endd = "10/01/15";
		t2 = new TimeLogEntry(name, task, loc, startd, endd);
	}

	@After
	public void tearDown() throws Exception {
		t1 = null;
		t2 = null;
	}

	@Test
	public void CheckConstructorAndGetters() {
		assert(t1.getLoc() != null);
		assert(t1.getName() != null);
		assert(t1.getStartTime() != null);
		assert(t1.getTask() != null);
		assert(t1.getEndTime() != null);
	}
	
	@Test
	public void CheckSetters() {
		String n1, n2;
		t1.setEndTime("01/01.11");
		n1 = "01/01.11";
		n2 = t1.getEndTime();
		assertTrue(n1.equals(n2));
		t1.setStartTime("01/01/12");
		n1 = "01/01/12";
		n2 = t1.getStartTime();
		assertTrue(n1.equals(n2));
		t1.setLoc("500");
		n1 = "500";
		n2 = t1.getLoc();
		assertTrue(n1.equals(n2));
		t1.setName("apples");
		n1 = "apples";
		n2 = t1.getName();
		assertTrue(n1.equals(n2));
		t1.setTask("jump");
		n1 = "jump";
		n2 = t1.getTask();
		assertTrue(n1.equals(n2));
	}
	 
	@Test
	public void CheckLogSavingToJson() {
		boolean check = true;
		
		try {
			TimeLogJson jl = new TimeLogJson("TimeLog.json");
			jl.addLog();
			int row = jl.size()-1;
				
			jl.editCell(0, row, "Elmo");
			jl.editCell(1, row, "get this to work");
			jl.editCell(2, row, "15");
			jl.editCell(3, row, "0/0/00");
			jl.editCell(4, row, "3/3/33");
				
				
		} catch (IOException | ParseException e) {
			check = false;
			e.printStackTrace();
		}
		assertTrue(check);
	}
	
	@Test
	public void CheckLogLoadingToJson() {
		boolean check = true;
		String name, task, loc, sd, ed, temp;
		
		try {
			TimeLogJson jl = new TimeLogJson("TimeLog.json");
			int row = jl.size()-1;
			
			loc = jl.getElement(row, "LOC");
			name = jl.getElement(row, "name");
			task = jl.getElement(row, "task");
			sd = jl.getElement(row, "startTime");
			ed = jl.getElement(row, "endTime");
			
			temp = "15";
			assertTrue(loc.equals(temp));
			temp = "Elmo";
			assertTrue(name.equals(temp));
			temp = "get this to work";
			assertTrue(task.equals(temp));
			temp = "0/0/00";
			assertTrue(sd.equals(temp));
			temp = "3/3/33";
			assertTrue(ed.equals(temp));
			
		} catch (IOException | ParseException e) {
			check = false;
			e.printStackTrace();
		}
		assertTrue(check);
	}

}
