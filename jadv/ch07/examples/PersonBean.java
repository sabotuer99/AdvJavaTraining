package examples;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String title;
  private Date birthDate;
  private int id;

  public PersonBean() {
    name = "";
    title = "";
    birthDate = new Date();
  }

  public PersonBean(String nm, String ti, String bDate, int i)
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

  public void setName(String nm) {
    name = nm;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String t) {
    title = t;
  }

  public int getId() {
    return id;
  }

  public void setId(int i) {
    id = i;
  }

  public String getBirthDate() {
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
    return df.format(birthDate);
  }

  public void setBirthDate(String shortDate) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
    birthDate = df.parse(shortDate);
  }

  public String toString() {
    SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
    return title + " " + name + " born " + df.format(birthDate);
  }

  public boolean equals(Object o) {
    if (o instanceof PersonBean) {
      PersonBean pTest = (PersonBean) o;
      return name.equals(pTest.name) && title.equals(pTest.title)
          && birthDate.equals(pTest.birthDate) && id == pTest.id;
    }
    return false;
  }
}
