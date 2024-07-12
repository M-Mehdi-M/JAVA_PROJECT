package org.poo.cb;

public class FileReaderContext { // STRATEGY DESIGN PATTERN
    private FileReadingStrategy strategy;

    public FileReaderContext(FileReadingStrategy strategy) {
        this.strategy = strategy;
    }

    public String readFile(String file) {
        return strategy.readFile(file);
    }
    public double ultimulPret(String stockValues, String companyName) {
        return strategy.ultimulPret(stockValues, companyName);
    }
}
