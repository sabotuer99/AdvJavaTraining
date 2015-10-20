package solutions;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GetImage {
  public static void main(String args[]) {
    // Prompt for image URL
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter an image URL: ");
    String urlString = scanner.next();

    try {
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      // connect explicitly to catch connection problem exceptions
      conn.connect();

      // Check the content-type to make sure it is an image
      String contentType = conn.getContentType();
      if (contentType.startsWith("image/")) {

        // read the image into a byte array
        int contentLength = conn.getContentLength();
        byte[] content = new byte[contentLength];
        InputStream is = conn.getInputStream();
        // read method may read all the data at once or
        // across several reads, loop to read all the data
        int offset = 0;
        int len = contentLength;
        while (offset != contentLength) {
          int bytesRead = is.read(content, offset, len);
          offset += bytesRead;
          len -= bytesRead;
        }

        // extract the specific image type from the content-type value
        // to build the name of the new file
        String[] tokens = contentType.split("/");
        String fileName = "imageCopy." + tokens[1];
        Path file = Paths.get(fileName);
        // write the copy using NIO
        try (FileChannel ch = FileChannel.open(file, CREATE, WRITE)) {
          ByteBuffer bb = ByteBuffer.wrap(content);
          ch.write(bb);
          System.out.println("File written: " + fileName);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      else {
        System.err.println(urlString + " is not an image");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
