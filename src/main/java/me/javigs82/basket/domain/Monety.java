package me.javigs82.basket.domain;

import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * This class represent how to define money in the system
 * Assuming everything is un euros, set Locale as Germany due to EU Bank is there.
 */
public final class Monety {
    public static final Locale LOCALE = Locale.GERMANY;
    public static final Currency CURRENCY = Currency.getInstance(LOCALE);
    public final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
}
