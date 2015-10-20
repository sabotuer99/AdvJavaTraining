package examples;

public class ConcatTest {
  public static void main(String[] args) {
    concatString();
    concatStringBuffer();
    concatStringBuilder();
  }

  static void concatString() {
    String s = new String("Java ");
    s += "Programming";
  }

  static void concatStringBuffer() {
    StringBuffer b = new StringBuffer("Java ");
    b.append("Programming");
  }

  static void concatStringBuilder() {
    StringBuilder b = new StringBuilder("Java ");
    b.append("Programming");
  }
}