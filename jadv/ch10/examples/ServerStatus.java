package examples;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerStatus extends Remote {
  public String getStatus() throws RemoteException;
}
