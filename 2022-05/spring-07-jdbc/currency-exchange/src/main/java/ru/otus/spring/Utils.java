package ru.otus.spring;

import java.util.Arrays;

public final class Utils {

    private Utils() {
        throw new RuntimeException("Couldn't create instance of utility class.");
    }
    public static String maskString(String strToMask) {
        return maskString('*', false, strToMask);
    }

    public static String maskString(char maskChar, boolean maskLen, String strToMask) {
        if (null == strToMask) {
            return "null";
        } else if (strToMask.isBlank()) {
            return "";
        } else {
            char[] chars;
            if (maskLen) {
                chars = new char[8];
                Arrays.fill(chars, maskChar);
                return new String(chars);
            } else {
                chars = strToMask.toCharArray();
                Arrays.fill(chars, maskChar);
                return new String(chars);
            }
        }
    }
}
