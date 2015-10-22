package lab;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.SortedMap;

public class WhiteCharsets {

	public static void main(String[] args) {

		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		File file = new File("charsets.txt");
		
		try(FileChannel channel = FileChannel.open(file.toPath(), READ, WRITE)){
			
		      ByteBuffer buf = ByteBuffer.allocate(1024);
		      
		      //iterate over charsets in charSets
		      for (String csName : charSets.keySet()) {
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
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
