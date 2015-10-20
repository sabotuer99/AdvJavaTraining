package examples;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RateInfoImpl extends UnicastRemoteObject implements
    RateInfo {
  private static final long serialVersionUID = 1L;

  public RateInfoImpl() throws RemoteException {
    super();
  }

  public String getInfo() {
    return "All rates are currently competitive. "
        + "Call the office for more details.";
  }
}
