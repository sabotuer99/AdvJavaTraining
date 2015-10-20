package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRace implements Callable<String> {
  static List<String> theList;

  public String call() {
    for (int i = 0; i < 20; i++) {
        theList.add(Thread.currentThread().getName() + " " + i);
    }
    return "Done";
  }
  
  public static void main(String[] args) throws Exception {
    theList = Collections.synchronizedList(new ArrayList<String>());

    ExecutorService ex = Executors.newCachedThreadPool();
    Future<String> listAResult = ex.submit(new CallableRace());
    Future<String> listBResult = ex.submit(new CallableRace());
    Future<String> listCResult = ex.submit(new CallableRace());

    // get() is a blocking call, can also use isDone()
    System.out.println("Task A is " + listAResult.get());
    System.out.println("Task B is " + listBResult.get());
    System.out.println("Task C is " + listCResult.get());
    ex.shutdown();

    Iterator<String> it = theList.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    System.out.println("Size of the list " + theList.size());
  }
}
