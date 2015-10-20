package examples.server.count;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CounterFactory extends Remote {
  public Counter getCounter() throws RemoteException;
}
