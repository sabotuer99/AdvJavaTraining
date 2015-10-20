package solutions;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyScatter {
  public static void main(String[] args) {
    Path fileIn = Paths.get("examples/Scatter.java");
    Path fileOut = Paths.get("JavaSource");
    try (FileChannel fcIn = FileChannel.open(fileIn, READ);
        FileChannel fcOut = FileChannel.open(fileOut, CREATE, WRITE)) {

      ByteBuffer buf = ByteBuffer.allocate(1024);
      while ((fcIn.read(buf)) != -1) {
        buf.flip();
        fcOut.write(buf);
        buf.clear();
      }
      System.out.println("Copy complete.");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
