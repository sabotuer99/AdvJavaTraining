package examples.client.time;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import examples.server.time.Time;

public class ShowTimeClient {
  public static void main(String args[]) {
    System.setSecurityManager(new SecurityManager());
    new ShowTimeClient();
  }

  public ShowTimeClient() {
    try {
      Time t = (Time) Naming.lookup("rmi://localhost:1099/Time");
      System.out.println("The time is: " + t.getSystemTime());
    }
    catch (NotBoundException | MalformedURLException | 
        RemoteException e) {
      System.err.println(e);
    }
  }
}
