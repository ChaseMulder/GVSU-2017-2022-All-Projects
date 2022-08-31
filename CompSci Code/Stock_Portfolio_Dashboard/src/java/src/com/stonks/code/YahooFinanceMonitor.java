package com.stonks.code;

public class YahooFinanceMonitor implements Runnable {
    private StockTickerMngr stockTickerManager;
    private boolean run = true;

    public YahooFinanceMonitor(StockTickerMngr stockTickerManager) {
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
        while (run) {
            stockTickerManager.updateQuotes();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
