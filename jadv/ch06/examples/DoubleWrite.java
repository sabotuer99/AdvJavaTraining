package examples;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DoubleWrite {
  public static void main(String[] args) {
    Path file = Paths.get("constants");
    
    // write PI and E to file
    try (FileChannel fcOut = FileChannel.open(file, CREATE, WRITE)) {

      ByteBuffer bbuf = ByteBuffer.allocate(16);
      DoubleBuffer dbuf = bbuf.asDoubleBuffer();
      dbuf.put(Math.PI);
      dbuf.put(Math.E);
      fcOut.write(bbuf);
      System.out.println("Wrote math constants to constants file.");

    }
    catch (Exception e) {
      e.printStackTrace();
    }

    // read PI and E from file
    try (FileChannel fcIn = FileChannel.open(file, READ)) {
      
      ByteBuffer bbuf2 = ByteBuffer.allocate(16);
      DoubleBuffer dbuf2 = bbuf2.asDoubleBuffer();      
      fcIn.read(bbuf2);      
      double pi = dbuf2.get();
      double e = dbuf2.get();
      System.out.println("pi = " + pi + " e = " + e);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
