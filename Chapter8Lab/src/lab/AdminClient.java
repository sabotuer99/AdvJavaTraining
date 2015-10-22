package lab;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AdminClient {

	public static void main(String[] args) {
	    try (Socket s = new Socket("localhost", 9999)){

	    	InputStream input = s.getInputStream();
	    	
	    	byte[] content = streamToBytes(input);
	    	
	        ByteArrayInputStream in = new ByteArrayInputStream(content);
	        ObjectInputStream is = new ObjectInputStream(in);
	        ServerStats stats = (ServerStats) is.readObject();
	    	
	        System.err.println("Connections: " + stats.getConnectionCount());
	        System.err.println("File name: " + stats.getFileName());
	        System.err.println("Port: " + stats.getPort());
	        
	    	//Close input stream
	    	input.close();
	    	is.close();
	    }
	    catch (UnknownHostException e) {
	    	System.err.println("Unknown host: " + e.getMessage());
	    	System.exit(1);
	    } catch (IOException e) {
	    	System.err.println(e.getMessage());
	    	System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] streamToBytes(InputStream is){
		
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				int nRead;
				byte[] data = new byte[16384];

				try {
					while ((nRead = is.read(data, 0, data.length)) != -1) {
					  buffer.write(data, 0, nRead);
					}
					buffer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return buffer.toByteArray();
	}

}
