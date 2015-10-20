package solutions;

import java.io.*;
import java.net.*;

public class SocketHandler {
  public static String echo(String host, int port, String input) {
    StringBuilder receive = new StringBuilder();
    try (Socket s = new Socket(host, port)) {

      // Send ouput to server
      PrintWriter out = 
          new PrintWriter(s.getOutputStream(), true);

      out.println(input);
      out.println("\u0004");

      // Retrieve input from server
      String line;
      BufferedReader in = new BufferedReader(
          new InputStreamReader(s.getInputStream()));

      while (!((line = in.readLine()).equals("\u0004"))) {
        receive.append(line + "\n");
      }

      // Close input and output streams
      out.close();
      in.close();
    }
    catch (UnknownHostException e) {
      System.err.println("Unknown Host: " + e.getMessage());
      System.exit(1);
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(2);
    }
    return receive.toString();
  }
}
