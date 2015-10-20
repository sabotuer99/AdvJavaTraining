package examples;

public class ThreadApp2 {
  public static void main(String[] args) {
    PrintNumbers3 p1 = new PrintNumbers3(1, 2); // odds
    PrintNumbers3 p2 = new PrintNumbers3(2, 2); // evens
    p1.setName("Odds");
    p2.setName("Evens");
    p1.start();
    p2.start();
  }
}

class PrintNumbers3 extends Thread {
  private int start = 0;
  private int increment = 1;

  public PrintNumbers3(int st, int inc) {
    start = st;
    increment = inc;
  }

  public void print() {
    int i, j;
    for (i = start, j = 0; j < 20; j++, i += increment) {
      System.out.println(this.getName() + " " + i);
    }
  }

  @Override
  public void run() {
    print();
  }
}
