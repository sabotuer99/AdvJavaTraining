package examples;

import java.lang.reflect.Field;

public class ReadFields {
  public String name;
  private String title;

  public ReadFields(String nm, String t) {
    name = nm;
    title = t;
  }

  public static void main(String[] args) {
    ReadFields[] test = new ReadFields[5];
    for (int i = 0; i < test.length; i++) {
      test[i] = new ReadFields("Object" + i, "Title" + i);
    }

    Class<ReadFields> rfClass = ReadFields.class;
    Field[] fields = rfClass.getFields();

    System.out.println("number of fields: " + fields.length);

    // display the name of the field and the value
    for (int i = 0; i < test.length; i++) {
      System.out.println("ReadFields #" + i);

      for (int j = 0; j < fields.length; j++) {
        try {
          System.out.println("\t" + fields[j].getName() + ": "
              + fields[j].get(test[i]));
        }
        catch (IllegalAccessException e) {
          System.err.println(e);
        }
      }
    }
  }
}
