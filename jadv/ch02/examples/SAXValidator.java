package examples;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXValidator {

  public static void main(String args[]) {
    try {
      SchemaFactory schemaFactory =
        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema(new File("garage.xsd"));

      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      factory.setSchema(schema);

      SAXParser parser = factory.newSAXParser();
      parser.parse(new File("badgarage.xml"), 
        new SAXValidatorHandler());
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }
}

class SAXValidatorHandler extends DefaultHandler {
  private Locator locator;
  public void setDocumentLocator(Locator loc) {
    locator = loc;
  }
  public void error(SAXParseException e) {
    System.err.println("Validation error on line " +
      locator.getLineNumber() + ":\n" + e.getMessage());
  }
  public void fatalError(SAXParseException e) {
    System.err.println("Well-formedness error on line " +
      locator.getLineNumber() + ":\n" + e.getMessage());
  }
}
