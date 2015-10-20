package examples;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflect implements IReflect {
  public static void main(String[] args) {
    Reflect reflect = new Reflect();
    ReflectFrame frame = new ReflectFrame(reflect);
    frame.setVisible(true);
  }

  public List<Constructor<?>> getConstructors(String className) {
    List<Constructor<?>> list = new ArrayList<>();
    // fill in Lab 1 code here
    try {
      Class<?> cls = Class.forName(className);
      Constructor<?>[] ctors = cls.getDeclaredConstructors();
      for (Constructor<?> ctor : ctors) {
        list.add(ctor);
      }
    }
    catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
    return list;
  }

  public Object instantiate(Constructor<?> ctor, Object[] args) {
    Object o = null;
    // fill in Lab 2 code here
    try {
      o = ctor.newInstance(args);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public List<Method> getMethods(Object obj) {
    List<Method> list = new ArrayList<>();
    // fill in Lab 3 code here
    Class<? extends Object> cls = obj.getClass();
    Method[] methods = cls.getDeclaredMethods();
    for (Method method : methods) {
      list.add(method);
    }
    return list;
  }

  public Object invoke(Method method, Object target, Object[] args) {
    Object retValue = null;
    // fill in Lab 4 code here
    try {
      retValue = method.invoke(target, args);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return retValue;
  }

  public List<String> getClasses() {
    String packageDir = "examples";
    List<String> v = new ArrayList<>();
    File curDir = new File(packageDir);
    File[] classFiles = curDir.listFiles(new FileFilter() {
      public boolean accept(File f) {
        return f.getName().contains(".class");
      }
    });
    for (File file : classFiles) {
      String name = file.getName();
      name = packageDir + "." + name.substring(0, name.indexOf(".class"));
      v.add(name);
    }
    return v;
  }
}
