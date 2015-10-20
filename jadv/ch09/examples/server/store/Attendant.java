package examples.server.store;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Attendant extends Remote {
  public int submitOrder(String itemCode, int quantity)
      throws RemoteException;
  public boolean release() throws RemoteException;
}
