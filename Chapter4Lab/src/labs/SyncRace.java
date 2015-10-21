package labs;

import java.util.ArrayList;
import java.util.Iterator;

public class SyncRace implements Runnable {
  static ArrayList<String> theList;

  public void run() {
    for (int i = 0; i < 200000; i++) {
      theList.add(Thread.currentThread().getName() + i);
    }
  }

  public static void main(String[] args) throws Exception {
    SyncRace race = new SyncRace();
    theList = new ArrayList<>();

    Thread thread1 = new Thread(race, "A");
    Thread thread2 = new Thread(race, "B");
    Thread thread3 = new Thread(race, "C");

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

//    Iterator<String> it = theList.iterator();
//    while (it.hasNext()) {
//      System.out.println(it.next());
//    }

    System.out.println("Size of the list " + theList.size());
  }
}