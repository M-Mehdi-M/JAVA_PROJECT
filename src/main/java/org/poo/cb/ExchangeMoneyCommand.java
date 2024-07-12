package org.poo.cb;

public class ExchangeMoneyCommand implements Command { // COMMAND DESIGN PATTERN
    private Utilizator utilizator;
    private String contSur;
    private String contDes;
    private double amount;
    private double exchangeRate;

    public ExchangeMoneyCommand(Utilizator utilizator, String contSur, String contDes, double amount, double exchangeRate) {
        this.utilizator = utilizator;
        this.contSur = contSur;
        this.contDes = contDes;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void execute() {
        utilizator.exchangeMoney(contSur, contDes, amount, exchangeRate);
    }
}
