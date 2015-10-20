package solutions.client.store;

import java.rmi.Naming;

import examples.server.store.Attendant;
import examples.server.store.Store;

public class Customer1 {
  public static void main(String[] args) {
    try {
      System.setSecurityManager(new SecurityManager());
      
      Store store = (Store) Naming.lookup("///Store");
      Attendant attendant = store.getAttendant();
      
      int orderNumber = attendant.submitOrder("hdw123", 5);
      System.out.println("The order number is: " + orderNumber);
      
      attendant.release();
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }
}
