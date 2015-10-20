package examples;

public class AccountSynchronized {
  private double balance;

  public AccountSynchronized(double initialBalance) {
    balance = initialBalance;
  }

  public synchronized void deposit(double amount) {
    double tempBalance = balance;
    tempBalance = tempBalance + amount;
    balance = tempBalance;
  }

  public synchronized void withdraw(double amount) {
    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }
}
