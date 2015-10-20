package labs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile2 implements Runnable{

	private int objNumber;
	private FileInputStream fileStream;
	private Thread thread;
	
	public static void main(String[] args) {
		String filename = "input.txt";
		ReadFile2 rf1 = new ReadFile2(filename, 1);
		ReadFile2 rf2 = new ReadFile2(filename, 2);
		ReadFile2 rf3 = new ReadFile2(filename, 3);
		
		rf1.start();
		rf2.start();
		rf3.start();

	}
	
	public ReadFile2(String filename, int objNumber){
		this.objNumber = objNumber;
		try {
			this.fileStream = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.thread = new Thread(this);
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
	
	public void start(){
		thread.start();
	}
	
}
