package solutions;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendFile {
  public static void main(String[] args) {
    String file = "data.txt";
    int port = 8000;
    byte[] data = null; // contents of the file

    try (FileInputStream in = new FileInputStream(file)) {
      data = new byte[in.available()];
      in.read(data);
    }
    catch (IOException e) {
      System.err.println("Error reading " + file + ": " + e);
      System.exit(1);
    }

    Socket s = null;
    try (ServerSocket ss = new ServerSocket(port)) {
      while (true) {
        // wait for the connection
        s = ss.accept();

        // build sockout from the socket's Output Stream
        OutputStream sockout = s.getOutputStream();

        // Tell the user that we've connected
        System.out.println("Connected to " + s.getInetAddress() + ":"
            + s.getPort());

        // Send the client the contents of the file
        sockout.write(data);

        s.close();
      }
    }
    catch (IOException e) {
      System.err.println(e);
    }
    finally {
      // Always be sure to close the socket to release resources
      if (s != null) {
        try {
          s.close();
        }
        catch (IOException ignore) {
        }
      }
    }
  }
}
