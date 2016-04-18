package net.sf.memoranda.tests;

import static org.junit.Assert.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.sf.memoranda.TimeLogEntry;
import net.sf.memoranda.TimeLogJson;
/*
 * Author: Jason Rice
 * Description: this is a blackbox junit test for timelog
 */
public class TimeLogJsonBlackBoxTest {

	TimeLogEntry t1;
	boolean check = true;
	
	@Before
	public void setUp() throws Exception {
		String name = "DeleteTest1";
		String task = "toDeleteThings";
		String loc = "2";
		String startd = "04/10/16";
		String endd = "04/11/16";
		t1 = new TimeLogEntry(name, task, loc, startd, endd);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	//constructor was used in the set up so just need to 
	//validate that it was successful.
	@Test
	public void BlackboxConstructorTest() {
		assertTrue(check);
	}
	
	@Test
	public void BlackboxAddLogTest() {
		try {
			TimeLogJson tlj = new TimeLogJson("TimeLogBlackBoxTest.json");
			tlj.addLog();			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			check = false;
		}
		assertTrue(check);
	}
	
	public int rowCheck(int row) {
		if(row == 0) {
			return row;
		}
		else {
			return row -1;
		}
	}
	
	@Test
	public void EditCellAndGetElementTest() {
		try {
			TimeLogJson tlj = new TimeLogJson("TimeLogBlackBoxTest.json");
			int row = rowCheck(tlj.size());
			TimeLogEntry tmp = t1;
			tlj.editCell(0, row, tmp.getName());
			tlj.editCell(1, row, tmp.getTask());
			tlj.editCell(2, row, tmp.getLoc());
			tlj.editCell(3, row, tmp.getStartTime());
			tlj.editCell(4, row, tmp.getEndTime());
			
			//we know the cells name from above arguments.
			String testname = "DeleteTest1";
			String name = tlj.getElement(row, "name");
			assert(name.equals(testname));
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			check = false;
		}
		assertTrue(check);
	}
	
	@Test
	public void BlackBoxDeleteCellTest() {
		try {
			TimeLogJson tlj = new TimeLogJson("TimeLogBlackBoxTest.json");
			int row = rowCheck(tlj.size());
			tlj.deleteCell(row);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			check = false;
		}
		assertTrue(check);
	}
	
	@Test
	public void BlackBoxGetKeyTest() {
		try {
			TimeLogJson tlj = new TimeLogJson("TimeLogBlackBoxTest.json");
			for(int i = 0; i < 5; i++) {
				@SuppressWarnings("static-access")
				String str = tlj.getKey(i);
				if(str.isEmpty()) {
					check = false;
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			check = false;
		}
		assertTrue(check);
	}

}
