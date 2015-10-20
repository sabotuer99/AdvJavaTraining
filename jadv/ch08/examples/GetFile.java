package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetFile {
  public static void main(String[] args) {
    String contents = null;
    BufferedReader buf = null;
    try {
      URL myPage = new URL("http://www.java.com/");

      buf = new BufferedReader(new InputStreamReader(
          myPage.openStream()));

      while ((contents = buf.readLine()) != null) {
        System.out.println(contents);
      }
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
