package org.poo.cb;

public class BuyStocksCommand implements Command { // COMMAND DESIGN PATTERN
    private Utilizator utilizator;
    private String company;
    private double numberOfStocks;
    private double reduceAmount;
    private boolean isInRecommendedStocks;

    public BuyStocksCommand(Utilizator utilizator, String company, double numberOfStocks, double reduceAmount, boolean isInRecommendedStocks) {
        this.utilizator = utilizator;
        this.company = company;
        this.numberOfStocks = numberOfStocks;
        this.reduceAmount = reduceAmount;
        this.isInRecommendedStocks = isInRecommendedStocks;
    }

    @Override
    public void execute() {
        utilizator.buyStocks(company, numberOfStocks, reduceAmount, isInRecommendedStocks);
    }
}
