package examples;

public class DeadLockExample {
  public static ClassA a;
  public static ClassB b;

  public static void main(String[] args) {
    a = new ClassA();
    b = new ClassB();
    a.start();
    b.start();
  }
}

class ClassA extends Thread {
  public synchronized void method1() {
    System.out.println(this.getName() + " acquired A's lock");
    Thread.yield(); // force deadlock
    System.out.println(this.getName() + " calling B's method");
    DeadLockExample.b.method4();
    System.out.println(this.getName() + " released A's lock");
  }

  public synchronized void method2() {
  }

  public void run() {
    this.method1();
  }
}

class ClassB extends Thread {
  public synchronized void method3() {
    System.out.println(this.getName() + " acquired B's lock");
    System.out.println(this.getName() + " calling A's method");
    DeadLockExample.a.method2();
    System.out.println(this.getName() + " released B's lock");
  }

  public synchronized void method4() {
  }

  public void run() {
    this.method3();
  }
}
