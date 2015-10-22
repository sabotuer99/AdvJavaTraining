package lab;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SendFile{

	public static void main(String[] args) {
		FileServer fs = new SendFile().new FileServer();
		fs.start();

	}
	
	class FileServer extends Thread{
		public void listenFileRequests(){
			Socket clientSocket = null;
			try (ServerSocket listenSocket = new ServerSocket(7778)){
				while(true){
					clientSocket = listenSocket.accept();
					
					PrintWriter sockout = new PrintWriter(
							clientSocket.getOutputStream(), true);
					
					Scanner in = new Scanner(new FileInputStream("data.txt"));	
					
					while(in.hasNext()){
						sockout.println(in.nextLine());
					}
					
					sockout.close();
					clientSocket.close();	
					in.close();
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
		
		@Override
		public void run(){
			listenFileRequests();
		}
	}
	
	class AdminServer extends Thread{
		public void listenFileRequests(){
			Socket clientSocket = null;
			try (ServerSocket listenSocket = new ServerSocket(9999)){
				while(true){
					clientSocket = listenSocket.accept();
					
					PrintWriter sockout = new PrintWriter(
							clientSocket.getOutputStream(), true);
					
//					Scanner in = new Scanner(new FileInputStream("data.txt"));	
//					
//					while(in.hasNext()){
//						sockout.println(in.nextLine());
//					}
					
					sockout.close();
					clientSocket.close();	
					//in.close();
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
		
		@Override
		public void run(){
			listenFileRequests();
		}
	}
}
