package solutions;

public class PagerTest {
  public static void main(String args[]) {
    String message = "Houston, we have a problem...";
    String id = "SysDBA";

    Pager p = new Pager();
    p.page(message, id);
  }
}
