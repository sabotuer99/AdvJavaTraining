package lab;

import java.io.Serializable;

public class ServerStats implements Serializable {
	
	private static final long serialVersionUID = 256456188834978152L;
	private String fileName;
	private int port;
	private int connectionCount;
	
	public void incrementCount(){
		connectionCount++;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getConnectionCount() {
		return connectionCount;
	}
	public void setConnectionCount(int connectionCount) {
		this.connectionCount = connectionCount;
	}
	
	
}
