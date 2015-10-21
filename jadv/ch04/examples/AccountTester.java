package examples;

public class AccountTester extends Thread {
  private Account theAccount = null;
  private double depositAmount = 0.0;

  public AccountTester(Account acc, double amt) {
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
    Account account = new Account(0.0);
    AccountTester tester1 = new AccountTester(account, 2);
    AccountTester tester2 = new AccountTester(account, 3);

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