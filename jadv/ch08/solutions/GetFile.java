package solutions;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.InetAddress;

public class GetFile {
  public static void main(String[] args) {
    Socket s = null;
    BufferedReader sockin = null;

    try {
      // create the connection
      s = new Socket(InetAddress.getLocalHost(), 8000);

      // create a BufferedReader to read lines from the server output
      sockin = new BufferedReader(
        new InputStreamReader(s.getInputStream()));

      // Tell the user that we've connected
      System.out.println("Connected to " +
        s.getInetAddress() + ":"+ s.getPort());

      // read what the server sends
      String linein = null;
      while((linein = sockin.readLine()) != null) {
        System.out.println(linein);
      }
    }
    catch (IOException e) {
      System.err.println(e);
    }
    // Always be sure to close the socket to release resources
    finally {
      if (s != null) {
        try {
          s.close();
        }
        catch (IOException ignore) {}
      }
    }
  }
}
