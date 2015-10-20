package examples;

import java.util.Random;

public class QueueTester {
  public static void main(String[] args) {
    Queue<Integer> q = new Queue<>();
    Thread adder = new Thread(new QueueAdder(q), "Adder");
    Thread remover = new Thread(new QueueRemover(q), "Remover");
    adder.start();
    remover.start();
  }
}

class QueueAdder implements Runnable {
  Queue<Integer> theQueue;

  public QueueAdder(Queue<Integer> q) {
    theQueue = q;
  }

  public void run() {
    int i = 0;
    Random rand = new Random();
    while (true) {
      try {
        // sleep between 0 and 1000 milliseconds
        Thread.sleep((int) (rand.nextFloat() * 1000));
      }
      catch (InterruptedException e) {
        System.err.println(e);
      }

      theQueue.enqueue(i++);
    }
  }
}

class QueueRemover implements Runnable {
  Queue<Integer> theQueue;

  public QueueRemover(Queue<Integer> q) {
    theQueue = q;
  }

  public void run() {
    while (true) {
      System.out.println(theQueue.dequeue());
    }
  }
}