package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketHandler {
  public static String echo(String host, int port, String input) {
    StringBuilder receive = new StringBuilder();
    try (Socket s = new Socket(host, port)){
    	
    	//Send output to server
    	PrintWriter out = 
    			new PrintWriter(s.getOutputStream(), true);
    	
    	out.println(input);
    	out.println("\u0004");
    	
    	//Retrieve input from server
    	String line;
    	BufferedReader in = new BufferedReader(
    			new InputStreamReader(s.getInputStream()));
    	
    	line = in.readLine();
    	while(!line.contains("\u0004")){
    		System.err.println("Client: " + line);
    		receive.append(line + "\n");
    		line = in.readLine();
    	}
    	System.err.println("Echo finished");
    	
    	//Close input and output streams
    	out.close();
    	in.close();
    	
    }
    catch (UnknownHostException e) {
    	System.err.println("Unknown host: " + e.getMessage());
    	System.exit(1);
    } catch (IOException e) {
    	System.err.println(e.getMessage());
    	System.exit(1);
	}
    return receive.toString();
  }
}
