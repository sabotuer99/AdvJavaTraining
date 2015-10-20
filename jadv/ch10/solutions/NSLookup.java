package solutions;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class NSLookup {
  public static void main(String[] arg) {
    String host = "yahoo.com";
    String[] rrtypes = new String[] { "A", "MX" };

    Hashtable<String, String> env = new Hashtable<>();
    env.put(Context.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.dns.DnsContextFactory");
    env.put(Context.PROVIDER_URL, "dns://208.67.220.220/");

    try {
      DirContext ctxt = new InitialDirContext(env);
      Attributes att = ctxt.getAttributes(host, rrtypes);

      NamingEnumeration<? extends Attribute> attlist = att.getAll();
      while (attlist.hasMore()) {
        Attribute a = (Attribute) attlist.next();
        // Normally we would iterate through attribure values.
        // Attribute.toString() gives nice enough output.
        System.out.println(a);
      }
    }
    catch (NamingException ne) {
      System.out.println(ne);
    }
  }
}
