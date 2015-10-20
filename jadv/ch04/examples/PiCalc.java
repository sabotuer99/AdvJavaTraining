package examples;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.lang.Math.*;

public class PiCalc implements Callable<Double> {
  public Double call() {
    // Machin's formula
    // pi/4 = 4 * arctan(1/5) - arctan(1/239);
    double pi = 16 * atan(1.0/5.0) - 4 * atan(1.0/239.0);
    
    try {
      // Sleep for 1 second to simulate a long running task
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    return pi;
  }

  public static void main(String[] args) throws Exception {
    ExecutorService ex = Executors.newCachedThreadPool();
    Future<Double> result = ex.submit(new PiCalc());
    // do other stuff
    Double pi = result.get();
    System.out.println(pi);
    ex.shutdown();
  }
}