package solutions;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class WriteCharsets2 {
  public static void main(String[] args) {
    Path file = Paths.get("charsets2");
    try (FileChannel channel = FileChannel.open(file, CREATE, WRITE)) {
      ByteBuffer buf = ByteBuffer.allocate(1024);
      CharBuffer cbuf = buf.asCharBuffer();

      Map<String, Charset> charsets = Charset.availableCharsets();
      for (String csName : charsets.keySet()) {
        if (csName.length() + 1 > (cbuf.limit() - cbuf.position())) {
          channel.write(buf);
          buf.clear();
          cbuf.clear();
        }
        cbuf.put(csName);
        cbuf.put('\n');
      }
      channel.write(buf);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
