package examples;

public class Account {
  private double balance;

  public Account(double initialBalance) {
    balance = initialBalance;
  }

  public void deposit(double amount) {
    double tempBalance = balance;
    tempBalance = tempBalance + amount;
    balance = tempBalance;
  }

  public void withdraw(double amount) {
    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }
}
