package com.stonks.test;

import com.stonks.code.DatabaseMonitor;
import com.stonks.code.LEDMatrix;
import com.stonks.code.VirtualLEDMatrix;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;


public class StockQueryTest {
    public static void main(String[] args) {
        try {
            Stock stock = YahooFinance.get("GME");
            StockQuote quote = stock.getQuote();
            System.out.println(quote.getPrice());

            Thread.sleep(5000);

            quote = stock.getQuote(true);
            System.out.println(quote.getPrice());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
