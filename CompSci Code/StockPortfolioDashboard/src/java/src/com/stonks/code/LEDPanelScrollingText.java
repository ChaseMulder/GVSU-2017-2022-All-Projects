package com.stonks.code;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class LEDPanelScrollingText implements Runnable {
    private int line;
    private Thread t;
    private boolean run = false;
    private LEDMatrix matrix;
    private ConcurrentHashMap<String, ScrollObject> values;
    private String[] valuesKeySet;
    private ConcurrentHashMap<String, ScrollObject> pendingUpdatesToValues;

    public LEDPanelScrollingText(int line, LEDMatrix matrix) {
        this.line = line;
        this.matrix = matrix;
        this.pendingUpdatesToValues = new ConcurrentHashMap<>();
        this.values = new ConcurrentHashMap<>();
        this.valuesKeySet = new String[1];
    }

    public void setValues(ConcurrentHashMap<String, ScrollObject> values) {
        if (!run) {
            this.values = values;

            this.valuesKeySet = values.keySet().toArray(new String[0]);
        }
    }

    public void changeKey(String oldKey, String newKey) {
        if (values.containsKey(oldKey)) {
            values.put(newKey, values.remove(oldKey));
            for (int i = 0; i < valuesKeySet.length; i++) {
                if (valuesKeySet[i].equals(oldKey)) {
                    valuesKeySet[i] = newKey;
                }
            }

            if (pendingUpdatesToValues.containsKey(oldKey)) {
                pendingUpdatesToValues.put(newKey, pendingUpdatesToValues.remove(oldKey));
            }
        }
    }

    public void updateValues(String key, ScrollObject value) {
        if (values.containsKey(key)) {
            if (values.size() > 1) {
                pendingUpdatesToValues.put(key, value);
            } else {
                values.put(key, value);
            }
        }
    }

    public void start() {
        if (t == null) {
            run = true;
            t = new Thread(this);
            t.start();
        }
    }

    public void stop() {
        if (t != null) {
            run = false;
            while (t.isAlive()) {}
            for (int i = 0; i < 7; i++) {
                for (int j = 2; j < 62; j++) {
                    matrix.setPixel(j, i + 1 + 8 * line, new Color(0, 0, 0));
                }
            }
            t = null;
        }
    }

    public boolean isRunning() {
        return this.run;
    }

    @Override
    public void run() {
        int currValue = 0;
        int currChar = 0;
        int charRowOffset = 0;

        while (run) {
            String[] keys = valuesKeySet;

            ArrayList<String[]> panelText = new ArrayList<>(Text5x7.getLetters(keys[currValue] + " - "));
            ScrollObject object = values.get(keys[currValue]);
            if (object.colorCode) {
                panelText.addAll(Text5x7.getLetters(object.toString(), object.getColor()));
            } else {
                panelText.addAll(Text5x7.getLetters(object.toString()));
            }
            panelText.addAll(Text5x7.getLetters(" | "));
            if (panelText.size() - currChar - 1 <= 10) {
                int nextVal = currValue + 1 < keys.length ? currValue + 1 : currValue + 1 - keys.length;
                panelText.addAll(Text5x7.getLetters(keys[nextVal] + " - "));
                ScrollObject object2 = values.get(keys[nextVal]);
                if (object2.colorCode) {
                    panelText.addAll(Text5x7.getLetters(object2.toString(), object2.getColor()));
                } else {
                    panelText.addAll(Text5x7.getLetters(object2.toString()));
                }
                panelText.addAll(Text5x7.getLetters(" | "));
            }

            for (int i = 0; i < 7; i++) {
                String row = "";
                for (String[] s : panelText) {
                    row += s[i];
                    row += " ";
                }

                int start = currChar * 6 + charRowOffset;
                String subRow = row.substring(start, start + 60);
                for (int j = 0; j < subRow.length(); j++) {
                    if (subRow.charAt(j) != ' ') {
                        matrix.setPixel(j + 2, this.line * 8 + i + 1, Text5x7.colors.getOrDefault(subRow.charAt(j), new Color(255, 255, 255)));
                    } else {
                        matrix.setPixel(j + 2, this.line * 8 + i + 1, new Color (0, 0, 0));
                    }
                }
            }

            for (String key : pendingUpdatesToValues.keySet()) {
                if (!keys[currValue].equals(key)) {
                    values.put(key, pendingUpdatesToValues.remove(key));
                }
            }

            charRowOffset++;
            if (charRowOffset > 5) {
                charRowOffset = 0;
                currChar++;
            }
            if (currChar > (keys[currValue].length() + values.get(keys[currValue]).length() + 5)) {
                currChar = 0;
                currValue++;
            }
            if (currValue > keys.length - 1) {
                currValue = 0;
            }

            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {

            }
        }
    }

}
