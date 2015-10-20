package solutions;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class AddNewCar {
  public static void main (String args[]) {
    DocumentBuilderFactory factory =
      DocumentBuilderFactory.newInstance();

    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File("garage.xml") );
      addCar(document,"Saturn", "SL2", "2002");
      System.out.println("Car Added");
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
