package examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public interface IReflect {
  public List<Constructor<?>> getConstructors(String className);
  public Object instantiate(Constructor<?> ctor, Object[] args);
  public List<Method> getMethods(Object obj);
  public Object invoke(Method method, Object target, Object[] args);
  public List<String> getClasses();
}
