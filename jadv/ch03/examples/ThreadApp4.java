package examples;

public class ThreadApp4 {
  public static void main(String[] args) {
    PrintNumbers5 p1 = new PrintNumbers5(1, 2); // odds
    PrintNumbers5 p2 = new PrintNumbers5(2, 2); // evens
    p1.setThreadName("Odds");
    p2.setThreadName("Evens");
    p1.startThread();
    p2.startThread();
  }
}

class PrintNumbers5 implements Runnable {
  private int start = 0;
  private int increment = 1;
  private Thread theThread = null;

  public PrintNumbers5(int st, int inc) {
    start = st;
    increment = inc;
    theThread = new Thread(this);
  }

  public void print() {
    int i, j;
    for (i = start, j = 0; j < 20; j++, i += increment) {
      System.out.println(theThread.getName() + " " + i);
      Thread.yield();
    }
  }

  public void run() {
    print();
  }

  public void startThread() {
    theThread.start();
  }

  public void setThreadName(String name) {
    theThread.setName(name);
  }
}
