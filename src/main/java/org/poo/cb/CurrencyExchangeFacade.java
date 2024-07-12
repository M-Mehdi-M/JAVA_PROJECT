package org.poo.cb;

public class CurrencyExchangeFacade { // FACADE DESIGN PATTERN
    private CurrencyExchange currencyExchange;

    public CurrencyExchangeFacade(String rates) {
        this.currencyExchange = new CurrencyExchangeService(rates);
    }

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        return currencyExchange.getExchangeRate(fromCurrency, toCurrency);
    }
}
