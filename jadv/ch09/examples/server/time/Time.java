package examples.server.time;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Time extends Remote {
  public String getSystemTime() throws RemoteException;
}
