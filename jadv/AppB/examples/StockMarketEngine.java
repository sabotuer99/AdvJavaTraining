package examples;

public class StockMarketEngine {
  public native void addStockListener(StockListener sl);
  public native void startEngine();
  static {
    System.loadLibrary("Stocks");
  }
}
