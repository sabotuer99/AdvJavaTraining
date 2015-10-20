package examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person2 {
  private String name;
  private String title;
  private Date birthDate;
  private int id;

  public Person2(String nm, String ti, String bDate, int i)
      throws ParseException {
    name = nm;
    title = ti;
    id = i;
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
    birthDate = df.parse(bDate);
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public int getId() {
    return id;
  }

  public String toString() {
    SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
    return title + " " + name + " born " + df.format(birthDate);
  }

  public boolean equals(Object o) {
    if (o instanceof Person2) {
      Person2 pTest = (Person2) o;
      return name.equals(pTest.name) && title.equals(pTest.title)
          && birthDate.equals(pTest.birthDate) && id == pTest.id;
    }
    return false;
  }
}
