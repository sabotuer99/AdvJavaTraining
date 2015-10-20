package examples;

public class CCCheckTest {
  public static void main(String args[]) {
    int result = 0;
    String testNumber = "12344";
    CCCheck checker = new CCCheck();

    result = checker.validCC(testNumber, testNumber.length());

    System.out.println("Number " + testNumber + " is "
        + (result != 0 ? "valid" : "not valid"));

    testNumber = "12341";
    result = checker.validCC(testNumber, testNumber.length());
    System.out.println("Number " + testNumber + " is "
        + (result != 0 ? "valid" : "not valid"));
  }
}