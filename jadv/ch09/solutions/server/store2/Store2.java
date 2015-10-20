package solutions.server.store2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Store2 extends Remote {
  public Attendant2 getAttendant() throws RemoteException;
}