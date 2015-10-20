package solutions;

public class Pager {
  public native void page(String message, String id);

  static {
    System.loadLibrary("Pager");
  }
}
