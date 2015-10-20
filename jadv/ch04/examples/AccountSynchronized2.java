package examples;

public class AccountSynchronized2 {
  private double balance;

  public AccountSynchronized2(double initialBalance) {
    balance = initialBalance;
  }

  public void deposit(double amount) {
    synchronized (this) {
      balance += amount;
    }
  }

  public void withdraw(double amount) {
    synchronized (this) {
      balance -= amount;
    }
  }

  public double getBalance() {
    return balance;
  }
}
