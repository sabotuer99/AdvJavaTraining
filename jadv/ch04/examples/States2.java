package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class States2 {

  public static void main(String[] args) {
    List<String> nonThreadSafeStates = getListOfStatesAndTerritories();

    // Create a thread safe version of the list
    CopyOnWriteArrayList<String> states = new CopyOnWriteArrayList<>(
        nonThreadSafeStates);

    // In a separate thread, remove US territories, leaving only states
    removeTerritories(states);

    // Loop through the list, displaying each value
    Iterator<String> it = states.iterator();
    while (it.hasNext()) {
      System.out.print(it.next());
      if (it.hasNext())
        System.out.print(", ");
      else
        System.out.println();
    }
  }

  private static void removeTerritories(final List<String> states) {
    new Thread(new Runnable() {
      public void run() {
        for (int i = 0; i < states.size(); i++) {
          String state = states.get(i);
          switch (state) {
          case "AS":
          case "GU":
          case "PR":
          case "VI":
            // Since this is done in a CopyOnWriteArrayList, each call
            // to remove results in a new copy of the backing array.
            // Any existing Iterators will use the old copy, all new
            // Iterators will use the new copy.
            states.remove(i);
            break;
          }
        }
      }
    }).start();
  }

  private static List<String> getListOfStatesAndTerritories() {
    return new ArrayList<String>(Arrays.asList("AK", "AL", "AR", "AS",
        "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "GU", "HI", "IA",
        "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI",
        "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
        "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD",
        "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"));
  }
}
