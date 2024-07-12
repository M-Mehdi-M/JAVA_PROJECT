package org.poo.cb;

import java.io.*;

public class fileReader implements FileReadingStrategy { // STRATEGY DESIGN PATTERN
    @Override
    public String readFile(String file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int character;
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return content.toString();
    }

    @Override
    public double ultimulPret(String stockValues, String companyName) {
        double ultimulPret = 0.0;
        for (String s : stockValues.split("\n")) {
            if (s.split(",")[0].equals(companyName)) {
                String[] stockPret = s.split(",");
                ultimulPret = Double.parseDouble(stockPret[stockPret.length - 1]);
            }
        }
        return ultimulPret;
    }
}
