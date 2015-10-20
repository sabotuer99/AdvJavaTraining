package examples.server.store;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AttendantServer extends UnicastRemoteObject implements
    Attendant {
  private static final long serialVersionUID = 1L;
  private DateFormat tf;

  public AttendantServer() throws RemoteException {
    tf = DateFormat.getTimeInstance();
    tf.setTimeZone(TimeZone.getDefault());
  }

  public int submitOrder(String itemCode, int quantity) {
    int orderNum = StoreServer.getOrderNumber();
    System.out.println("Order #" + orderNum + " for item code: "
        + itemCode + " quantity: " + quantity + " received at "
        + tf.format(new Date()));
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
