package org.poo.cb;

public class Stocks { // STRATEGY DESIGN PATTERN
    private String companyName;
    private double numberOfStocks;

    public Stocks(String companyName, double numberOfStocks) {
        this.companyName = companyName;
        this.numberOfStocks = numberOfStocks;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getNumberOfStocks() {
        return (int) this.numberOfStocks;
    }

    public double ultimulPret(String stockValues) {
        FileReaderContext context = new FileReaderContext(new fileReader());
        return context.ultimulPret(stockValues, this.companyName);
    }
}
