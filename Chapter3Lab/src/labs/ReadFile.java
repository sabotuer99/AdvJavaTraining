package labs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile extends Thread {

	private int objNumber;
	private FileInputStream fileStream;
	
	public static void main(String[] args) {
		String filename = "input.txt";
		ReadFile rf1 = new ReadFile(filename, 1);
		ReadFile rf2 = new ReadFile(filename, 2);
		ReadFile rf3 = new ReadFile(filename, 3);
		
		rf1.start();
		rf2.start();
		rf3.start();

	}
	
	public ReadFile(String filename, int objNumber){
		this.objNumber = objNumber;
		try {
			this.fileStream = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void print(){
		Scanner in = new Scanner(fileStream);
		for(int i = 0; i < 5 && in.hasNext(); i++){
			System.out.println(objNumber + " : " + in.nextLine());
			Thread.yield();
		}
	}
	
	@Override
	public void run(){
		print();
	}
	
}
