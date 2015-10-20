package solutions;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee implements Serializable {
  private int id;
  private String firstName;
  private String lastName;
  private String title;
  private Date hireDate;

  public Employee(int i, String first, String last, String ti, Date hire) {
    id = i;
    firstName = first;
    lastName = last;
    title = ti;
    hireDate = hire;
  }

  public String toString() {
    SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
    StringBuilder result = new StringBuilder();
    result.append(title);
    result.append(" ").append(firstName);
    result.append(" ").append(lastName);
    result.append(" hired ").append(df.format(hireDate));
    return result.toString();
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getTitle() {
    return title;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public int getId() {
    return id;
  }
}
