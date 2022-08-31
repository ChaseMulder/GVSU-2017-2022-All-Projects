package com.stonks.test;

import com.stonks.code.DatabaseQuery;
import com.stonks.code.StockTickers;

import java.util.Scanner;

public class WebUIIntegrationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Navigate to the web user interface at http://localhost");
        System.out.println("Press enter once you have the Web UI open.");

        scanner.nextLine();

        StockTickers stockTickers = DatabaseQuery.getStockTickers("jdbc:mysql://localhost:3306/stonks", "root", "");
        System.out.println("Layout: " + stockTickers.getLayoutStyle());
        System.out.println("Single Ticker: " + stockTickers.getSingleTicker());
        System.out.println("Two Tickers 1: " + stockTickers.getDoubleTicker1());
        System.out.println("Two Tickers 2: " + stockTickers.getDoubleTicker2());
        System.out.println("Many Tickers Main: " + stockTickers.getManyTicker1());
        System.out.println("Many Tickers Secondary 1: " + stockTickers.getManyTicker2());
        System.out.println("Many Tickers Secondary 2: " + stockTickers.getManyTicker3());
        System.out.println("Many Tickers Secondary 3: " + stockTickers.getManyTicker4());
        System.out.println("Many Tickers Secondary 4: " + stockTickers.getManyTicker5());

        System.out.println();
        System.out.println("The outputted tickers should match the tickers in the Web UI.");
        System.out.println("Now change some of the tickers and/or the layout and press save.");
        System.out.println("Press the enter key in the terminal when you have done this.");

        scanner.nextLine();

        System.out.println();
        stockTickers = DatabaseQuery.getStockTickers("jdbc:mysql://localhost:3306/stonks", "root", "");
        System.out.println("Layout: " + stockTickers.getLayoutStyle());
        System.out.println("Single Ticker: " + stockTickers.getSingleTicker());
        System.out.println("Two Tickers 1: " + stockTickers.getDoubleTicker1());
        System.out.println("Two Tickers 2: " + stockTickers.getDoubleTicker2());
        System.out.println("Many Tickers Main: " + stockTickers.getManyTicker1());
        System.out.println("Many Tickers Secondary 1: " + stockTickers.getManyTicker2());
        System.out.println("Many Tickers Secondary 2: " + stockTickers.getManyTicker3());
        System.out.println("Many Tickers Secondary 3: " + stockTickers.getManyTicker4());
        System.out.println("Many Tickers Secondary 4: " + stockTickers.getManyTicker5());

        System.out.println();
        System.out.println("The newly outputted tickers should match what you changed in the Web UI.");
        System.out.println("If either of the sets of tickers failed to match the Web UI, the test fails.");
    }
}
