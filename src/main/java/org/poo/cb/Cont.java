package org.poo.cb;

public class Cont {
    private String currencyName;
    private double amount;

    public Cont(String currencyName) {
        this.currencyName = currencyName;
        this.amount = 0.00;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getAmount() {
        return amount;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }

    @Override
    public String toString() {
        return "{" +
                "\"currencyName\":\"" + currencyName + '\"' +
                ",\"amount\":\"" + amount + '\"' +
                "}";
    }
}
