package examples;

import static java.nio.file.StandardOpenOption.READ;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadLog1 {
  public static void main(String[] args) {
    Path file = Paths.get("access_log");
    try (FileChannel channel = FileChannel.open(file, READ)) {

      ByteBuffer bbuf = ByteBuffer.allocate(1024);
      CharBuffer cbuf = bbuf.asCharBuffer();
      int count = channel.read(bbuf);
      char[] chars = new char[count / 2];
      cbuf.get(chars);
      for (char c : chars) {
        System.out.print(c);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
