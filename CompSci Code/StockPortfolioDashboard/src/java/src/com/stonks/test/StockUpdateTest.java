package com.stonks.test;

import com.stonks.code.StockTickerMngr;
import com.stonks.code.StockTickers;
import com.stonks.code.YahooFinanceMonitor;

public class StockUpdateTest {
    public static void main(String[] args) {
        StockTickers stockTickers = new StockTickers(
                "single",
                "TSLA",
                "GME",
                "AMC",
                "GME",
                "AMC",
                "MSFT",
                "F",
                "TSLA"
        );

        StockTickerMngr stockTickerMngr = new StockTickerMngr();
        stockTickerMngr.updateTickers(stockTickers);

        YahooFinanceMonitor yahooFinanceMonitor = new YahooFinanceMonitor(stockTickerMngr);
        yahooFinanceMonitor.start();

        System.out.println("Stock prices will output for 20 seconds. This time can be adjusted in the program is necessary");
        System.out.println("Stock prices in the system should update within 6 seconds of the price changing.");
        System.out.println("Note that the price output updating can't be tested when the market is closed.");
        System.out.println("Find the stock price of TSLA at https://finance.yahoo.com/quote/TSLA and compare to the outputted price.");

        for (int i = 0; i < 20; i++) {
            System.out.println("TSLA Price: " + stockTickerMngr.getStockQuote(stockTickers.getSingleTicker()).getPrice());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

        yahooFinanceMonitor.stop();
        System.out.println("If the price updated within 6 or so seconds, the test passes.");
    }
}
