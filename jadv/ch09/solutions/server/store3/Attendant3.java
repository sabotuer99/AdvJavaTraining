package solutions.server.store3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Attendant3 extends Remote {
  public String[] listItems() throws RemoteException;
  public Item3 getItem(String code) throws RemoteException;
  public int submitOrder(Order order) throws RemoteException;
  public boolean release() throws RemoteException;
}
