package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class States {

  public static void main(String[] args) {
    List<String> nonThreadSafeStates = getListOfStatesAndTerritories();
    // Create a "Thread Safe" version of the list
    List<String> states = Collections
        .synchronizedList(nonThreadSafeStates);

    // In a separate thread, remove US territories, leaving only states
    removeTerritories(states);

    // Loop through the list, displaying each value
    synchronized (states) {
      Iterator<String> it = states.iterator();
      while (it.hasNext()) {
        System.out.print(it.next());
        if (it.hasNext())
          System.out.print(", ");
        else
          System.out.println();
      }
    }
  }

  private static void removeTerritories(List<String> states) {
    new Thread(new Runnable() {
      public void run() {
        synchronized (states) {
          for (int i = 0; i < states.size(); i++) {
            String state = states.get(i);
            switch (state) {
            case "AS": case "GU": case "PR": case "VI":
              states.remove(i);
              break;
            }
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
