package examples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObj {
  public static void main(String args[]) {
    Person armstrong = new Person("Neil Armstrong", "Astronaut",
        "08/05/1930", 1);

    try (ObjectOutputStream out = new ObjectOutputStream(
          new FileOutputStream("astronaut.ser"))) {

      out.writeObject(armstrong);
      System.out.println("Wrote \"" + armstrong + "\" to file");
    }
    catch (IOException e) {
      System.err.println("Error writing object: " + e.getMessage());
    }
  }
}
