package examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
  private String name;
  private String title;
  private Date birthDate;
  private int id;

  public Person(String nm, String ti, String bDate, int i) {
    name = nm;
    title = ti;
    id = i;
    try {
      SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
      birthDate = df.parse(bDate);
    }
    catch (ParseException e) {
      System.err.println("Error parsing Birth Date: " + bDate);
      birthDate = new Date();
    }
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
    if (o instanceof Person) {
      Person pTest = (Person) o;
      return name.equals(pTest.name) && title.equals(pTest.title)
          && birthDate.equals(pTest.birthDate) && id == pTest.id;
    }
    return false;
  }
}
