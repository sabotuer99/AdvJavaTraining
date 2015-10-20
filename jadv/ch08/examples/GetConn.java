package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetConn {
  public static void main(String[] args) {
    String contents = null;
    URLConnection conn = null;
    BufferedReader buf = null;
    try {
      URL myPage = new URL("http://www.java.com/");

      conn = myPage.openConnection();

      System.out.println("File type: " + conn.getContentType());

      int len = conn.getContentLength();
      if (len == -1)
        System.out.println("File length unknown");
      else
        System.out.println("File length: " + len);

      System.out.println("Hit RETURN to continue");
      System.in.read();

      buf = new BufferedReader(new InputStreamReader(
          conn.getInputStream()));

      while ((contents = buf.readLine()) != null) {
        System.out.println(contents);
      }
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
