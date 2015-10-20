package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionRace implements Runnable {
  static List<String> theList;

  public void run() {
    for (int i = 0; i < 20; i++) {
      // No longer need to synchronize the list
      // synchronized (theList) {
        theList.add(Thread.currentThread().getName() + i);
      //}
    }
  }
  
  public static void main(String[] args) throws Exception {
    CollectionRace race  = new CollectionRace();
    theList = Collections.synchronizedList(new ArrayList<String>());

    Thread thread1 = new Thread(race, "A");
    Thread thread2 = new Thread(race, "B");
    Thread thread3 = new Thread(race, "C");

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    Iterator<String> it = theList.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    System.out.println("Size of the list " + theList.size());
  }
}
