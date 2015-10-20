package examples;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class Customer extends Person implements Externalizable {
  private int accountNumber;

  // required no-argument constructor for Externalizable mechanism
  public Customer() {
    // provide a dummy birth date
    // Person can't handle null
    setBirthDate(new Date());
  }

  public Customer(int accountNumber, String name, String title,
      String birthDate, int id) {
    super(name, title, birthDate, id);
    this.accountNumber = accountNumber;
  }

  // read Person.id and Customer.accountNumber
  public void readExternal(ObjectInput in) throws IOException {
    setId(in.readInt());
    accountNumber = in.readInt();
  }

  // write Person.id and Customer.accountNumber, we don't want to
  // expose the other fields of Person on the output stream.
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeInt(getId());
    out.writeInt(accountNumber);
  }

  public String toString() {
    return "Account Number " + accountNumber + ": " + super.toString();
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(int num) {
    accountNumber = num;
  }
}
