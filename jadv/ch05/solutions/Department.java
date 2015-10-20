package solutions;

import java.io.Serializable;

public class Department implements Serializable {
  private String name;
  private Employee manager;

  public Department(String nm, Employee mg) {
    name = nm;
    manager = mg;
  }

  public boolean equals(Object o) {
    if (o instanceof Department) {
      Department dTest = (Department) o;
      return name.equals(dTest.name) && manager.equals(dTest.manager);
    }
    else
      return false;
  }

  public String toString() {
    return name + " manager: " + manager;
  }

  public String getName() {
    return name;
  }

  public Employee getManager() {
    return manager;
  }

  public void setName(String nm) {
    name = nm;
  }
}
