package solutions;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class WriteCharsets {
  public static void main(String[] args) {
    Path file = Paths.get("charsets");
    try (FileChannel channel = FileChannel.open(file, CREATE, WRITE)) {
      ByteBuffer buf = ByteBuffer.allocate(1024);

      Map<String, Charset> charsets = Charset.availableCharsets();
      for (String csName : charsets.keySet()) {
        if (csName.length() + 1 > buf.limit() - buf.position()) {
          buf.flip();
          channel.write(buf);
          buf.clear();
        }
        buf.put(csName.getBytes());
        buf.put((byte) '\n');
      }
      buf.flip();
      channel.write(buf);
      System.out.println("File written.");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
