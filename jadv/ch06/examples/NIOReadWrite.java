package examples;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOReadWrite {
  public static void main(String[] args) {
    Path file = Paths.get("access_log");
    try (FileChannel channel = FileChannel.open(file, READ, WRITE)) {
      ByteBuffer buf = ByteBuffer.allocate(1024);
      byte[] bytes = null;
      int count = -1;

      while ((count = channel.read(buf)) != -1) {
        bytes = new byte[count];
        buf.flip();
        buf.get(bytes);
        for (byte b : bytes) {
          System.out.print((char) b);
        }
        buf.clear();
      }

      // write to file
      buf.put("Hello".getBytes());
      buf.flip();
      channel.write(buf);
      System.out.println("Wrote string \"Hello\" to access_log.");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
