package solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListExample {
  public static void main(String[] args) {
    List<String> theList = new ArrayList<>();

    // add 20000 items
    long start = System.nanoTime();
    for (int i = 0; i < 20000; i++) {
      theList.add("Item" + i);
    }
    long end = System.nanoTime();

    System.out
        .printf("Add 20000 items took: %,d ns.%n", (end - start));

    // insert 10000 items into the middle
    start = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      theList.add(0, "Item" + i);
    }
    end = System.nanoTime();

    System.out.printf(
        "Insert 10000 items at the beginning took: %,d ns.%n",
        (end - start));

    // insert 10000 items into the middle
    start = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      theList.add(15000, "Item" + i);
    }
    end = System.nanoTime();

    System.out.printf(
        "Insert 10000 items in the middle took: %,d ns.%n",
        (end - start));

    // insert 10000 items at the end
    start = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      theList.add("Item" + i);
    }
    end = System.nanoTime();

    System.out.printf("Add 10000 items at the end took: %,d ns.%n",
        (end - start));

    // retrieve 50000 items
    start = System.nanoTime();
    for (int i = 4999; i >= 0; i--) {
      theList.get(i);
    }
    end = System.nanoTime();

    System.out.printf(
        "Retrieve 50000 items in reverse order took: %,d ns.%n",
        (end - start));

    // remove 5000 items after index 25000
    start = System.nanoTime();
    Iterator<String> it = theList.iterator();
    int counter = 0;
    while (it.hasNext()) {
      it.next();
      if (counter >= 25000 && counter < 30000) {
        it.remove();
      }
      counter++;
    }
    end = System.nanoTime();

    System.out.printf("Remove 5000 items after 25000 took: %,d ns.%n",
        (end - start));
  }
}
