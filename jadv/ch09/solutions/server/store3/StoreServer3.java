package solutions.server.store3;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StoreServer3 extends UnicastRemoteObject implements Store3 {
  private static final long serialVersionUID = 1L;
  private Map<String, Item3> items;
  private static int nextOrder = 1;

  public StoreServer3() throws RemoteException {
    items = new HashMap<>();
    items.put("hdw123", new ItemImpl3("hdw123", "1 GB SIM", 50));
    items.put("hdw124", new ItemImpl3("hdw124", "60 GB HD", 30));
    items.put("hdw125", new ItemImpl3("hdw125", "80 GB HD", 25));
    items.put("per456", new ItemImpl3("per456", "InkJt Printer", 36));
    items.put("per457", new ItemImpl3("per457", "Laser Printer", 4));
  }

  public Attendant3 getAttendant() throws RemoteException {
    return new AttendantServer3(this);
  }

  public Set<String> getCodes() {
    return items.keySet();
  }

  public Collection<Item3> getItems() {
    return items.values();
  }

  public Item3 getItem(String code) {
    return items.get(code);
  }

  public static synchronized int getOrderNumber() {
    return nextOrder++;
  }

  public static void main(String[] args) {
    try {
      StoreServer3 store = new StoreServer3();
      Naming.rebind("///Store3", store);
    }
    catch (java.net.MalformedURLException e) {
      System.err.println(e);
    }
    catch (RemoteException e) {
      System.err.println(e);
    }
  }
}
