package com.stonks.test;

import com.stonks.code.Text5x7;
import com.stonks.code.VirtualLEDMatrix;

import java.awt.*;
import java.util.ArrayList;

public class TestLedPanel {
    public static void main(String[] args) {
        VirtualLEDMatrix matrix = new VirtualLEDMatrix();

        ArrayList<String[]> chars = Text5x7.getLetters("GME 123.21");
        for (int i = 0; i < 7; i++) {
            String row = "";
            for (String[] s : chars) {
                row += s[i];
                row += " ";
            }

            for (int f = 0; f < row.length(); f++) {
                if (row.charAt(f) == '0') {
                    matrix.setPixel(f, i, new Color(255, 255, 255));
                }
            }
        }
    }
}
