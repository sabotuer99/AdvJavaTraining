package solutions;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferlessCopy {
  public static void main(String[] args) {
    Path fileIn = Paths.get("examples/Scatter.java");
    Path fileOut = Paths.get("JavaSource");
    try (FileChannel fcIn = FileChannel.open(fileIn, READ);
        FileChannel fcOut = FileChannel.open(fileOut, CREATE, WRITE)) {

      fcIn.transferTo(0, fcIn.size(), fcOut);

      fcIn.close();
      fcOut.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
