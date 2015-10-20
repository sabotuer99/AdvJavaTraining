package solutions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class AdminClient {
  public static void main(String args[]) {
    // Connect to the server's admin port
    try (Socket s = new Socket(InetAddress.getLocalHost(), 9000)) {
      ObjectInputStream sockin = new ObjectInputStream(
          s.getInputStream());

      // Tell the user that we've connected
      System.out.println("Connected to " + s.getInetAddress() + ":"
          + s.getPort());

      // get the statistics object
      ServerStatistics stats = (ServerStatistics) sockin.readObject();
      System.out.println(stats);
    }
    catch (IOException ioe) {
      System.err.println("Error connecting to server: " + ioe);
    }
    catch (ClassNotFoundException e) {
      System.err.println("Error reading ServerStatistics obj from admin server " + e);
    }
  }
}
