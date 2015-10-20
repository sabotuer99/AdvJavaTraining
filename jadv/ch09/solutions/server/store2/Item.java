package solutions.server.store2;

public interface Item {
  public String getItemCode();
  public String getDescription();
  public int getQuantityOnHand();
}

class ItemImpl implements Item {
  private String itemCode;
  private String description;
  private int quantityOnHand;

  public ItemImpl() {
  }

  public ItemImpl(String code, String desc, int quantity) {
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
