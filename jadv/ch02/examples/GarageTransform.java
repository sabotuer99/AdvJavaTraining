package examples;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class GarageTransform {
  public static void main(String[] args) throws Exception {
    Source xmlSource = new StreamSource("garage.xml");
    Source xslSource = new StreamSource("garage.xslt");
    Result xmlResult = new StreamResult("garage.html");

    TransformerFactory tFactory = TransformerFactory.newInstance();
    Transformer transformer = tFactory.newTransformer(xslSource);
    transformer.transform(xmlSource, xmlResult);
  }
}
