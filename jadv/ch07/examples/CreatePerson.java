package examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreatePerson {
  public static void main(String[] args) {
    Class<Person2> clazz = Person2.class;
    Class<?>[] parmTypes = { String.class, String.class, String.class,
        int.class };

    try {
      Constructor<Person2> constructor = clazz
          .getConstructor(parmTypes);

      Object[] parms = { "Jane Doe", "CEO", "3/20/1964", 1 };
      Person2 person = (Person2) constructor.newInstance(parms);
      System.out.println(person);

      parms = new Object[] { "John Doe", "CIO", "xx/02/1968", 2 };
      person = (Person2) constructor.newInstance(parms);
      System.out.println(person);
    }
    catch (InvocationTargetException ite) {
      System.out.println("The invoked method threw an exception: "
          + ite.getCause().getClass());
      ite.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
