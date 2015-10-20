package examples;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Serializable {
  private String name;
  private String title;
  private Date birthDate;
  private int id;

  public Person() {
  }

  public Person(String nm, String ti, String shortBirthDate, int i) {
    name = nm;
    title = ti;
    id = i;
    try {
      SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
      birthDate = df.parse(shortBirthDate);
    }
    catch (ParseException e) {
      System.err.println("Parsing error: " + shortBirthDate);
      birthDate = new Date();
    }
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof Person)) {
      return false;
    }

    Person p = (Person) obj;

    return name.equals(p.name) && title.equals(p.title)
        && birthDate.equals(p.birthDate) && id == p.id;
  }

  public String toString() {
    SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
    return title + " " + name + " born " + df.format(birthDate)
        + " id " + id;
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

  public void setName(String nm) {
    name = nm;
  }

  public void setTitle(String t) {
    title = t;
  }

  public void setBirthDate(Date dt) {
    birthDate = dt;
  }

  public void setId(int i) {
    id = i;
  }
}
