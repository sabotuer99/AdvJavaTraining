package solutions;

public class PagerTest2 {
  public static void main(String args[]) {
    String message = "Houston, we have a problem...";
    String id = "SysDBA";

    Pager2 p = new Pager2();
    p.page(message, id);

    String person = p.who(id);
    System.out.println("id belongs to " + person);
  }
}
