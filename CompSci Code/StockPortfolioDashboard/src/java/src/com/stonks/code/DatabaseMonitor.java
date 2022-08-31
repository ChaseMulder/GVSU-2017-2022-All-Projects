package com.stonks.code;

public class DatabaseMonitor implements Runnable {
    private final String connectionUrl = "jdbc:mysql://localhost:3306/stonks";
    private final String username = "root";
    private final String password = "";
    private boolean run = true;

    private StockTickerMngr stockTickerManager;

    public DatabaseMonitor(StockTickerMngr stockTickerManager) {
        this.stockTickerManager = stockTickerManager;
    }

    public void start() {
        run = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void stop() {
        run = false;
    }

    @Override
    public void run() {
        int increment = 0;

        while (run) {
            StockTickers tickersFromDB = DatabaseQuery.getStockTickers(connectionUrl, username, password);

            stockTickerManager.updateTickers(tickersFromDB);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
