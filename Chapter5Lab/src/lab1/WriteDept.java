package lab1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class WriteDept{

	  public static void main(String args[]) {
		  
		    Employee hrManager = new Employee("1","SpongeBob","SquarePants","HR Manager",new Date(System.currentTimeMillis()));
		    
		    Department hr = new Department();
		    hr.setManager(hrManager);
		    hr.setName("Humane Resources");

		    try (ObjectOutputStream out = new ObjectOutputStream(
		          new FileOutputStream("hr.ser"))) {

		      out.writeObject(hr);
		      System.out.println("Wrote \"" + hr.getName() + "\" to file");
		    }
		    catch (IOException e) {
		      System.err.println("Error writing object: " + e.getMessage());
		    }
		  }
	
}
