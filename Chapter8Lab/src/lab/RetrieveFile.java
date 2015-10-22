package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class RetrieveFile {

	public static void main(String[] args) {

		    try (Socket s = new Socket("localhost", 7778)){

		    	String line;
		    	BufferedReader in = new BufferedReader(
		    			new InputStreamReader(s.getInputStream()));
		    	
		    	
		    	while((line = in.readLine()) != null){
		    		System.err.println(line);
		    	}
		    	System.err.println("Read finished");
		    	
		    	//Close input stream
		    	in.close();
		    	
		    }
		    catch (UnknownHostException e) {
		    	System.err.println("Unknown host: " + e.getMessage());
		    	System.exit(1);
		    } catch (IOException e) {
		    	System.err.println(e.getMessage());
		    	System.exit(1);
			}

	}

}
