package solutions.server.store3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface Item3 extends Remote {
  public String getItemCode() throws RemoteException;
  public String getDescription() throws RemoteException;
  public int getQuantityOnHand() throws RemoteException;
}

class ItemImpl3 extends UnicastRemoteObject implements Item3 {
  private static final long serialVersionUID = 1L;
  private String itemCode;
  private String description;
  private int quantityOnHand;

  public ItemImpl3() throws RemoteException {
  }

  public ItemImpl3(String code, String desc, int quantity)
      throws RemoteException {
    itemCode = code;
    description = desc;
    quantityOnHand = quantity;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String code) {
    itemCode = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String desc) {
    description = desc;
  }

  public int getQuantityOnHand() {
    return quantityOnHand;
  }

  public void setQuantityOnHand(int q) {
    quantityOnHand = q;
  }
}
