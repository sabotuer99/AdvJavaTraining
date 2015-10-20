package solutions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteDept {
  public static void main(String args[]) throws ParseException {
    SimpleDateFormat f = new SimpleDateFormat("M/d/yyyy");
    Date d = f.parse("08/05/1960");
    Employee armstrong = new Employee(5, "Neil", "Armstrong",
        "Astronaut", d);
    Department space = new Department("Space Division", armstrong);

    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("dept.ser"))) {

      out.writeObject(space);
      System.out.println("Wrote \"" + space + "\" to file");
    }
    catch (IOException e) {
      System.err.println(e);
    }
  }
}
