package labs;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class AddNewCar {
	public static void main(String[] args){
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("garage.xml"));
			addVehicle(document);

			// Use a Transformer for output
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		    DOMSource source = new DOMSource(document);
		    Result xmlResult = new StreamResult("garage.xml");
		    transformer.transform(source, xmlResult);
		    
		    
		    //Source xmlSource = new StreamSource("garage.xml");
		    Source xslSource = new StreamSource("garage.xslt");
		    Result xmlResult2 = new StreamResult("garage.html");

		    TransformerFactory tFactory2 = TransformerFactory.newInstance();
		    Transformer transformer2 = tFactory2.newTransformer(xslSource);
		    transformer2.transform(source, xmlResult2);
		    
		} catch(Exception e){
			
		}
	}

	private static void addVehicle(Document document) {
		Scanner in = new Scanner(System.in);
		System.out.println("(C)ar, (V)an, or (Q)uit?");
		String choice = null;
		while(choice == null){
			choice = in.nextLine();
			switch(choice){
				case "q":
				case "Q":
					in.close();
					return;
				case "c":
				case "C":
					createVehicle("car", document);
					break;
				case "v":
				case "V":
					createVehicle("van", document);
					break;
				default:
					System.out.println("Invalid choice!");
					choice = null;
					break;
			}
		}
		in.close();
		
	}

	private static void createVehicle(String string, Document document) {
		Node garage = document.getElementsByTagName("garage").item(0);
		Element newVehicle = document.createElement(string);
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter Make:");
		String make = null;
		while(make == null){
			make = in.nextLine();
			if("".equals(make)){
				System.out.println("Cannot be blank!");
			}
		}
		
		System.out.println("Enter Model:");
		String model = null;
		while(model == null){
			model = in.nextLine();
			if("".equals(model)){
				System.out.println("Cannot be blank!");
			}
		}
		
		System.out.println("Enter Year:");
		String year = null;
		while(year == null){
			year = in.nextLine();
			if("".equals(model)){
				System.out.println("Cannot be blank!");
				year = null;
			} 
			else if(!Pattern.matches("(19|20)[0-9]{2}", year)) {
				System.out.println("Not a valid year!");
				year = null;
			} else {
				int parsedYear = Integer.parseInt(year);
				if(parsedYear < 1900 || parsedYear > 2016){
					System.out.println("Must be between 1900 and 2016!");
					year = null;
				}
			}
		}
		
		System.out.println("Enter Mileage:");
		String miles = null;
		while(miles == null){
			miles = in.nextLine();
			if("".equals(model)){
				System.out.println("Cannot be blank!");
				miles = null;
			} 
			else if(!Pattern.matches("\\d+", miles)) {
				System.out.println("Not a valid year!");
				miles = null;
			}
		}
		
		newVehicle.setAttribute("miles", miles);
		Element newModel = document.createElement("model");
		Element newMake = document.createElement("make");
		Element newYear = document.createElement("year");
		Node modelText = document.createTextNode(model);
		Node makeText = document.createTextNode(make);
		Node yearText = document.createTextNode(year);
		
		newModel.appendChild(modelText);
		newMake.appendChild(makeText);
		newYear.appendChild(yearText);
		
		newVehicle.appendChild(newMake);
		newVehicle.appendChild(newModel);
		newVehicle.appendChild(newYear);
		
		garage.appendChild(newVehicle);
		in.close();
	}
	
}
