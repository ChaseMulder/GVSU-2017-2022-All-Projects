package com.stonks.code;

import yahoofinance.quotes.stock.StockQuote;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class LEDPanelUpdater implements Runnable {
    private StockTickerMngr stockTickerManager;
    private LEDMatrix ledMatrix;

    public LEDPanelUpdater(StockTickerMngr stockTickerManager, LEDMatrix ledMatrix) {
        this.stockTickerManager = stockTickerManager;
        this.ledMatrix = ledMatrix;

        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 32; j++) {
                ledMatrix.setPixel(i, j, new Color(0, 0, 0));
            }
        }
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    // line is 0 - 3
    private void writeToPanelMultiColor (int line, String text, Color color) {
        ArrayList<String[]> chars;

        if (text.length() > 10) {
            chars = Text5x7.getLetters(text.substring(0, 10), color);
        } else {
            chars = Text5x7.getLetters(text, color);
        }

        for (int i = 0; i < 7; i++) {
            String row = "";
            for (String[] s : chars) {
                row += s[i];
                row += " ";
            }

            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) != ' ') {
                    ledMatrix.setPixel(j + 2, i + (line * 8), Text5x7.colors.get(row.charAt(j)));
                } else {
                    ledMatrix.setPixel(j + 2, i + (line * 8), new Color(0, 0, 0));
                }
            }
        }
    }

    private void writeToPanel (int line, String text, Color color) {
        writeToPanel(line, 0, text, color);
    }

    // line is 0 - 3
    private void writeToPanel (int line, int offset, String text, Color color) {
        ArrayList<String[]> chars;
        if (text.length() > (10 - offset)) {
            chars = Text5x7.getLetters(text.substring(0, (10 - offset)));
        } else {
            chars = Text5x7.getLetters(text);
        }
        for (int i = 0; i < 7; i++) {
            String row = "";
            for (String[] s : chars) {
                row += s[i];
                row += " ";
            }

            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '0') {
                    ledMatrix.setPixel(j + 2 + (offset * 6), i + 1 + (line * 8), color);
                } else {
                    ledMatrix.setPixel(j + 2 + (offset * 6), i + 1 + (line * 8), new Color(0, 0, 0));
                }
            }
        }
    }

    public void writeToPanel(int line, String text) {
        writeToPanel(line, text, new Color(255, 255, 255));
    }

    public void writeToPanel(int line, int offset, String text) {
        writeToPanel(line, offset, text, new Color(255, 255, 255));
    }

    public void writeMarketStatus() {
        ZonedDateTime dateTime = ZonedDateTime.now(TimeZone.getTimeZone("EST").toZoneId());
        if (dateTime.getHour() < 16 && dateTime.getHour() > 9 || (dateTime.getHour() == 9 && dateTime.getMinute() >= 30)) {
            for (int i = 2; i < 62; i++) {
                ledMatrix.setPixel(i, 0, new Color(0, 255, 0));
            }
        } else {
            for (int i = 2; i < 62; i++) {
                ledMatrix.setPixel(i, 0, new Color (200, 200, 200));
            }
        }
    }

    @Override
    public void run() {
        LEDPanelScrollingText doubleScroll1 = new LEDPanelScrollingText(1, ledMatrix);
        LEDPanelScrollingText doubleScroll2 = new LEDPanelScrollingText(3, ledMatrix);
        LEDPanelScrollingText manyScroll = new LEDPanelScrollingText(3, ledMatrix);

        StockTickers prevTickers = stockTickerManager.getStockTickersSelected();
        StockTickers stockTickers = stockTickerManager.getStockTickersSelected();

        for (;;) {
            prevTickers = stockTickers;
            stockTickers = stockTickerManager.getStockTickersSelected();
            writeMarketStatus();

            if (stockTickers.getLayoutStyle().equals("single")) {
                if (doubleScroll1.isRunning()) {
                    doubleScroll1.stop();
                }
                if (doubleScroll2.isRunning()) {
                    doubleScroll2.stop();
                }
                if (manyScroll.isRunning()) {
                    manyScroll.stop();
                }

                if (!prevTickers.getLayoutStyle().equals(stockTickers.getLayoutStyle()) || !prevTickers.getSingleTicker().equals(stockTickers.getSingleTicker())) {
                    writeToPanel(0, "          ");
                    writeToPanel(1, "          ");
                    writeToPanel(2, "          ");
                    writeToPanel(3, "          ");
                }

                StockQuote quote = stockTickerManager.getStockQuote(stockTickers.getSingleTicker());

                writeToPanel(0, stockTickers.getSingleTicker());
                writeToPanel(1, quote.getPrice().setScale(2, RoundingMode.HALF_UP).toString());

                BigDecimal change = quote.getChange();
                if (change.compareTo(new BigDecimal(0)) < 0) {
                    writeToPanel(2, change.setScale(2, RoundingMode.HALF_UP).toString(), new Color(255, 0, 0));
                } else if (change.compareTo(new BigDecimal(0)) > 0) {
                    writeToPanel(2, "+" + change.setScale(2, RoundingMode.HALF_UP).toString(), new Color(0, 255, 0));
                } else {
                    writeToPanel(2, change.toString());
                }

                BigDecimal volume = new BigDecimal(quote.getVolume());
                if (volume.compareTo(new BigDecimal(1000)) < 0) {
                    writeToPanel(3, "Vol " + volume);
                } else if (volume.compareTo(new BigDecimal(10000)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP) + "K");
                } else if (volume.compareTo(new BigDecimal(100000)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000), 1, RoundingMode.HALF_UP) + "K");
                } else if (volume.compareTo(new BigDecimal(1000000)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000), 0, RoundingMode.HALF_UP) + "K");
                } else if (volume.compareTo(new BigDecimal(10000000)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000000), 2, RoundingMode.HALF_UP) + "M");
                } else if (volume.compareTo(new BigDecimal(100000000)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000000), 1, RoundingMode.HALF_UP) + "M");
                } else if (volume.compareTo(new BigDecimal(1000000000)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000000), 0, RoundingMode.HALF_UP) + "M");
                } else if (volume.compareTo(new BigDecimal(10000000000L)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000000000), 2, RoundingMode.HALF_UP) + "B");
                } else if (volume.compareTo(new BigDecimal(100000000000L)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000000000), 1, RoundingMode.HALF_UP) + "B");
                } else if (volume.compareTo(new BigDecimal(1000000000000L)) < 0) {
                    writeToPanel(3, "Vol " + volume.divide(new BigDecimal(1000000000), 0, RoundingMode.HALF_UP) + "B");
                }
            } else if (stockTickers.getLayoutStyle().equals("double")) {
                if (manyScroll.isRunning()) {
                    manyScroll.stop();
                }

                if (!prevTickers.getLayoutStyle().equals(stockTickers.getLayoutStyle()) || !prevTickers.getDoubleTicker1().equals(stockTickers.getDoubleTicker1()) || !prevTickers.getDoubleTicker2().equals(stockTickers.getDoubleTicker2())) {
                    writeToPanel(0, "          ");
                    writeToPanel(2, "          ");
                }

                StockQuote quote1 = stockTickerManager.getStockQuote(stockTickers.getDoubleTicker1());
                StockQuote quote2 = stockTickerManager.getStockQuote(stockTickers.getDoubleTicker2());

                writeToPanel(0, stockTickers.getDoubleTicker1());
                writeToPanel(2, stockTickers.getDoubleTicker2());

                if (!doubleScroll1.isRunning()) {
                    ConcurrentHashMap<String, ScrollObject> double1attrib = new ConcurrentHashMap<>();
                    double1attrib.put("Price", new ScrollObject(quote1.getPrice().setScale(2, RoundingMode.HALF_UP)));
                    double1attrib.put("Chng", new ScrollObject(quote1.getChange().setScale(2, RoundingMode.HALF_UP), true));
                    doubleScroll1.setValues(double1attrib);
                    doubleScroll1.start();
                } else {
                    doubleScroll1.updateValues("Price", new ScrollObject(quote1.getPrice().setScale(2, RoundingMode.HALF_UP)));
                    doubleScroll1.updateValues("Chng", new ScrollObject(quote1.getChange().setScale(2, RoundingMode.HALF_UP), true));
                }

                if (!doubleScroll2.isRunning()) {
                    ConcurrentHashMap<String, ScrollObject> double2attrib = new ConcurrentHashMap<>();
                    double2attrib.put("Price", new ScrollObject(quote2.getPrice().setScale(2, RoundingMode.HALF_UP)));
                    double2attrib.put("Chng", new ScrollObject(quote2.getChange().setScale(2, RoundingMode.HALF_UP), true));
                    doubleScroll2.setValues(double2attrib);
                    doubleScroll2.start();
                } else {
                    doubleScroll2.updateValues("Price", new ScrollObject(quote2.getPrice().setScale(2, RoundingMode.HALF_UP)));
                    doubleScroll2.updateValues("Chng", new ScrollObject(quote2.getChange().setScale(2, RoundingMode.HALF_UP), true));
                }
            } else {
                if (doubleScroll1.isRunning()) {
                    doubleScroll1.stop();
                }
                if (doubleScroll2.isRunning()) {
                    doubleScroll2.stop();
                }

                if (!prevTickers.getLayoutStyle().equals(stockTickers.getLayoutStyle()) || !prevTickers.getManyTicker1().equals(stockTickers.getManyTicker1())) {
                    writeToPanel(0, "          ");
                    writeToPanel(1, "          ");
                    writeToPanel(2, "          ");
                }

                writeToPanel(0, stockTickers.getManyTicker1());
                writeToPanel(1, stockTickerManager.getStockQuote(stockTickers.getManyTicker1()).getPrice().setScale(2, RoundingMode.HALF_UP).toString());

                BigDecimal change = stockTickerManager.getStockQuote(stockTickers.getManyTicker1()).getChange();
                if (change.compareTo(new BigDecimal(0)) < 0) {
                    writeToPanel(2, change.setScale(2, RoundingMode.HALF_UP).toString(), new Color(255, 0, 0));
                } else if (change.compareTo(new BigDecimal(0)) > 0) {
                    writeToPanel(2, "+" + change.setScale(2, RoundingMode.HALF_UP).toString(), new Color(0, 255, 0));
                } else {
                    writeToPanel(2, change.toString());
                }

                if (!manyScroll.isRunning()) {
                    ConcurrentHashMap<String, ScrollObject> manyStocks = new ConcurrentHashMap<>();
                    manyStocks.put(stockTickers.getManyTicker2(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker2()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyStocks.put(stockTickers.getManyTicker3(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker3()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyStocks.put(stockTickers.getManyTicker4(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker4()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyStocks.put(stockTickers.getManyTicker5(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker5()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyScroll.setValues(manyStocks);
                    manyScroll.start();
                } else {
                    if (!prevTickers.getManyTicker2().equals(stockTickers.getManyTicker2())) {
                        manyScroll.changeKey(prevTickers.getManyTicker2(), stockTickers.getManyTicker2());
                    }
                    if (!prevTickers.getManyTicker3().equals(stockTickers.getManyTicker3())) {
                        manyScroll.changeKey(prevTickers.getManyTicker3(), stockTickers.getManyTicker3());
                    }
                    if (!prevTickers.getManyTicker4().equals(stockTickers.getManyTicker4())) {
                        manyScroll.changeKey(prevTickers.getManyTicker4(), stockTickers.getManyTicker4());
                    }
                    if (!prevTickers.getManyTicker5().equals(stockTickers.getManyTicker5())) {
                        manyScroll.changeKey(prevTickers.getManyTicker5(), stockTickers.getManyTicker5());
                    }
                    manyScroll.updateValues(stockTickers.getManyTicker2(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker2()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyScroll.updateValues(stockTickers.getManyTicker3(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker3()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyScroll.updateValues(stockTickers.getManyTicker4(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker4()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                    manyScroll.updateValues(stockTickers.getManyTicker5(), new ScrollObject(stockTickerManager.getStockQuote(stockTickers.getManyTicker5()).getChange().setScale(2, RoundingMode.HALF_UP), true));
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
        }
    }
}
