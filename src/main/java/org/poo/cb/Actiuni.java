package org.poo.cb;

import java.util.*;

public class Actiuni {
    private Map<String, List<Double>> stockData;

    public Actiuni() {
        this.stockData = new HashMap<>();
    }

    public void addStock(String stockLine) {
        String[] stockInfo = stockLine.split(",");
        String stockName = stockInfo[0];
        List<Double> values = new ArrayList<>();
        for (int i = 1; i < stockInfo.length; i++) {
            values.add(Double.parseDouble(stockInfo[i]));
        }
        stockData.put(stockName, values);
    }

    public List<String> recommendStocks() {
        List<String> recommendedStocks = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : stockData.entrySet()) {
            String stockName = entry.getKey();
            List<Double> values = entry.getValue();
            double smaCinciZile = calculateSMA(values.subList(values.size() - 5, values.size()));
            double smaZeceZile = calculateSMA(values.subList(values.size() - 10, values.size()));
            if (smaCinciZile > smaZeceZile) {
                recommendedStocks.add("\"" + stockName + "\"");
            }
        }
        return recommendedStocks;
    }

    private double calculateSMA(List<Double> values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }
}
