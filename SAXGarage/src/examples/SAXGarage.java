package examples;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class SAXGarage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try{
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new SAXHandler();
			parser.parse(new File("garage.xml"), handler);
		}
		catch (Exception ex) {
			System.err.println("ERROR " + ex);
		}
	}

}
