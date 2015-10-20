package solutions.server.store2;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class AttendantServer2 extends UnicastRemoteObject implements
    Attendant2 {
  private static final long serialVersionUID = 1L;
  private DateFormat tf;

  public AttendantServer2() throws RemoteException {
    tf = DateFormat.getTimeInstance();
    tf.setTimeZone(TimeZone.getDefault());
  }

  public int submitOrder(Order order) {
    int orderNum = StoreServer2.getOrderNumber();

    System.out.println("Order #" + orderNum + " received at "
        + tf.format(new Date()));

    Map<String, Integer> items = order.getItems();
    for (Map.Entry<String, Integer> item : items.entrySet()) {
      System.out.println("  Item: " + item.getKey() + " quantity: "
          + item.getValue());
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
