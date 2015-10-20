package examples;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	private boolean printChars = false;
	
	public void startDocument() throws SAXException {
		System.out.println("Vehicles In My Garage");
	}
	
	public void endDocument() throws SAXException {
		System.out.println("\nGarage Door Closed");
	}
	
	public void startElement(String namespaceURI, String localName, 
			String qName, Attributes atts) throws SAXException {
		if(qName.equals("make")) {
			System.out.print("\n");
		}
		if(qName.equals("make") || qName.equals("model")) {
			System.out.print(qName + " : ");
			printChars = true;
		}
	}
	
	public void characters(char ch[], int start, int length)
		throws SAXException{
		
		if(printChars) {
			String s = new String(ch, start, length);
			System.out.println(s);
		}
		printChars = false;
	}
}
