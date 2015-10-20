package examples;

import static java.nio.file.StandardOpenOption.READ;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Scatter {
  public static void main(String[] args) {
    Path file = Paths.get("mixed");
    try (FileChannel fc = FileChannel.open(file, READ)) {

      ByteBuffer[] bufs = new ByteBuffer[2];

      // set up the temperatures buffer
      bufs[0] = ByteBuffer.allocate(16);
      FloatBuffer fbuf = bufs[0].asFloatBuffer();

      // set up the cities buffer
      bufs[1] = ByteBuffer.allocate(100);
      CharBuffer cbuf = bufs[1].asCharBuffer();

      int bytes = (int) fc.read(bufs);

      // read the temperatures
      float[] temps = new float[4];
      fbuf.get(temps);

      // read the cities
      char[] cCities = new char[(bytes - 16) / 2];
      cbuf.get(cCities);
      String sCities = new String(cCities);
      String[] cities = sCities.split("\\s");

      for (int i = 0; i < temps.length; i++) {
        System.out.println(cities[i] + ": " + temps[i]);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
