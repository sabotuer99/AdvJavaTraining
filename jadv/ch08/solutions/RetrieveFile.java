package solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class RetrieveFile {
  public static void main(String[] args) {
    try (Socket s = new Socket(InetAddress.getLocalHost(), 8000)) {
      // create a BufferedReader to read lines from the server output
      BufferedReader sockin = new BufferedReader(new InputStreamReader(
          s.getInputStream()));

      // Tell the user that we've connected
      System.out.println("Connected to " + s.getInetAddress() + ":"
          + s.getPort());

      // read what the server sends
      String linein = null;
      while ((linein = sockin.readLine()) != null) {
        System.out.println(linein);
      }
    }
    catch (IOException e) {
      System.err.println(e);
    }
  }
}
