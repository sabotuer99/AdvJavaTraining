package examples.server.time;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeServer extends UnicastRemoteObject implements Time {
  private static final long serialVersionUID = 1L;

  public TimeServer() throws RemoteException {
  }

  public String getSystemTime() {
    DateFormat df = DateFormat.getTimeInstance();
    df.setTimeZone(TimeZone.getDefault());
    return df.format(new Date());
  }

  public static void main(String args[]) {
    try {
      TimeServer ts = new TimeServer();
      Naming.rebind("rmi://localhost:1099/Time", ts);
    }
    catch (MalformedURLException | RemoteException e) {
      System.err.println(e);
    }
  }
}
