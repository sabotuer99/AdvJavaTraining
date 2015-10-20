package solutions;

import java.io.*;
import java.net.*;

public class EchoServer {
  public static void main(String[] args) {
    Socket clientSocket = null;
    try (ServerSocket listenSocket = new ServerSocket(7777)) {
      while (true) {
        clientSocket = listenSocket.accept();

        BufferedReader sockin = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter sockout = new PrintWriter(
            clientSocket.getOutputStream(), true);

        String linein;
        // Read from socket until client closes its end
        while ((linein = sockin.readLine()) != null) {
          sockout.println(linein); // echo message back
          System.err.println("Server: " + linein);
        }
        System.err.println("Server: Connection closed");

        // close resources
        sockin.close();
        sockout.close();
        clientSocket.close();
      }
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
    finally {
      try {
        if (clientSocket != null) {
          clientSocket.close();
        }
      }
      catch (IOException e) { }
    }
  }
}
