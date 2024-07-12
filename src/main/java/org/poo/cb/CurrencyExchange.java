package org.poo.cb;

public interface CurrencyExchange { // FACADE DESIGN PATTERN
    double getExchangeRate(String fromCurrency, String toCurrency);
}

