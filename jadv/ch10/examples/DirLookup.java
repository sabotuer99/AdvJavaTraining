package examples;

import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DirLookup {
  public static void main(String args[]) {
    String name = "";
    if (args.length > 0) {
      name = args[0];
    }

    StringBuilder buf = new StringBuilder(name);
    buf.append(":\n");

    Hashtable<String, String> env = new Hashtable<>();
    env.put(DirContext.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(DirContext.PROVIDER_URL,
        "ldap://ldap.yo-linux.com/o=stooges");
    try {
      DirContext ctx = new InitialDirContext(env);

      NamingEnumeration<? extends Attribute> attrs = 
          ctx.getAttributes(name).getAll();
      while (attrs.hasMore()) {
        Attribute attr = attrs.next();
        buf.append("   ").append(attr.getID());
        if (attr.size() > 1) {
          buf.append(" has ").append(attr.size());
          if (attr.isOrdered()) {
            buf.append(" ordered");
          }
          else {
            buf.append(" unordered");
          }
          buf.append(" values:\n");
          NamingEnumeration<?> values = attr.getAll();
          while (values.hasMore()) {
            buf.append("       ").append(values.next())
              .append('\n');
          }
        }
        else {
          buf.append(": ").append(attr.get()).append('\n');
        }
      }
      System.out.println(buf.toString());
    }
    catch (NamingException ne) {
      System.err.println(ne);
    }
  }
}
