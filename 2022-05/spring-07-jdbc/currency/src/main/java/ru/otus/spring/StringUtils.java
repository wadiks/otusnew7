package ru.otus.spring;

public final class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return null == str || str.isBlank();
    }

    public static boolean isNullOrWhitespace(String str) {
        return null == str || str.trim().isBlank();
    }

    private StringUtils() {
        throw new RuntimeException("Couldn't create instance of utility class.");
    }
}
