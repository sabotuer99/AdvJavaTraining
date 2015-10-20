package examples;

public class StockMarketTest {
  public static void main(String[] args) {
    StockMarketEngine sme = new StockMarketEngine();

    StockBroker sb = new StockBroker(1);
    sme.addStockListener(sb);

    sb = new StockBroker(2);
    sme.addStockListener(sb);

    sb = new StockBroker(3);
    sme.addStockListener(sb);

    sme.startEngine();
  }
}