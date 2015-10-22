package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		Socket clientSocket = null;
		try (ServerSocket listenSocket = new ServerSocket(7777)){
			while(true){
				clientSocket = listenSocket.accept();
				
				BufferedReader sockin = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter sockout = new PrintWriter(
						clientSocket.getOutputStream(), true);
				
				String linein;
				
				//Read from socket until client closes its end
				while((linein = sockin.readLine()) != null) {
					System.err.println("Server: " + linein);
					sockout.println("You said: " + linein);
				}
				System.err.println("Server: Connection closed.");
				
				//close resources
				sockin.close();
				sockout.close();
				clientSocket.close();
							
			}
		}
		catch(IOException ex){
			System.err.println(ex.getMessage());
		}
		finally{
			try{
				if(clientSocket != null){
					clientSocket.close();
				}
			}
			catch(IOException ex){}
		}

	}

}
