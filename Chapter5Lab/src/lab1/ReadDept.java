package lab1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ReadDept {
	  public static void main(String args[]) {

		    try (ObjectInputStream in = new ObjectInputStream(
		        new FileInputStream("hr.ser"))) {

		      Department hr = (Department) in.readObject();
		      System.out.println(hr.getName());
		      System.out.println("Managed by: ");
		      System.out.println(hr.getManager().toString());
		    }
		    catch (ClassCastException e) {
		      System.err.println("Error casting object to a Person");
		    }
		    catch (ClassNotFoundException e) {
		      System.err.println("Class not found");
		    }
		    catch (IOException e) {
		      System.err.println("Error reading object: " + e.getMessage());
		    }
		  }
}
