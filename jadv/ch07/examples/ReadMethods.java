package examples;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReadMethods {
  private String name;
  private String title;

  public String getName() {
    return name;
  }
  protected String getTitle() {
    return title;
  }
  private void setTitle(String ti) {
    title = ti;
  }
  public static void main(String[] args) {
    ReadMethods obj = new ReadMethods();
    Class<? extends ReadMethods> clazz = obj.getClass();
    Method[] methods = clazz.getMethods();

    System.out.println("Number of methods: " + methods.length);
    System.out.println(clazz.getName() + " methods:");

    for (int i = 0; i < methods.length; i++) {
      int mods = methods[i].getModifiers();
      if (Modifier.isStatic(mods))
        System.out.print("static ");

      System.out.print(methods[i].getReturnType().getName() + " ");

      Class<?> dcl = methods[i].getDeclaringClass();
      if (!clazz.getName().equals(dcl.getName())) {
        System.out.print(dcl.getName() + ".");
      }

      System.out.print(methods[i].getName() + "(");
      Class<?>[] parms = methods[i].getParameterTypes();
      for (int j = 0; j < parms.length; j++) {
        System.out.print((j > 0 ? ", " : "") + parms[j].getName());
      }
      System.out.println(")");
    }
  }
}
