package solutions.client.store;

import java.rmi.Naming;

import solutions.server.store3.Order;
import solutions.server.store3.Attendant3;
import solutions.server.store3.Item3;
import solutions.server.store3.Store3;

public class Customer3 {
  public static void main(String[] args) {
    try {
      System.setSecurityManager(new SecurityManager());
      
      Store3 store = (Store3) Naming.lookup("///Store3");
      Attendant3 attendant = store.getAttendant();

      String[] itemCodes = attendant.listItems();
      System.out.println("All item codes: ");
      for (String code : itemCodes) {
        System.out.println("Item Code: " + code);
      }
      System.out.println();

      Item3 i = attendant.getItem("hdw123");
      System.out.println("Item Code: " + i.getItemCode() +
        " Description: " + i.getDescription() +
        " Quantity On Hand: " + i.getQuantityOnHand());

      Order order = new Order();
      order.add("hdw123", 5);
      order.add("per456", 3);
      int orderNumber = attendant.submitOrder(order);
      System.out.println("The order number is: " + orderNumber);

      System.out.println("Item Code: " + i.getItemCode() +
        " Description: " + i.getDescription() +
        " Quantity On Hand: " + i.getQuantityOnHand());

      attendant.release();
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }
}
