package com.stonks.code;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.stonks.code.Font5x7.*;

public class Text5x7 {
    public static HashMap<Character, Color> colors = new HashMap<>(){{
        put('0', new Color(255, 255, 255));
        put('r', new Color(255, 0, 0));
        put('g', new Color(0, 255, 0));
    }};

    public static ArrayList<String[]> getLetters(String str) {
        ArrayList<String[]> list = new ArrayList<>();
        char [] array = str.toUpperCase().toCharArray();
        for(char ch : array) {
            if (ch == 'A') {
                list.add(a.clone());
            } else if (ch == 'B') {
                list.add(b.clone());
            } else if (ch == 'C') {
                list.add(c.clone());
            } else if (ch == 'D') {
                list.add(d.clone());
            } else if (ch == 'E') {
                list.add(e.clone());
            } else if (ch == 'F') {
                list.add(f.clone());
            } else if (ch == 'G') {
                list.add(g.clone());
            } else if (ch == 'H') {
                list.add(h.clone());
            } else if (ch == 'I') {
                list.add(i.clone());
            } else if (ch == 'J') {
                list.add(j.clone());
            } else if (ch == 'K') {
                list.add(k.clone());
            } else if (ch == 'L') {
                list.add(l.clone());
            } else if (ch == 'M') {
                list.add(m.clone());
            } else if (ch == 'N') {
                list.add(n.clone());
            } else if (ch == 'O') {
                list.add(o.clone());
            } else if (ch == 'P') {
                list.add(p.clone());
            } else if (ch == 'Q') {
                list.add(q.clone());
            } else if (ch == 'R') {
                list.add(r.clone());
            } else if (ch == 'S') {
                list.add(s.clone());
            } else if (ch == 'T') {
                list.add(t.clone());
            } else if (ch == 'U') {
                list.add(u.clone());
            } else if (ch == 'V') {
                list.add(v.clone());
            } else if (ch == 'W') {
                list.add(w.clone());
            } else if (ch == 'X') {
                list.add(x.clone());
            } else if (ch == 'Y') {
                list.add(y.clone());
            } else if (ch == 'Z') {
                list.add(z.clone());
            } else if (ch == '0') {
                list.add(zero.clone());
            } else if (ch == '1') {
                list.add(one.clone());
            } else if (ch == '2') {
                list.add(two.clone());
            } else if (ch == '3') {
                list.add(three.clone());
            } else if (ch == '4') {
                list.add(four.clone());
            } else if (ch == '5') {
                list.add(five.clone());
            } else if (ch == '6') {
                list.add(six.clone());
            } else if (ch == '7') {
                list.add(seven.clone());
            } else if (ch == '8') {
                list.add(eight.clone());
            } else if (ch == '9') {
                list.add(nine.clone());
            } else if (ch == '.') {
                list.add(decimal.clone());
            } else if (ch == ':') {
                list.add(colon.clone());
            } else if (ch == '$') {
                list.add(dollar.clone());
            } else if (ch == '%') {
                list.add(percent.clone());
            } else if (ch == '-') {
                list.add(minus.clone());
            } else if (ch == '+') {
                list.add(plus.clone());
            } else if (ch == ' ') {
                list.add(space.clone());
            } else if (ch == '↑') {     //alt + 24
                list.add(inc.clone());
            } else if (ch == '↓') {     //alt + 25
                list.add(dec.clone());
            } else if (ch == '|') {
                list.add(bar.clone());
            }
        }
        return list;
    }

    public static ArrayList<String[]> getLetters(String str, Color color) {
        ArrayList<String[]> temp = getLetters(str);
        for (char c : colors.keySet()) {
            if (
                    colors.get(c).getRed() == color.getRed() &&
                    colors.get(c).getGreen() == color.getGreen() &&
                    colors.get(c).getBlue() == color.getBlue()
            ) {
                for (String[] strings : temp) {
                    for (int j = 0; j < strings.length; j++) {
                        strings[j] = strings[j].replace('0', c);
                    }
                }
                break;
            }
        }
        return temp;
    }

    static int getWidth(String text) {
        return (text.length() + 2) * 5;
    }

    static int getHeight(String text) {
        return 7;
    }

    static void printText(ArrayList<String[]> strings) {
        for (int i = 0; i < 7; i++) {
            for(String[] s : strings) {
                System.out.print(s[i]);
                System.out.print("   ");
            }
            System.out.println();
        }
    }


}
