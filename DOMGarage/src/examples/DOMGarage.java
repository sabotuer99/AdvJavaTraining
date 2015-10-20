package examples;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DOMGarage {
	public static void main (String args[]){
		
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("garage.xml"));
			System.out.println("Vehicles In My Garage\n");
			searchForVehicles(document);
			System.out.println("Garage Door Closes");
			
		} catch(Exception e){
			
		}
	}

	public static void searchForVehicles(Document doc) {

		NodeList list = doc.getElementsByTagName("car");
		processVehiclesList(list);
		list = doc.getElementsByTagName("van");
		processVehiclesList(list);
	}

	public static void processVehiclesList(NodeList list) {
		
		for (int i = 0; i < list.getLength(); i++){
			Node auto = list.item(i);
			NodeList autoFeatures = auto.getChildNodes();
			
			for(int j = 0; j < autoFeatures.getLength(); j++){
				Node featureNode = autoFeatures.item(j);
				
				if(featureNode.getNodeType() == Node.ELEMENT_NODE){
					String name = featureNode.getNodeName();
					
					if(name.equals("make") || name.equals("model")){
						System.out.println(name + " : " + 
								featureNode.getFirstChild().getNodeValue());
					}
				}
				//System.out.println();
			}
			System.out.println();
		}
	}
	
	
}
