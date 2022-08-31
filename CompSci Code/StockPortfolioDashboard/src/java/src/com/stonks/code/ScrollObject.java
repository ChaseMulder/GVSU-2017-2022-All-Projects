package com.stonks.code;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScrollObject {
    private String s;
    private BigDecimal d;
    private int type;
    public boolean colorCode;

    public ScrollObject(String s) {
        this.s = s;
        type = 0; // string
        colorCode = false;
    }

    public ScrollObject(BigDecimal d) {
        this.d = d;
        type = 1; // bigdecimal
        colorCode = false;
    }

    public ScrollObject(BigDecimal d, boolean colorCode) {
        this.d = d;
        type = 1; // bigdecimal
        this.colorCode = colorCode;
    }

    public int length() {
        return type == 0 ? s.length() : d.toString().length();
    }

    public Color getColor() {
        if (colorCode) {
            if (d.compareTo(new BigDecimal(0)) < 0) {
                return new Color(255, 0, 0);
            } else if (d.compareTo(new BigDecimal(0)) > 0) {
                return new Color(0, 255, 0);
            } else {
                return new Color(255, 255, 255);
            }
        } else {
            return new Color(255, 255, 255);
        }
    }

    @Override
    public String toString() {
        return type == 0 ? s : d.toString();
    }
}
