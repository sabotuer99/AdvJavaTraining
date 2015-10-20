package solutions.server.store3;

import java.util.HashMap;
import java.util.Map;

public class Order implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private Map<String, Integer> orderItems;

  public Order() {
    orderItems = new HashMap<>();
  }

  public void add(String itemCode, int quantity) {
    orderItems.put(itemCode, new Integer(quantity));
  }

  public Map<String, Integer> getItems() {
    return orderItems;
  }
}
