package net.sf.memoranda;

// import static org.junit.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class TemplateXMLTest {
	
	/*TemplateXML testXML;
	TemplateXML testXML1;
	
	@Before
	public void setUp() throws Exception {
		
		testXML = new TemplateXML("/workspace316/xml/src/xml/TaskTemplate.xml","template");
		testXML1 = new TemplateXML("/workspace316/xml/src/xml/Test.xml","template");

	}

	@Test
	public void getElementTest() throws TransformerException	{
		
		assertEquals("1", testXML.getElement("1","id"));
		assertEquals("theThirdName", testXML.getElement("3", "name"));
		assertEquals("todaythe4", testXML.getElement("4", "startDate"));
		assertEquals("tomorrow5", testXML.getElement("5", "endDate"));
		// assertEquals("myProgresstoday", testXML.getElement("7", "progress"));
		assertEquals("myEfforttoday", testXML.getElement("10", "effort"));
		assertEquals("this task is really hard", testXML.getElement("11", "description"));
		assertEquals("null", testXML.getElement("12", "parent"));		
		
	}
	
	@Test
	public void getElementsTest() {
		
		ArrayList<String> testList = new ArrayList<String>();
		
		testList.add("1");
		testList.add("2");
		assertEquals(testList, testXML1.getElements("id"));
		
		testList.clear();
		
		testList.add("aname");
		testList.add("second name");
		assertEquals(testList, testXML1.getElements("name"));
		
		testList.clear();
		
		testList.add("today");
		testList.add("today second");
		assertEquals(testList, testXML1.getElements("startDate"));
		
		testList.clear();
		
		testList.add("tommorrow");
		testList.add("tommorrow 2");
		assertEquals(testList, testXML1.getElements("endDate"));
		
		testList.clear();
		
		//testList.add("myProgress");
		//testList.add("myProgress 2");
		//assertEquals(testList, testXML1.getElements("progress"));
		
		//testList.clear();
		
		testList.add("myEffort");
		testList.add("myEffort 2");
		assertEquals(testList, testXML1.getElements("effort"));
		
		testList.clear();
		
		testList.add("this task is hard");
		testList.add("this task is hard 2");
		assertEquals(testList, testXML1.getElements("description"));
	
		testList.clear();
		
		testList.add("null");
		testList.add("nul");
		assertEquals(testList, testXML1.getElements("parent"));		
	}
	
	@Test
	public void isUniqueTest() throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		assertTrue(testXML.isUnique("NOTANameInTheXML"));
		
		testXML.addNode("nameInXML", "2", "3", "4", "5", "6", "7");
		assertFalse(testXML.isUnique("nameInXML"));
		
	}
	
	@Test
	public void getRootElementTest(){
		
		assertEquals("templates", testXML.getRootElement());
		assertEquals("templates", testXML1.getRootElement());
		
	}
	
	@Test
	public void addNodeTest() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		// assertTrue(testXML.addNode("uniquekj", "start", "end", "1", "2", "3", "null"));
		//assertFalse(testXML.addNode("aname", "start", "end", "1", "2", "3", "null"));
		
	}
	
	@Test
	public void editElementTest() throws TransformerException {
		
		testXML1.editElement("1", "progress", "newprogress");
		assertEquals("newprogress",testXML1.getElement("1", "progress"));
		
	}*/
}
