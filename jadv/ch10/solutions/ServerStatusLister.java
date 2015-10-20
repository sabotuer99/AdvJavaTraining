package solutions;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import examples.ServerStatus;

public class ServerStatusLister {
  public static void main(String args[]) {
    Hashtable<String, String> env = new Hashtable<>();

    env.put(Context.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.rmi.registry.RegistryContextFactory");
    env.put(Context.PROVIDER_URL, "rmi:///");

    try {
      Context ctx = new InitialContext(env);

      NamingEnumeration<NameClassPair> list = ctx.list("");
      while (list.hasMore()) {
        NameClassPair ncp = list.next();
        String name = ncp.getName();
        if (name.startsWith("ServerStatus.")) {
          try {
            ServerStatus stat = (ServerStatus) ctx.lookup(name);
            System.out.println(stat.getStatus());
          }
          catch (RemoteException re) {
            System.err.println("Remote method failed for " + name
                + ": " + re);
          }
          catch (NamingException ne) {
            System.err.println("Failed looking up " + name + ": " + ne);
          }
        }
      }
    }
    catch (NamingException ne) {
      System.err.println(ne);
    }
  }
}
