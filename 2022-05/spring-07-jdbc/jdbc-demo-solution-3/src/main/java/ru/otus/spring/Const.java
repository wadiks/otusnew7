package ru.otus.spring;


public final class Const {
    public static final String EX_MSG_UTILITY_INSTANCE = "Couldn't create instance of utility class. ";

    private Const() {
        throw new RuntimeException(EX_MSG_UTILITY_INSTANCE);
    }

    /**
     * Переменная для URL обращения загрузки курсов
     * передача даты формат dd.MM.yyyy
     */
    public static final String URL_REQUEST_CURRENCY = " http://localhost:8888/api/all_currency_date?data=";
}