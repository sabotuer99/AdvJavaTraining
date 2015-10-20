package examples;

public class StockBroker implements StockListener {
  int brokerID;

  public StockBroker(int id) {
    brokerID = id;
  }

  public void tradeNotification(String sym, int shares) {
    System.out.print("Broker " + brokerID + " : ");
    System.out.println(shares + " shares of " + sym + " were traded");
  }
}
