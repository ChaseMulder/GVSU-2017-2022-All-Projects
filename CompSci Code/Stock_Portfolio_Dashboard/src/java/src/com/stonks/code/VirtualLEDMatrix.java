package com.stonks.code;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class VirtualLEDMatrix implements LEDMatrix {
    private JFrame frame;
    private JPanel panel;
    private LEDMatrixPanel matrixPanel;

    public VirtualLEDMatrix() {
        this.frame = new JFrame("Virtual LED Matrix");

        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 10)));

        this.matrixPanel = new LEDMatrixPanel(16);
        this.panel.add(this.matrixPanel);

        this.frame.getContentPane().add(this.panel);
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.frame.validate();
        this.frame.setVisible(true);
        this.frame.pack();
        this.frame.setResizable(false);
    }

    private class LEDMatrixPanel extends JPanel {
        ConcurrentHashMap<String, Diode> diodes;

        public LEDMatrixPanel(int mult) {

            int width = 64 * mult;
            int height = 32 * mult;

            this.setPreferredSize(new Dimension(width, height));
            this.diodes = new ConcurrentHashMap<>();
        }

        public void add(Diode d) {
            this.diodes.put("" + d.x + "-" + d.y, d);
            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D panelBackground = (Graphics2D) g.create();
            panelBackground.setColor(Color.BLACK);
            panelBackground.fill(new Rectangle(this.getWidth(), this.getHeight()));

            diodes.forEach((key, diode) -> {
                panelBackground.setColor(diode.c);
                panelBackground.fill(new Ellipse2D.Double(diode.x * 16, diode.y * 16, 14, 14));
            });
        }
    }

    private static class Diode extends Point {
        private Color c;

        public Diode(int x, int y, Color c) {
            super(x, y);
            this.c = c;
        }
    }

    public void setPixel(int x, int y, Color rgb) {
        this.matrixPanel.add(new Diode(x, y, rgb));
    }
}
