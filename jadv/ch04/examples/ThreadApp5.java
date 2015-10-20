package examples;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadApp5 {
  public static void main(String[] args) {
    ExecutorService ex = Executors.newFixedThreadPool(2);
    ex.execute(new PrintNumbers6( 1, 2 ));
    ex.execute(new PrintNumbers6( 2, 2 ));
    ex.shutdown();
    //ex.shutdownNow();
  }
}

class PrintNumbers6 implements Runnable {
  private int start=0;
  private int increment=1;

  public PrintNumbers6(int st, int inc) {
    start = st;
    increment = inc;
  }

  public void print() {
    int i, j;
    for (i=start, j=0; j<20; j++, i+=increment) {      
      System.out.println(Thread.currentThread().getName() + " " + i);
      
      if (Thread.interrupted()) {
        break;
      }
      Thread.yield();
    }
  }

  public void run() {
    print();
  }
}
