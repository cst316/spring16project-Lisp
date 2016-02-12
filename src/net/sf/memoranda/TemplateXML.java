package net.sf.memoranda;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TemplateXML {
	
	private File XMLFile;
	
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	private Element root;
	private Element newTemplate;
	
	private NodeList nodeList;
	
	private String id;
	private String filePath;
	
	ArrayList<String> elementList = new ArrayList<String>();
	
	// Constructor to allow us to read and write from the appropriate xml file. 
	// filepath is the path to the file, make sure you change this
	// nodename the name of the element we are adding, this case its a template
	public TemplateXML(String fileLocation, String tagName) throws SAXException, 
				IOException, ParserConfigurationException{
		
		XMLFile = new File(fileLocation);
		filePath = fileLocation;
		
		
		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(XMLFile);
		doc.getDocumentElement().normalize();
		
		nodeList = doc.getElementsByTagName(tagName);
		root = doc.getDocumentElement();
	    newTemplate = doc.createElement(tagName);
	    
	    id = Integer.toString(nodeList.getLength()+1);    
	}
	
	// Appending a new template to the existing xml document
	public void addNode(String name, String startDate, String endDate, 
					String progress, String effort, String description, String parent) throws ParserConfigurationException, 
						SAXException, IOException, TransformerException{
	        
		if(isUnique(name)){
			
	        Element elementId = doc.createElement("id");
	        elementId.appendChild(doc.createTextNode(this.id));
	        newTemplate.appendChild(elementId);

	        Element elementName = doc.createElement("name");
	        elementName.appendChild(doc.createTextNode(name));
	        newTemplate.appendChild(elementName);
	        
	        Element elementStartDate = doc.createElement("startDate");
	        elementStartDate.appendChild(doc.createTextNode(startDate));
	        newTemplate.appendChild(elementStartDate);

	        Element elementEndDate = doc.createElement("endDate");
	        elementEndDate.appendChild(doc.createTextNode(endDate));
	        newTemplate.appendChild(elementEndDate);
	        
	        Element elementProgress = doc.createElement("progress");
	        elementProgress.appendChild(doc.createTextNode(progress));
	        newTemplate.appendChild(elementProgress);
	        
	        Element elementEffort = doc.createElement("effort");
	        elementEffort.appendChild(doc.createTextNode(effort));
	        newTemplate.appendChild(elementEffort);

	        Element elementDescription = doc.createElement("description");
	        elementDescription.appendChild(doc.createTextNode(description));
	        newTemplate.appendChild(elementDescription);
	        
	        Element elementParent = doc.createElement("parent");
	        elementParent.appendChild(doc.createTextNode(parent));
	        newTemplate.appendChild(elementParent);
	        
	        root.appendChild(newTemplate);
	   
	        DOMSource source = new DOMSource(doc);
	        writeToFile(source);   
	        
		} else{
			System.out.println("This Name has already been used.");
		}
	}
	
	public void writeToFile(DOMSource source) throws TransformerException{
		 // Write to the new nodes created to the file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // Write to the file in a clean manner that is indented 
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        // Write file
        StreamResult result = new StreamResult(filePath);
        transformer.transform(source, result);
	}
	
	// Get any element by the id
	public String getElement(String id, String element){
		
		String elem = "";
		
		for( int i = 0; i < size(); i++ ){
			
			Node nNode = nodeList.item(i);
			Element nodeElement = (Element) nNode;
			
			if(nodeElement.getElementsByTagName("id").item(0).getTextContent().equals(id)){
				elem = nodeElement.getElementsByTagName(element).item(0).getTextContent();
			}
		}
		
		return elem;
	}
	
	// Edit an element in the xml file
	public void editElement(String name, String element, String newValue) throws TransformerException{
		
		for(int i = 0; i < size(); i++){
			Node nNode = nodeList.item(i);
			Element nodeElement = (Element) nNode;
			
			if(nodeElement.getElementsByTagName("name").item(i).getTextContent().equals(name)){
				nodeElement.getElementsByTagName(element).item(i).setTextContent(newValue);
				DOMSource source = new DOMSource(doc);
		        writeToFile(source);
			}
		}
	}
	
	// Get a specific node and add them into an array list
	public ArrayList<String> getElements(String elementName){
		
		// Start a new list
		elementList.clear();
				
		for (int i = 0; i < size(); i++) {
			
			Node nNode = nodeList.item(i);
			Element nodeElements = (Element) nNode;
			
			elementList.add(nodeElements.getElementsByTagName(elementName).item(i).getTextContent());
		}
		return elementList;
	}
	
	// check if the node has a unique name
	public boolean isUnique(String name){
		
		boolean flag = true;
		
		for (int i = 0; i < size(); i++){
			Node nNode = nodeList.item(i);
			Element nodeElement = (Element) nNode;
			if(nodeElement.getElementsByTagName("name").item(i).getTextContent().equals(name)){
				flag = false;
			}
		}
		return flag;
	}
	// get the base element of the xml document
	public String getRootElement(){
		return doc.getDocumentElement().getNodeName();
	}
	
	// return the number of templates in the xml file
	public int size(){
		return nodeList.getLength();
	}
}