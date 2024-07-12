package org.poo.cb;

import java.util.*;

public class CurrencyExchangeService implements CurrencyExchange { // FACADE DESIGN PATTERN
    private Map<String, Map<String, Double>> exchangeRates;

    public CurrencyExchangeService(String rates) {
        this.exchangeRates = parseRates(rates);
    }

    @Override
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        double rate = 0;
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.get(fromCurrency).containsKey(toCurrency)) {
            rate = exchangeRates.get(fromCurrency).get(toCurrency);
        }
        return rate;
    }

    private Map<String, Map<String, Double>> parseRates(String rates) {
        Map<String, Map<String, Double>> exchangeRates = new HashMap<>();
        String[] rows = rates.split("\n");
        String[] currencies = rows[0].split(",");
        for (int i = 1; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            Map<String, Double> ratesMap = new HashMap<>();
            for (int j = 1; j < values.length; j++) {
                ratesMap.put(currencies[j], Double.parseDouble(values[j]));
            }
            exchangeRates.put(currencies[i], ratesMap);
        }
        return exchangeRates;
    }
}
