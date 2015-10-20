package labs;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	private boolean getChars = false;
	private int count = 0;
	
	public void startDocument() throws SAXException {
		System.out.println("Counting cars...");
	}
	
	public void endDocument() throws SAXException {
		//System.out.println("\nGarage Door Closed");
		System.out.println("Found " + count + " vehicles");
	}
	
	public void startElement(String namespaceURI, String localName, 
			String qName, Attributes atts) throws SAXException {
		if(qName.equals("car") | qName.equals("van")) {
			int miles = Integer.parseInt(atts.getValue("miles"));
			if(miles < 20000)
				count++;
		}
	}
	
//	public void characters(char ch[], int start, int length)
//		throws SAXException{
//		
//		if(getChars) {
//			String s = new String(ch, start, length);
//			int miles = Integer.parseInt(s);
//			
//		}
//		getChars = false;
//	}
}
