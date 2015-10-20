package solutions;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.RateInfo;

public class GetRates {
  public static void main(String[] args) {
    Hashtable<String, String> env = new Hashtable<>();
    env.put(Context.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.rmi.registry.RegistryContextFactory");
    env.put(Context.PROVIDER_URL, "rmi:///");

    try {
      Context ctx = new InitialContext(env);
      RateInfo rates = (RateInfo) ctx.lookup("RateInfo");
      System.out.println(rates.getInfo());
    }
    catch (RemoteException | NamingException e) {
      System.err.println(e);
    }
  }
}
