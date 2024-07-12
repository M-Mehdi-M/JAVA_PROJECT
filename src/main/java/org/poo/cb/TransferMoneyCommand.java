package org.poo.cb;

public class TransferMoneyCommand implements Command { // COMMAND DESIGN PATTERN
    private Utilizator utilizator;
    private String friendEmail;
    private String currency;
    private double amount;

    public TransferMoneyCommand(Utilizator utilizator, String friendEmail, String currency, double amount) {
        this.utilizator = utilizator;
        this.friendEmail = friendEmail;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute() {
        utilizator.transferMoney(friendEmail, currency, amount);
    }
}
