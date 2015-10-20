package examples.client.count;

import java.rmi.Naming;

import examples.server.count.Counter;
import examples.server.count.CounterFactory;

public class CountClient {
  public static void main(String args[]) {
    System.setSecurityManager(new SecurityManager());

    try {
      CounterFactory factory = (CounterFactory) Naming
          .lookup("///CountFactory");
      Counter counter = factory.getCounter();

      for (int i = 0; i < 10; i++) {
        System.out.println("Count: " + counter.getCount());
      }
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }
}
