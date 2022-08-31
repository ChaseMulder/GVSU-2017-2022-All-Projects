package com.stonks.test;

import com.stonks.code.DatabaseQuery;
import com.stonks.code.StockTickerMngr;
import com.stonks.code.StockTickers;

public class DBYahooFinanceIntegrationTest {
    public static void main(String[] args) {
        String connection = "jdbc:mysql://localhost:3306/stonks";
        String user = "root";
        String password = "";

        StockTickers tickers = DatabaseQuery.getStockTickers(connection, user, password);
        StockTickerMngr stockTickerManager = new StockTickerMngr();
        stockTickerManager.updateTickers(tickers);
        System.out.println("Single Ticker: " + tickers.getSingleTicker() + ":");
        System.out.println(stockTickerManager.getStockQuote(tickers.getSingleTicker()).getPrice());
        System.out.println("Double Ticker 1: " + tickers.getDoubleTicker1() + ":");
        System.out.println(stockTickerManager.getStockQuote(tickers.getDoubleTicker1()).getPrice());
        System.out.println("Many Ticker 1: " + tickers.getManyTicker1() + ":");
        System.out.println(stockTickerManager.getStockQuote(tickers.getManyTicker1()).getPrice());

        System.out.println();
        System.out.println("The outputted prices should correspond to tickers in the database.");
        System.out.println("The prices outputted should correspond to the ticker and be current.");
        System.out.println("Prices can be verified at https://finance.yahoo.com");
        System.out.println("If the tickers are the correct tickers and the prices are right, the test passes.");
    }
}
