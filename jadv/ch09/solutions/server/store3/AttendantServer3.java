package solutions.server.store3;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class AttendantServer3 extends UnicastRemoteObject implements
    Attendant3 {
  private static final long serialVersionUID = 1L;
  private DateFormat tf;
  private StoreServer3 store;

  public AttendantServer3(StoreServer3 store) throws RemoteException {
    this.store = store;
    tf = DateFormat.getTimeInstance();
    tf.setTimeZone(TimeZone.getDefault());
  }

  public String[] listItems() {
    Set<String> codeSet = store.getCodes();
    String[] codes = new String[codeSet.size()];
    return codeSet.toArray(codes);
  }

  public Item3 getItem(String code) {
    return store.getItem(code);
  }

  public int submitOrder(Order order) throws RemoteException {
    int orderNum = StoreServer3.getOrderNumber();

    System.out.println("Order #" + orderNum + " received at "
        + tf.format(new Date()));

    Map<String, Integer> items = order.getItems();
    for (Map.Entry<String, Integer> item : items.entrySet()) {
      Item3 invItem = store.getItem(item.getKey());
      int onHand = invItem.getQuantityOnHand();
      int orderQuantity = item.getValue().intValue();

      System.out.println("  Item: " + item.getKey() + " quantity: "
          + item.getValue());

      if (onHand < orderQuantity) {
        System.out.println("    backordered");
      }
      else {
        ItemImpl3 impl = (ItemImpl3) invItem;
        impl.setQuantityOnHand(onHand - orderQuantity);
      }
    }
    return orderNum;
  }

  public boolean release() {
    boolean success = false;
    try {
      success = UnicastRemoteObject.unexportObject(this, false);
    }
    catch (NoSuchObjectException e) {
      System.err.println(e);
    }
    return success;
  }
}
