package solutions.client.store;

import java.rmi.Naming;

import solutions.server.store2.Attendant2;
import solutions.server.store2.Order;
import solutions.server.store2.Store2;

public class Customer2 {
  public static void main(String[] args) {
    try {
      System.setSecurityManager(new SecurityManager());

      Store2 store = (Store2) Naming.lookup("///Store2");
      Attendant2 attendant = store.getAttendant();

      Order order = new Order();
      order.add("hdw123", 5);
      order.add("app456", 2);
      order.add("per789", 3);
      int orderNumber = attendant.submitOrder(order);
      System.out.println("The order number is: " + orderNumber);

      attendant.release();
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }
}
