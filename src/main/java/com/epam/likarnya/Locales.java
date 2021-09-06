package com.epam.likarnya;

import java.util.Locale;

public final class Locales {
    private static final Locale ru = new Locale("ru", "RU");
    private static final Locale en = new Locale("en", "US");

    public static Locale getLocale(final String locale) {
        Locale loc = en;
        if (locale.equalsIgnoreCase("ru")) {
            loc = ru;
        }
        return loc;
    }

    private Locales() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
