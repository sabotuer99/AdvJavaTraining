package examples;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This class bootstraps our RMI services for testing purposes. In our
 * production environment, these services will be started individually on their
 * respective servers.
 */
public class Server {
  public static void main(String[] args) {
    Context ctx = null;
    try {
      Remote r;
      Hashtable<String, String> env = new Hashtable<>();
      env.put(Context.INITIAL_CONTEXT_FACTORY,
          "com.sun.jndi.rmi.registry.RegistryContextFactory");
      env.put(Context.PROVIDER_URL, "rmi:///");
      ctx = new InitialContext(env);
      r = new ServerStatusImpl("web1");
      ctx.rebind("ServerStatus.web1", r);
      r = new ServerStatusImpl("web2");
      ctx.rebind("ServerStatus.web2", r);
      r = new ServerStatusImpl("xmldb");
      ctx.rebind("ServerStatus.xmldb", r);
      r = new RateInfoImpl();
      ctx.rebind("RateInfo", r);
    }
    catch (RemoteException re) {
      re.printStackTrace();
      System.exit(1);
    }
    catch (NamingException ne) {
      ne.printStackTrace();
      System.exit(2);
    }
    System.out.println("running.");
  }
}
