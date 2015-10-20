package examples;

public interface StockListener {
  public void tradeNotification(String sym, int shares);
}