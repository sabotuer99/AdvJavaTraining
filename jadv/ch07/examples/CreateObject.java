package examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateObject {
  public static void main(String[] args) {
    Class<Person> personClass = Person.class;

    Class<?>[] parmTypes = {String.class, String.class,
      String.class, int.class};

    Constructor<Person> pCreate = null;

    try {
      pCreate = personClass.getConstructor(parmTypes);
    }
    catch (NoSuchMethodException nsme) {
      System.err.println(nsme);
      System.exit(1);
    }

    Object[] parms = new Object[4];
    parms[0] = "Sam";
    parms[1] = "Java Programmer";
    parms[2] = "03/20/1992";
    parms[3] = 1;

    try {
      Person p = pCreate.newInstance(parms);
      System.out.println(p);
    }
    catch (InstantiationException ie) {
      System.err.println("Instantiation Exception " + ie);
    }
    catch (InvocationTargetException ie) {
      System.err.println("Invocation Target Exception " + ie);
    }
    catch (IllegalAccessException ie) {
      System.err.println("Illegal Access Exception " + ie);
    }
  }
}
