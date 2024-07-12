package org.poo.cb;

import java.util.*;

public class Utilizator {
    private String email;
    private String nume;
    private String prenume;
    private String adresa;
    private List<Cont> portofoliu;
    private List<Stocks> purchasedStocksList;
    private List<Utilizator> prieteni;
    private Boolean premium;

    public Utilizator(String email, String nume, String prenume, String adresa) {
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.portofoliu = new ArrayList<>();
        this.prieteni = new ArrayList<>();
        this.purchasedStocksList = new ArrayList<>();
        this.premium = false;
    }

    public String getEmail() {
        return email;
    }

    public void setPremium() {
        this.premium = true;
    }

    public boolean getPremium() {
        return premium;
    }

    public void adaugaPrieten(Utilizator prieten) {
        if (!UserManager.getInstance().userExistsWithEmail(prieten.getEmail())) {
            System.out.println("User with " + prieten.getEmail() + " doesn't exist");
            return;
        }
        if (prieteni.contains(prieten)) {
            System.out.println("User with " + prieten.getEmail() + " is already a friend");
            return;
        }
        prieteni.add(prieten);
        prieten.prieteni.add(this);
    }

    public void addAccount(String currency) {
        if (findAccountByCurrency(currency) != null) {
            System.out.println("Account in currency " + currency + " already exists for user");
            return;
        }
        Cont newAccount = new Cont(currency);
        portofoliu.add(newAccount);
    }

    public Cont alimentareCont(String currencyName, double amount) {
        Cont account = null;
        for (Cont existingAccount : portofoliu) {
            if (existingAccount.getCurrencyName().equals(currencyName)) {
                account = existingAccount;
                break;
            }
        }
        if (account != null) {
            account.addAmount(amount);
        }
        return account;
    }

    public void exchangeMoney(String contSur, String contDes, double amount, double exchangeRate) {
        Cont cont1 = null;
        Cont cont2 = null;
        double num = amount * exchangeRate;
        for (Cont account : portofoliu) {
            if (account.getCurrencyName().equals(contSur)) {
                cont1 = account;
            } else if (account.getCurrencyName().equals(contDes)) {
                cont2 = account;
            }
        }
        if (cont1.getAmount() < amount) {
            System.out.println("Insufficient amount in account " + contSur + " for exchange");
            return;
        }
        if (num > (0.5 * cont1.getAmount())) {
            double commission;
            if (getPremium()) {
                commission = 0;
            } else {
                commission = 0.01 * num;
            }
            cont1.addAmount((-amount * exchangeRate) - commission);
            cont2.addAmount(amount);
        } else {
            cont1.addAmount(-(amount * exchangeRate));
            cont2.addAmount(amount);
        }
    }

    public void transferMoney(String friendEmail, String currency, double amount) {
        Utilizator friend = null;
        for (Utilizator prieten : prieteni) {
            if (prieten.getEmail().equals(friendEmail)) {
                friend = prieten;
                break;
            }
        }
        if (friend == null) {
            System.out.println("You are not allowed to transfer money to " + friendEmail);
            return;
        }
        Cont contSursa = findAccountByCurrency(currency);
        if (contSursa.getAmount() < amount) {
            System.out.println("Insufficient amount in account " + currency + " for transfer");
            return;
        }
        contSursa.addAmount(-amount);
        friend.alimentareCont(currency, amount);
    }

    public void buyStocks(String company, double numberOfStocks, double reduceAmount, boolean isInRecommendedStocks) {
        Cont usdAccount = findAccountByCurrency("USD");
        if (usdAccount.getAmount() < reduceAmount) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }
        if (getPremium() && isInRecommendedStocks) {
            reduceAmount = reduceAmount - (reduceAmount * (5/100.0));
        }
        usdAccount.addAmount(-reduceAmount);
        Stocks purchasedStocks = new Stocks(company, numberOfStocks);
        purchasedStocksList.add(purchasedStocks);
    }

    public void buyPremium() {
        Cont usdAccount = findAccountByCurrency("USD");
        if (usdAccount.getAmount() < 100) {
            System.out.println("Insufficient amount in account for buying premium option");
            return;
        }
        usdAccount.addAmount(-100);
        setPremium();
    }

    public String listPortfolio() {
        StringBuilder result = new StringBuilder();
        result.append("{\"stocks\":[");
        for (Stocks stocks : purchasedStocksList) {
            result.append("{")
                    .append("\"stockName\":\"").append(stocks.getCompanyName()).append("\",")
                    .append("\"amount\":").append(stocks.getNumberOfStocks()).append("")
                    .append("},");
        }
        if (!purchasedStocksList.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("],\"accounts\":[");
        for (Cont account : portofoliu) {
            result.append("{")
                    .append("\"currencyName\":\"").append(account.getCurrencyName()).append("\",")
                    .append("\"amount\":\"").append(String.format("%.2f", account.getAmount())).append("\"")
                    .append("},");
        }
        if (!portofoliu.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("]}");
        return result.toString();
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    private Cont findAccountByCurrency(String currency) {
        for (Cont account : portofoliu) {
            if (account.getCurrencyName().equals(currency)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder friendList = new StringBuilder("[");
        for (Utilizator prieten : prieteni) {
            friendList.append("\"").append(prieten.getEmail()).append("\", ");
        }
        if (friendList.length() > 1) {
            friendList.setLength(friendList.length() - 2);
        }
        friendList.append("]");
        return "{" +
                "\"email\":\"" + email + '\"' +
                ",\"firstname\":\"" + nume + '\"' +
                ",\"lastname\":\"" + prenume + '\"' +
                ",\"address\":\"" + adresa + '\"' +
                ",\"friends\":" + friendList +
                "}";
    }
}
