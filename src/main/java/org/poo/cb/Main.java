package org.poo.cb;

import java.util.*;

public class Main { // DESIGN PATTERNS (SINGLETON, FACADE, STRATEGY, COMMAND)

    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();
        if (args == null) {
            userManager.showMessage();
        } else {
            FileReaderContext context = new FileReaderContext(new fileReader());
            List<Utilizator> utilizatoriList = new ArrayList<>();
            List<String> recommendedStocks = new ArrayList<>();
            String exchangeRates = context.readFile("src/main/resources/" + args[0]);
            String stockValues1 = context.readFile("src/main/resources/" + args[1]);
            String[] commands = context.readFile("src/main/resources/" + args[2]).split("\n");
            List<String> stockValues2 = new ArrayList<>(Arrays.asList(stockValues1.split("\n")));
            stockValues2.remove(0);
            for (String command : commands) {
                if (command.contains("CREATE USER")) {
                    String[] info = command.split(" ");
                    String address = "";
                    for (int i = 5; i < info.length; i++) {
                        if (i == info.length - 1) {
                            address += info[i];
                            break;
                        }
                        address += info[i] + " ";
                    }
                    if (userManager.userExistsWithEmail(info[2])) {
                        System.out.println("User with " + info[2] + " already exists");
                    } else {
                        Utilizator newUser = new Utilizator(info[2], info[3], info[4], address);
                        utilizatoriList.add(newUser);
                        userManager.adaugaUtilizator(newUser);
                    }
                } else if (command.contains("ADD FRIEND")) {
                    String[] info = command.split(" ");
                    Utilizator user1 = null;
                    Utilizator user2 = null;
                    for (Utilizator user : utilizatoriList) {
                        if (user.getEmail().equals(info[2])) {
                            user1 = user;
                        } else if (user.getEmail().equals(info[3])) {
                            user2 = user;
                        }
                    }
                    if (user1 != null && user2 != null) {
                        user1.adaugaPrieten(user2);
                    } else if (user1 == null) {
                        System.out.println("User with "+ info[2] +" doesn't exist");
                    } else {
                        System.out.println("User with "+ info[3] +" doesn't exist");
                    }
                } else if (command.contains("ADD ACCOUNT")) {
                    String[] info = command.split(" ");
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    if (user != null) {
                        user.addAccount(info[3]);
                    }
                } else if (command.contains("ADD MONEY")) {
                    String[] info = command.split(" ");
                    double amount = Double.parseDouble(info[4]);
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    user.alimentareCont(info[3], amount);
                } else if (command.contains("EXCHANGE MONEY")) {
                    String[] info = command.split(" ");
                    double amount = Double.parseDouble(info[5]);
                    CurrencyExchangeFacade exchangeFacade = new CurrencyExchangeFacade(exchangeRates);
                    double exchangeRate = exchangeFacade.getExchangeRate(info[4], info[3]);
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    Command exchangeMoneyCommand = new ExchangeMoneyCommand(user, info[3], info[4], amount, exchangeRate);
                    user.executeCommand(exchangeMoneyCommand);
                } else if (command.contains("TRANSFER MONEY")) {
                    String[] info = command.split(" ");
                    double amount = Double.parseDouble(info[5]);
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    Command transferMoneyCommand = new TransferMoneyCommand(user, info[3], info[4], amount);
                    user.executeCommand(transferMoneyCommand);
                } else if (command.contains("BUY STOCKS")) {
                    String[] info = command.split(" ");
                    Stocks stock = new Stocks(info[3], Double.parseDouble(info[4]));
                    double stockPrice = stock.ultimulPret(stockValues1);
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    double totalCost = stockPrice * Integer.parseInt(info[4]);
                    if (recommendedStocks.contains("\"" + info[3] + "\"")) {
                        Command buyStocksCommand = new BuyStocksCommand(user, info[3], Integer.parseInt(info[4]), totalCost, true);
                        user.executeCommand(buyStocksCommand);
                    } else {
                        Command buyStocksCommand = new BuyStocksCommand(user, info[3], Integer.parseInt(info[4]), totalCost, false);
                        user.executeCommand(buyStocksCommand);
                    }
                } else if (command.contains("RECOMMEND STOCKS")) {
                    Actiuni actiuni = new Actiuni();
                    for (String stockLine : stockValues2) {
                        actiuni.addStock(stockLine);
                    }
                    recommendedStocks = actiuni.recommendStocks();
                    StringBuilder result = new StringBuilder();
                    result.append("{" + "\"stocksToBuy\":[");
                    for (String recommendedstocks : recommendedStocks) {
                        result.append(recommendedstocks+",");
                    }
                    if (!recommendedStocks.isEmpty()) {
                        result.deleteCharAt(result.length() - 1);
                    }
                    result.append("]}");
                    System.out.print(result + "\n");
                } else if (command.contains("LIST USER")) {
                    String[] info = command.split(" ");
                    boolean userFound = false;
                    for (Utilizator user : utilizatoriList) {
                        if (user.getEmail().equals(info[2])) {
                            System.out.println(user.toString());
                            userFound = true;
                            break;
                        }
                    }
                    if (!userFound) {
                        System.out.println("User with " + info[2] + " doesn't exist");
                    }
                } else if (command.contains("LIST PORTFOLIO")) {
                    String[] info = command.split(" ");
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    if (user != null) {
                        String portfolio = user.listPortfolio();
                        System.out.println(portfolio);
                    } else {
                        System.out.println("User with " + info[2] + " doesn't exist");
                    }
                } else if (command.contains("BUY PREMIUM")) {
                    String[] info = command.split(" ");
                    Utilizator user = userManager.gasesteUtilizator(info[2]);
                    if (user != null) {
                        user.buyPremium();
                    } else {
                        System.out.println("User with " + info[2] + " doesn't exist");
                    }
                }
            }
        }
    }
}
