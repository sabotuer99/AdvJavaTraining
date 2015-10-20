package examples.server.count;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;

public class CountServerFactory extends UnicastRemoteObject implements
    CounterFactory {
  private static final long serialVersionUID = 1L;

  public CountServerFactory() throws RemoteException {
  }

  public Counter getCounter() throws RemoteException {
    return new CountServer();
  }

  public static void main(String args[]) {
    CountServerFactory factory;
    try {
      factory = new CountServerFactory();
      Naming.rebind("///CountFactory", factory);
    }
    catch (MalformedURLException | RemoteException e) {
      System.err.println(e);
    }
  }
}