package examples;

public class ThreadApp3 {
  public static void main(String[] args) {
    PrintNumbers4 p1 = new PrintNumbers4(1, 2); // odds
    PrintNumbers4 p2 = new PrintNumbers4(2, 2); // evens
    p1.setName("Odds");
    p2.setName("Evens");
    p1.start();
    p2.start();
  }
}

class PrintNumbers4 extends Thread {
  private int start = 0;
  private int increment = 1;

  public PrintNumbers4(int st, int inc) {
    start = st;
    increment = inc;
  }

  public void print() {
    int i, j;
    for (i = start, j = 0; j < 20; j++, i += increment) {
      System.out.println(this.getName() + " " + i);
      Thread.yield();
    }
  }

  @Override
  public void run() {
    print();
  }
}
