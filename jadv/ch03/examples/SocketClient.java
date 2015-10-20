// This is a simple echo client for testing against ConcurrentServer.
package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

  public static void main(String[] args) {
    Socket s = null;
    BufferedReader in = null;
    PrintWriter out = null;

    // use BufferedReader for ease of coding
    BufferedReader buf = new BufferedReader(new InputStreamReader(
        System.in));

    try {
      // create the connection
      s = new Socket("localhost", 7777);

      // build in and out from Socket's InputStream and OutputStream
      in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      out = new PrintWriter(s.getOutputStream(), true);

      // Tell the user that we've connected
      System.out.println("Connected to " + s.getInetAddress() + ":"
          + s.getPort());

      // loop, reading input, shooting it through the socket
      // and writing the returned data to the screen,
      // until the user types 'q'
      String linein = null;
      while (!((linein = buf.readLine()).equals("q"))) {
        out.println(linein);
        System.out.println(in.readLine());
      }
    }
    catch (IOException e) {
      System.err.println(e);
    }
    // Always be sure to close the socket to release resources
    finally {
      try {
        if (s != null) {
          s.close();
        }
      }
      catch (IOException e) {
      }
    }
  }
}
