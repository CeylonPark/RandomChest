package com.ceylon.randomchest.util;

public class NumberFormat {
    public static int parseInt(String s, int instead) {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return instead;
        }
    }
}
