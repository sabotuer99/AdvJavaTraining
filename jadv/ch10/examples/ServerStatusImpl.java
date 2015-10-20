package examples;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerStatusImpl extends UnicastRemoteObject implements
    ServerStatus {
  private static final long serialVersionUID = 1L;
  private String name;

  public ServerStatusImpl(String name) throws RemoteException {
    this.name = name;
  }

  public String getStatus() throws RemoteException {
    return "Server " + name + " is running.";
  }
}
