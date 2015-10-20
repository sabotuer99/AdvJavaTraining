package examples.server.count;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CountServer implements Counter {
  private int count = 0;

  public CountServer() throws RemoteException {
    UnicastRemoteObject.exportObject(this, 0);
  }

  public int getCount() {
    return ++count;
  }
}
