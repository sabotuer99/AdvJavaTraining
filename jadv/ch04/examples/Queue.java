package examples;

import java.util.List;
import java.util.LinkedList;

public class Queue<T> {
  private List<T> list;

  public Queue() {
    list = new LinkedList<T>();
  }

  public void enqueue(T i) {
    synchronized (this) {
      list.add(i);
      // tell all waiters that an item has arrived
      notifyAll();
    }
  }

  public T dequeue() {
    synchronized (this) {
      while (list.size() == 0) {
        // wait until an item arrives before returning it
        try {
          wait();
        }
        catch (InterruptedException e) {
          System.err.println(e);
        }
      }
      return list.remove(0);
    }
  }
}