package solutions;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class AddNewCar2 {
  public static void main (String args[]) {
    DocumentBuilderFactory factory =
      DocumentBuilderFactory.newInstance();

    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File("garage.xml") );

      addCar(document,"Saturn","SL2","2002");
      System.out.println("Car Added");

      Source xslSource = new StreamSource("garage.xslt");
      DOMSource domSource = new DOMSource(document);
      Result result = new StreamResult("garage.html");

      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer(xslSource);
      transformer.transform(domSource, result);
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }

  public static void addCar(Document d, String carMake,
      String carModel, String carYear) {

    Element car = d.createElement("car");

    Element make = d.createElement("make");
    Text text = d.createTextNode(carMake);
    make.appendChild(text);

    Element model = d.createElement("model");
    text = d.createTextNode(carModel);
    model.appendChild(text);

    Element year = d.createElement("year");
    text = d.createTextNode(carYear);
    year.appendChild(text);

    car.appendChild(make);
    car.appendChild(model);
    car.appendChild(year);
    car.setAttribute("miles", carYear);

    Element garage = d.getDocumentElement();
    garage.appendChild(car);
  }
}
