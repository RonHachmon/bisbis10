package com.att.tdp.bisbis10.utils;

public class Validation {
    public static boolean isNameValid(String s) {
        return s != null && !s.isEmpty();
    }
    public static boolean isPriceValid(Integer price) {
        return price != null && price > 0;
    }

}
