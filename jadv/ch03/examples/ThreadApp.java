package examples;

public class ThreadApp {
  public static void main(String[] args) {
    PrintNumbers2 p1 = new PrintNumbers2(1, 2); // odds
    PrintNumbers2 p2 = new PrintNumbers2(2, 2); // evens
    p1.start();
    p2.start();
  }
}

class PrintNumbers2 extends Thread {
  private int start = 0;
  private int increment = 1;

  public PrintNumbers2(int st, int inc) {
    start = st;
    increment = inc;
  }

  public void print() {
    int i, j;
    for (i = start, j = 0; j < 20; j++, i += increment) {
      System.out.println(i);
      try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
  }

  @Override
  public void run() {
    print();
  }
}
