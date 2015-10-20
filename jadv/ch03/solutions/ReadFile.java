package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile extends Thread {
  private int id;
  private String filename;

  public ReadFile(String fn, int i) {
    id = i;
    filename = fn;
  }

  @Override
  public void run() {
    String line = null;
    int count = 0;

    try (BufferedReader input = new BufferedReader(new FileReader(
        filename))) {

      while ((line = input.readLine()) != null) {
        System.out.println("Object " + id + "->" + line);
        if ((++count % 5) == 0)
          Thread.yield();
      }
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
  
  public static void main(String[] args) {
    ReadFile[] readers = new ReadFile[3];

    for (int i = 0; i < readers.length; i++) {
      // read source code file
      readers[i] = new ReadFile("solutions/ReadFile.java", i);
      readers[i].start();
    }
  }  
}
