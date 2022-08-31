package com.stonks.test;

import com.stonks.code.LEDMatrix;
import com.stonks.code.LEDPanelScrollingText;
import com.stonks.code.ScrollObject;
import com.stonks.code.VirtualLEDMatrix;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class ScrollingTextTest {
    public static void main(String[] args) {
        LEDMatrix matrix = new VirtualLEDMatrix();
        LEDPanelScrollingText scrollingText = new LEDPanelScrollingText(0, matrix);
        LEDPanelScrollingText scrollingText2 = new LEDPanelScrollingText(1, matrix);
        LEDPanelScrollingText scrollingText3 = new LEDPanelScrollingText(2, matrix);
        LEDPanelScrollingText scrollingText4 = new LEDPanelScrollingText(3, matrix);


        ConcurrentHashMap<String, ScrollObject> map = new ConcurrentHashMap<>();
        map.put("GME", new ScrollObject(new BigDecimal("189.24")));
        map.put("AMC", new ScrollObject(new BigDecimal("34.23")));
        map.put("TSLA", new ScrollObject(new BigDecimal("1023.78")));

        scrollingText.setValues(map);
        scrollingText.start();

        scrollingText2.setValues(map);
        scrollingText2.start();

        scrollingText3.setValues(map);
        scrollingText3.start();

        scrollingText4.setValues(map);
        scrollingText4.start();

        System.out.println("A Virtual LED Matrix should have opened and displayed 4 rows of scrolling text.");
        System.out.println("The scrolling test should display the values coded into the class.");
        System.out.println("If this is the case, the test has passed and you can close the window. Otherwise, the test has failed.");
    }
}
