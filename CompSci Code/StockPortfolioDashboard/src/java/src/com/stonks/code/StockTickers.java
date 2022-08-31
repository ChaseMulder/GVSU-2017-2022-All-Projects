package com.stonks.code;

import java.util.ArrayList;

public class StockTickers {
    private String layoutStyle;
    private String singleTicker;
    private String doubleTicker1;
    private String doubleTicker2;
    private String manyTicker1;
    private String manyTicker2;
    private String manyTicker3;
    private String manyTicker4;
    private String manyTicker5;

    public StockTickers(String layoutStyle, String singleTicker, String doubleTicker1, String doubleTicker2, String manyTicker1, String manyTicker2, String manyTicker3, String manyTicker4, String manyTicker5) {
        this.layoutStyle = layoutStyle;
        this.singleTicker = singleTicker;
        this.doubleTicker1 = doubleTicker1;
        this.doubleTicker2 = doubleTicker2;
        this.manyTicker1 = manyTicker1;
        this.manyTicker2 = manyTicker2;
        this.manyTicker3 = manyTicker3;
        this.manyTicker4 = manyTicker4;
        this.manyTicker5 = manyTicker5;
    }

    public String getLayoutStyle() {
        return layoutStyle;
    }

    public String getSingleTicker() {
        return singleTicker;
    }

    public String getDoubleTicker1() {
        return doubleTicker1;
    }

    public String getDoubleTicker2() {
        return doubleTicker2;
    }

    public String getManyTicker1() {
        return manyTicker1;
    }

    public String getManyTicker2() {
        return manyTicker2;
    }

    public String getManyTicker3() {
        return manyTicker3;
    }

    public String getManyTicker4() {
        return manyTicker4;
    }

    public String getManyTicker5() {
        return manyTicker5;
    }

    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(singleTicker);
        list.add(doubleTicker1);
        list.add(doubleTicker2);
        list.add(manyTicker1);
        list.add(manyTicker2);
        list.add(manyTicker3);
        list.add(manyTicker4);
        list.add(manyTicker5);
        return list;
    }
}
