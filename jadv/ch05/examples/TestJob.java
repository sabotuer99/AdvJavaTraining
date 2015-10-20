package examples;

import java.io.Serializable;

// A simple class for testing BatchProcessor
public class TestJob implements Job, Serializable {
  private static final long serialVersionUID = 1L;
  private String name;

  public TestJob(String name) {
    this.name = name;
  }

  public void runJob() {
    System.out.println(name + " Starting.");
    // simulate some work
    try {
      Thread.sleep(25);
    }
    catch (InterruptedException ex) {
      // propagate the interruption to the batch processor
      Thread.currentThread().interrupt();
    }
    System.out.println(name + " Completed.");
  }
}
