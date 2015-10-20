package solutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadJunk {
  public static void main(String[] args) {

    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("junk.txt"))) {

      Department d = (Department) in.readObject();
      System.out.println(d);
    }
    catch (ClassCastException | IOException | ClassNotFoundException e) {
      System.out.println(e);
    }
  }
}
