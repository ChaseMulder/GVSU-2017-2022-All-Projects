package com.stonks.code;

public class Main {
    public static void main(String[] args) {
        StockTickerMngr stockTickerManager = new StockTickerMngr();
        LEDMatrix ledMatrix = new VirtualLEDMatrix();

        DatabaseMonitor databaseMonitor = new DatabaseMonitor(stockTickerManager);
        databaseMonitor.start();

        YahooFinanceMonitor yahooFinanceMonitor = new YahooFinanceMonitor(stockTickerManager);
        yahooFinanceMonitor.start();

        LEDPanelStartup startup = new LEDPanelStartup(ledMatrix);

        if (startup.start()) {
            LEDPanelUpdater ledPanelUpdater = new LEDPanelUpdater(stockTickerManager, ledMatrix);
            ledPanelUpdater.start();
        } else {
            yahooFinanceMonitor.stop();
            databaseMonitor.stop();
        }
    }
}
