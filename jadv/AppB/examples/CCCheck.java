package examples;

public class CCCheck {
  public native int validCC(String ccNumber, int digitCount);
  static {
    System.loadLibrary("CCCheck");
  }
}
