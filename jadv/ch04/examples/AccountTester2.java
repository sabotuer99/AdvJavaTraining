package examples;

public class AccountTester2 extends Thread {
  private AccountSynchronized theAccount = null;
  private double depositAmount = 0.0;

  public AccountTester2(AccountSynchronized acc, double amt) {
    theAccount = acc;
    depositAmount = amt;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000000; i++) {
      theAccount.deposit(depositAmount);
    }
  }

  public static void main(String[] args) {
    AccountSynchronized account = new AccountSynchronized(0.0);
    AccountTester2 tester1 = new AccountTester2(account, 2);
    AccountTester2 tester2 = new AccountTester2(account, 3);

    tester1.start();
    tester2.start();

    try {
      tester1.join();
      tester2.join();
    }
    catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.println("End balance is: " + account.getBalance());
  }
}