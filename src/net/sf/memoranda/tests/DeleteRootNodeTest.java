/**
 * 
 */
package net.sf.memoranda.tests;
import net.sf.memoranda.TaskJson;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jason Rice
 * Description: Black/White box testing of the delete root node method
 */
public class DeleteRootNodeTest {

	/*
	 * Author: Jason Rice
	 * Description: add a new node to the list to see if I can delete it.
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("This ran");
		TaskJson tj = new TaskJson("template.json","tasks");
		System.out.println("This ran");
		String name = "TesterNode";
		String BDate = "01/05/16";
		String EDate = "01/06/16";
		String eff = "0";
		String prior = "1";
		String prog = "0";
		String desc = "This is a tester node";
		String par = "null";
		ArrayList<String> chil = new ArrayList<String>();
		tj.addNode(name, BDate, EDate, eff, prog, prior, desc, par, chil);	
		System.out.println("This ran");
	}

	/*
	 * Description: This is the generic tear down method.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * Author: Jason Rice
	 * Description: This will test if the delete method in TaskJson
	 * deletes the root node or not. It will do this by first getting the id
	 * of the most recent node added and then calling the delete method based off that id
	 * then checking if that id is still in the file or not.
	 */
	@Test
	public void testDeleteRootNode() throws FileNotFoundException, IOException, ParseException {
		TaskJson tj = new TaskJson("template.json","tasks");
		ArrayList<String> rid = tj.getRootIds();
		String id = rid.get(rid.size()-1);
		System.out.println("The id retrieved after the add = " + id + "\n");
		boolean answer = false;
		try {
			tj.deleteNode(id);
		}
		catch(NullPointerException ex) {
			System.out.println("There was an NullPointerException");
			answer = false;
		}
		catch(IOException ex) {
			System.out.println("There was an IOException");
			answer = false;
		}
		catch(Exception ex) {
			System.out.println("There was an Exception");
			answer = false;
		}
		//check to see if the correct id was deleted
		rid = tj.getRootIds();
		String id2 = rid.get(rid.size()-1);
		System.out.println("The id retrieved after the delete = " + id2);
		//check to see if the ids are different
		if(!id.equals(id2)) {
			answer = true;
		}
		else {
			answer = false;
		}
		assertTrue(answer);
	}

}
