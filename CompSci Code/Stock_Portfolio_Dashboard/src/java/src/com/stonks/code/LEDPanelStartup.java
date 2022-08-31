package com.stonks.code;

import java.awt.*;
import java.util.ArrayList;

public class LEDPanelStartup {
    private LEDMatrix matrix;
    private int[] heights = { 0, 0, 1, 1, 2, 3, 4, 5, 6, 6, 7, 7, 8, 9, 10, 11, 10, 9, 8, 7, 6, 7, 8, 9, 10, 10, 9, 9, 8,
            7, 6, 5, 4, 4, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 13, 12, 11, 11, 10, 10, 11, 12, 13, 14};
    private final String connectionUrl = "jdbc:mysql://localhost:3306/stonks";
    private final String username = "root";
    private final String password = "";

    public LEDPanelStartup(LEDMatrix matrix) {
        this.matrix = matrix;
    }

    // returns true if initialization succeeded, false if not
    public boolean start() {
        for (int i = 0; i < heights.length; i++) {
            matrix.setPixel(i + 2, (14 - heights[i]), new Color(0, 255, 0));

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }

        writeToPanel(2, "Stonk", new Color(0, 255, 0));
        writeToPanel(3, "Ticker", new Color(0, 255, 0));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        boolean dbWorks = DatabaseQuery.tryDB(connectionUrl, username, password);
        if (!dbWorks) {
            writeToPanel(2, "Error: No", new Color(255, 0, 0));
            writeToPanel(3, "DB Avail.", new Color(255, 0, 0));
            return false;
        }

        boolean yfWorks = StockTickerMngr.tryYahooFinance();
        if (!yfWorks) {
            writeToPanel(2, "Erorr: YF", new Color(255, 0 , 0));
            writeToPanel(3, "Not Avail.", new Color(255, 0, 0));
            return false;
        }

        return true;
    }

    private void writeToPanel (int line, String text, Color color) {
        ArrayList<String[]> chars;
        if (text.length() > 10) {
            chars = Text5x7.getLetters(text.substring(0, 10));
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
                    matrix.setPixel(j + 2, i + 1 + (line * 8), color);
                } else {
                    matrix.setPixel(j + 2, i + 1 + (line * 8), new Color(0, 0, 0));
                }
            }
        }
    }
}
