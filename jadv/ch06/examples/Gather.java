package examples;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gather {
  public static void main(String[] args) {
    float[] temps = { 42.0f, 56.0f, 80.0f, 78.0f };
    String[] cities = { "Juneau", "Denver", "Fresno", "Helena" };

    Path file = Paths.get("mixed");
    try (FileChannel fc = FileChannel.open(file, CREATE, WRITE)) {

      ByteBuffer[] bufs = new ByteBuffer[2];

      // write the temperatures
      bufs[0] = ByteBuffer.allocate(temps.length * 4);
      FloatBuffer fbuf = bufs[0].asFloatBuffer();
      fbuf.put(temps);

      // write the cities
      bufs[1] = ByteBuffer.allocate(cities.length * 2 * 7);
      CharBuffer cbuf = bufs[1].asCharBuffer();
      for (String city : cities) {
        cbuf.put(city + " ");
      }

      fc.write(bufs);
      System.out.println("Wrote the data to mixed file.");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
