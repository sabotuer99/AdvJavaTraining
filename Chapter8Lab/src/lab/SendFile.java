package lab;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SendFile{
	
	public static void main(String[] args) {
		
		ServerStats stats = new ServerStats();
		stats.setPort(7778);
		stats.setFileName("data.txt");
		stats.setConnectionCount(0);
		
		FileServer fs = new SendFile().new FileServer(stats);
		fs.start();
		AdminServer as = new SendFile().new AdminServer(stats);
		as.start();

	}
	
	class FileServer extends Thread{
		
		ServerStats stats;
		
		public FileServer(ServerStats stats){
			this.stats = stats;
		}
		
		
		public void listenFileRequests(){
			Socket clientSocket = null;
			try (ServerSocket listenSocket = new ServerSocket(7778)){
				while(true){
					clientSocket = listenSocket.accept();
					stats.incrementCount();
					
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

		ServerStats stats;
		
		public AdminServer(ServerStats stats){
			this.stats = stats;
		}
		
		public void listenAdminRequests(){
			Socket clientSocket = null;
			try (ServerSocket listenSocket = new ServerSocket(9999)){
				while(true){
					clientSocket = listenSocket.accept();
					stats.incrementCount();
					
					OutputStream sockout = clientSocket.getOutputStream();
					
					ByteArrayOutputStream out = new ByteArrayOutputStream();
				    ObjectOutputStream os = new ObjectOutputStream(out);
				    os.writeObject(stats);
				    
					byte[] bytes = out.toByteArray();
								
					sockout.write(bytes);
					
					sockout.close();
					clientSocket.close();	
					os.close();
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
			listenAdminRequests();
		}
	}
}
