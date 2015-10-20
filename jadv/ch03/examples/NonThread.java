package examples;

public class NonThread {
  public static void main(String[] args) {
    PrintNumbers p1 = new PrintNumbers(1, 2); // odds
    PrintNumbers p2 = new PrintNumbers(2, 2); // evens
    p1.print();
    p2.print();
  }
}

class PrintNumbers {
  private int start = 0;
  private int increment = 1;

  public PrintNumbers(int st, int inc) {
    start = st;
    increment = inc;
  }

  public void print() {
    int i, j;
    for (i = start, j = 0; j < 20; j++, i += increment) {
      System.out.println(i);
      try {
		Thread.yield();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
  }
}
